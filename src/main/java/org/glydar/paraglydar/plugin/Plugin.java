package org.glydar.paraglydar.plugin;

import org.glydar.paraglydar.Server;
import org.glydar.paraglydar.command.Command;
import org.glydar.paraglydar.command.CommandExecutor;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.i18n.I18n;
import org.glydar.paraglydar.i18n.I18nLoader;
import org.glydar.paraglydar.i18n.I18nTarget;

import com.google.common.collect.ImmutableList;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.logging.Level;

public abstract class Plugin implements CommandExecutor, I18nTarget {

	private PluginLoader loader;
	private PluginLogger logger;
	private Server server;
	private boolean enabled = false;

	public void onEnable() {
	}

	public void onDisable() {
	}

	public abstract String getVersion();

	public abstract String getName();

	public String getAuthor() {
		return null;
	}

	public PluginLogger getLogger() {
		return logger;
	}

	public PluginLoader getLoader() {
		return loader;
	}

	public Server getServer() {
		return server;
	}

	@Override
	public Iterable<URL> getI18nLocations(String filename) {
		ImmutableList.Builder<URL> builder= ImmutableList.builder();

		URLClassLoader cl = loader.getClassLoader(this);
		builder.add(cl.getResource(filename));

		File userLocation = new File(getFolder(), filename);
		if (userLocation.exists()) {
			try {
				builder.add(userLocation.toURI().toURL());
			} catch (MalformedURLException exc) {
				getLogger().log(Level.WARNING, "Unable to convert i18n filepath to an url for " + filename, exc);
			}
		}

		return builder.build();
	}

	/**
	 * Get an {@link I18n} instance for the given name and the locales 
	 * defined in the server configuration. Localization files are looked for
	 * in the jar and the config folder of this plugin.
	 */
	public I18n getI18n(String name) {
		// TODO: Get locales from the server config
		I18nLoader i18nloader = new I18nLoader(this, new Locale[0]);
		return i18nloader.load(name);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean b) {
		if (enabled && b)
			return;
		else if (!enabled && !b)
			return;
		else if (b) {
			enabled = b;
			onEnable();
		} else {
			enabled = b;
			onDisable();
		}
	}

	public File getFolder() {
		File file = new File("config/" + getName());
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	public InputStream getResource(String name) {
		URLClassLoader cl = loader.getClassLoader(this);
		return cl.getResourceAsStream(name);
	}

	public void saveResource(String name) {
		File file = new File(getFolder(), name);
		saveResource(name, file);
	}

	public void saveResource(String name, File file) {
		InputStream in = null;
		OutputStream out = null;
		try {
			file.createNewFile();
			in = getResource(name);
			out = new FileOutputStream(file);
			if (in == null)
				throw new PluginException("Could not find resource " + file.getName());
			byte[] buffer = new byte[1024];
			int len = in.read(buffer);
			while (len != -1) {
				out.write(buffer, 0, len);
				len = in.read(buffer);
			}
		} catch (Exception e) {
			logger.warning("Error while saving file " + file.getName() + ": " + e.getMessage());
			e.printStackTrace();
			return;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
	}

	protected final void initialize(Server server, PluginLoader loader, PluginLogger logger) {
		this.server = server;
		this.logger = logger;
		this.loader = loader;
	}

	@Override
	public CommandOutcome execute(CommandSender cs, Command cmd, String lbl, String[] args) {
		return CommandOutcome.NOT_HANDLED;
	}

}

package org.glydar.paraglydar.plugin;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.glydar.paraglydar.ParaGlydar;

public class PluginLogger extends Logger {

	private Plugin plugin;

	public PluginLogger(Plugin plugin) {
		super(plugin.getClass().getCanonicalName(), null);
		this.plugin = plugin;
		setParent(ParaGlydar.getServer().getLogger());
		setLevel(Level.ALL);
	}

	public void log(LogRecord log) {
		log.setMessage("[" + plugin.getName() + "] " + log.getMessage());
		super.log(log);
	}
}

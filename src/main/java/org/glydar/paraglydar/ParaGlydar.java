package org.glydar.paraglydar;

import java.util.logging.Logger;

import org.glydar.paraglydar.data.DataCreator;
import org.glydar.paraglydar.models.ModelCreator;
import org.glydar.paraglydar.plugin.PluginLoader;

/**
 * Represents the Glydar API
 */
public final class ParaGlydar {
	private static Server s;
	private static ModelCreator mc;
	private static DataCreator dc;
	private static PluginLoader pl;

	/**
	 * Static class cannot be initialized.
	 */
	private ParaGlydar() {
	}

	;

	/**
	 * Gets the current {@link Server} singleton
	 *
	 * @return Server instance being ran
	 */
	public static Server getServer() {
		return s;
	}

	/**
	 * Attempts to set the {@link Server} singleton.
	 * <p/>
	 * This cannot be done if the Server is already set.
	 *
	 * @param server Server instance
	 */
	public static void setServer(Server server) {
		if (s == null) {
			s = server;
		} else {
			getLogger().severe("Can't change the server instance!");
		}
	}

	/**
	 * Gets an instance of the {@link DataCreator} class used to create the various Data-Types (Like Vector3).
	 *
	 * @return DataCreator instance
	 */
	public static DataCreator getDataCreator() {
		return dc;
	}

	/**
	 * Gets an instance of the {@link DataCreator} class used to create the various entities (Like NPCs).
	 *
	 * @return ModelCreator instance
	 */
	public static ModelCreator getModelCreator() {
		return mc;
	}
	
	/**
	 * Sets the plugin loader for this runtime.
	 * @param pluginLoader PluginLoader to be set to
	 */
	public static void setPluginLoader(PluginLoader pluginLoader) {
		if (pl == null) {
			pl = pluginLoader;
		} else {
			getLogger().severe("Can't change the plugin loader instance!");
		}
	}
	
	/**
	 * Gets the plugin loader for this runtime.
	 * @return The plugin loader
	 */
	public static PluginLoader getPluginLoader(){
		return pl;
	}

	/**
	 * Gets the logger for this runtime
	 * @return
	 */
	public static Logger getLogger() {
		return s.getLogger();
	}

	/**
	 * Sets the creator API for this runtime
	 * @param m ModelCreator to be set to
	 * @param d DataCreator to be set to
	 */
	public static void setCreatorAPI(ModelCreator m, DataCreator d) {
		mc = m;
		dc = d;
	}
}

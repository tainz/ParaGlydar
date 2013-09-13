package org.glydar.paraglydar;

import org.glydar.paraglydar.data.DataCreator;
import org.glydar.paraglydar.logging.GlydarLogger;
import org.glydar.paraglydar.models.ModelCreator;
import org.glydar.paraglydar.plugin.PluginLoader;

/**
 * Represents the Glydar API
 */
public final class ParaGlydar {

	private static Server server;

	/**
	 * Static class cannot be initialized.
	 */
	private ParaGlydar() {
	}

	/**
	 * Gets the current {@link Server} singleton
	 *
	 * @return Server instance being ran
	 */
	public static Server getServer() {
		return server;
	}

	/**
	 * Attempts to set the {@link Server} singleton.
	 * <p/>
	 * This cannot be done if the Server is already set.
	 *
	 * @param server Server instance
	 */
	public static void setServer(Server server) {
		if (ParaGlydar.server == null) {
			ParaGlydar.server = server;
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
		return server.getDataCreator();
	}

	/**
	 * Gets an instance of the {@link ModelCreator} class used to create the various entities (Like NPCs).
	 *
	 * @return ModelCreator instance
	 */
	public static ModelCreator getModelCreator() {
		return server.getModelCreator();
	}

	/**
	 * Gets the plugin loader for this runtime.
	 * 
	 * @return The plugin loader
	 */
	public static PluginLoader getPluginLoader(){
		return server.getPluginLoader();
	}

	/**
	 * Gets the logger for this runtime
	 * 
	 * @return
	 */
	public static GlydarLogger getLogger() {
		return server.getLogger();
	}
}

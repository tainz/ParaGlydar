package org.glydar.paraglydar;

import java.util.logging.Logger;

/**
 * Represents the Glydar API
 */
public final class ParaGlydar
{
	private static Server s;

	/**
	 * Static class cannot be initialized.
	 */
	private ParaGlydar() {};

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
	 *
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

	public static Logger getLogger() {
		return s.getLogger();
	}
}

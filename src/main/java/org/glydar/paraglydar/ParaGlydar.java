package org.glydar.paraglydar;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class ParaGlydar
{
	private static Server s;

    public static void setServer(Server server) {
    	if (s == null) {
    		s = server;
    	} else {
    		getLogger().severe("Can't change the server instance!");
    	}
    }
    
    public static Server getServer() {
    	return s;
    }

	public static Logger getLogger() {
		return s.getLogger();
	}
}

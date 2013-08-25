package org.glydar.paraglydar;

import org.glydar.paraglydar.util.LogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class ParaGlydar
{
	private static final Logger LOGGER = Logger.getLogger(ParaGlydar.class.getName());
	private static Server s;

	private ParaGlydar() {
		LOGGER.setUseParentHandlers(false);
		LogFormatter format = new LogFormatter();
		ConsoleHandler console = new ConsoleHandler();
		console.setFormatter(format);
		LOGGER.addHandler(console);
	}

    public static void setServer(Server server){
    	if (s == null){
    		s = server;
    	} else {
    		s.getLogger().severe("Can't change the server instance!");
    	}
    }
    
    public static Server getServer(){
    	return s;
    }

	public static Logger getLogger() {
		return LOGGER;
	}
}

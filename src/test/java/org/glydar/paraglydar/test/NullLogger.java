package org.glydar.paraglydar.test;

import java.util.logging.Level;

import org.glydar.paraglydar.logging.GlydarLogger;

public class NullLogger extends GlydarLogger {

	public NullLogger() {
		super(null, "", null);
	}

	public void log(Level level, Throwable thrown, String message, Object... parameters) {
	}

	public void logI(Level level, Throwable thrown, String message, Object... parameters) {
	}
}

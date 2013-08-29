package org.glydar.paraglydar.test;

import java.util.logging.Logger;

public class NullLogger extends Logger {

	public NullLogger() {
		super("", null);
		setUseParentHandlers(false);
	}
}

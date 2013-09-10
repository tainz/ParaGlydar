package org.glydar.paraglydar.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public class GlydarLogRecord extends LogRecord {

	private static final long serialVersionUID = -8178899715814412568L;

	private final String prefix;

	public GlydarLogRecord(Level level, String message, String prefix) {
		super(level, message);
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
}

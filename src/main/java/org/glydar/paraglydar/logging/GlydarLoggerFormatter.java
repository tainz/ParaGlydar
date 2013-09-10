package org.glydar.paraglydar.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class GlydarLoggerFormatter extends Formatter {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("[HH:mm:ss]");

	private final boolean displaySource;

	public GlydarLoggerFormatter(boolean displaySource) {
		this.displaySource = displaySource;
	}

	@Override
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder();

		builder.append(DATE_FORMAT.format(new Date(record.getMillis())));
		builder.append(" ");
		builder.append(record.getLevel().getLocalizedName());
		builder.append(": ");
		builder.append(formatMessage(record));
		if (displaySource) {
			builder.append(" (@");
			builder.append(record.getSourceClassName());
			builder.append("#");
			builder.append(record.getSourceMethodName());
			builder.append(")");
		}
		builder.append(LINE_SEPARATOR);

		if (record.getThrown() != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				builder.append(sw.toString());
			} catch (Exception exc) {
				// Ignore
			}
		}

		return builder.toString();
	}
}

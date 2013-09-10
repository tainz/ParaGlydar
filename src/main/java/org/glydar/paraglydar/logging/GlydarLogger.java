package org.glydar.paraglydar.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.glydar.paraglydar.i18n.I18n;
import org.glydar.paraglydar.i18n.I18nLoader;
import org.glydar.paraglydar.i18n.I18nTarget;

public class GlydarLogger {

	public static GlydarLogger of(I18nTarget target) {
		I18n i18n = new I18nLoader(target).load("logs");
		return of(Logger.getLogger(target.getClass().getCanonicalName()), i18n);
	}

	public static GlydarLogger of(Logger logger, I18n i18n) {
		return new GlydarLogger(logger, i18n);
	}

	private final Logger logger;
	private final I18n i18n;

	protected GlydarLogger(Logger logger, I18n i18n) {
		this.logger = logger;
		this.i18n = i18n;
	}

	public void log(Level level, Throwable thrown, String message, Object... parameters) {
		LogRecord record = new LogRecord(level, message);
		record.setThrown(thrown);
		if (parameters.length > 1) {
			record.setParameters(parameters);
		}

		logger.log(record);
	}

	public void logI(Level level, Throwable thrown, String key, Object... parameters) {
		I18nLogRecord record = new I18nLogRecord(i18n, level, key, parameters);
		record.setThrown(thrown);
		logger.log(record);
	}

	public void log(Level level, String message, Object... parameters) {
		log(level, null, message, parameters);
	}

	public void logI(Level level, String message, Object... parameters) {
		log(level, null, message, parameters);
	}

	public void severe(Throwable throwable, String message, Object... parameters) {
		log(Level.SEVERE, throwable, message, parameters);
	}

	public void severeI(Throwable throwable, String key, Object... parameters) {
		logI(Level.SEVERE, throwable, key, parameters);
	}

	public void severe(String message, Object... parameters) {
		log(Level.SEVERE, message, parameters);
	}

	public void severeI(String key, Object... parameters) {
		logI(Level.SEVERE, key, parameters);
	}

	public void warning(Throwable throwable, String message, Object... parameters) {
		log(Level.WARNING, throwable, message, parameters);
	}

	public void warningI(Throwable throwable, String key, Object... parameters) {
		logI(Level.WARNING, throwable, key, parameters);
	}

	public void warning(String message, Object... parameters) {
		log(Level.WARNING, message, parameters);
	}

	public void warningI(String key, Object... parameters) {
		logI(Level.WARNING, key, parameters);
	}

	public void config(String message, Object... parameters) {
		log(Level.CONFIG, message, parameters);
	}

	public void configI(String key, Object... parameters) {
		logI(Level.CONFIG, key, parameters);
	}

	public void info(String message, Object... parameters) {
		log(Level.INFO, message, parameters);
	}

	public void infoI(String key, Object... parameters) {
		logI(Level.INFO, key, parameters);
	}

	public void fine(String message, Object... parameters) {
		log(Level.FINE, message, parameters);
	}

	public void fineI(String key, Object... parameters) {
		logI(Level.FINE, key, parameters);
	}

	public void finer(String message, Object... parameters) {
		log(Level.FINER, message, parameters);
	}

	public void finerI(String key, Object... parameters) {
		logI(Level.FINER, key, parameters);
	}

	public void finest(String message, Object... parameters) {
		log(Level.FINEST, message, parameters);
	}

	public void finestI(String key, Object... parameters) {
		logI(Level.FINEST, key, parameters);
	}
}
package org.glydar.paraglydar.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.glydar.paraglydar.i18n.I18n;

public class I18nLogRecord extends LogRecord {

	private static final long serialVersionUID = -445207568551121150L;

	private final I18n i18n;
	private final Object[] i18nParameters;

	public I18nLogRecord(I18n i18n, Level level, String key, Object... parameters) {
		super(level, key);
		this.i18n = i18n;
		this.i18nParameters = parameters;
	}

	public I18n getI18n() {
		return i18n;
	}

	public String getKey() {
		return super.getMessage();
	}

	public Object[] getI18nParameters() {
		return i18nParameters;
	}

	public String getMessage() {
		return i18n.get(getKey(), i18nParameters);
	}
}
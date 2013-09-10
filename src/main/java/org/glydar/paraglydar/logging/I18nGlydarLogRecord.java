package org.glydar.paraglydar.logging;

import java.util.logging.Level;

import org.glydar.paraglydar.i18n.I18n;

public class I18nGlydarLogRecord extends GlydarLogRecord {

	private static final long serialVersionUID = -445207568551121150L;

	private final I18n i18n;
	private final Object[] i18nParameters;

	public I18nGlydarLogRecord(Level level, String key, String prefix, I18n i18n, Object... parameters) {
		super(level, key, prefix);
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
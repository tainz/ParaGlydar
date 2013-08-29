package org.glydar.paraglydar.i18n;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class PropertiesI18nLoader implements I18nFormatLoader {

	@Override
	public String[] getExtensions() {
		return new String[] { "properties" };
	}

	@Override
	public Map<?, ?> load(Reader reader) {
		Properties properties = new Properties();
		try {
			properties.load(reader);
			return properties;
		}
		catch (IOException exc) {
			return Collections.<String, String> emptyMap();
		}
	}
}

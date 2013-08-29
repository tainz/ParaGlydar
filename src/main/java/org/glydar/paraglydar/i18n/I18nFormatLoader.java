package org.glydar.paraglydar.i18n;

import java.io.Reader;
import java.util.Map;

/**
 * Interfaces for classes which load messages for a specific format.
 */
public interface I18nFormatLoader {

	/**
	 * File extensions (without the dot) accepted by this loader.
	 */
	String[] getExtensions();

	/**
	 * Parse the reader as a Map of &lt;key, message&gt;.
	 */
	Map<?, ?> load(Reader reader);
}

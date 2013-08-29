package org.glydar.paraglydar.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

/**
 * A set of messages loaded with {@link I18nLoader} for a given {@link Locale}.
 */
public class I18n {

	private final String name;
	private final Map<String, Object> map;

	I18n(String name, Map<String, Object> map) {
		this.name = name;
		this.map = ImmutableMap.copyOf(map);
	}

	public String getName() {
		return name;
	}

	/**
	 * Checks if the message for given key exists.
	 */
	public boolean has(String key) {
		Preconditions.checkNotNull(key);
		return map.containsKey(key);
	}

	/**
	 * Gets the message for the given key interpolating given arguments.
	 */
	public String get(String key, Object... arguments) {
		String message = rawGet(key, arguments);
		if (message == null) {
			return keyNotFound(key);
		}

		return message;
	}

	/**
	 * Works like {@link #get(String, Object...)} expect it looks up for each
	 * keys in the given order, returning the first message found.
	 */
	public String get(String[] keys, Object... arguments) {
		Preconditions.checkArgument(keys.length > 0, "Keys can't be empty");
		for (String key : keys) {
			String message = rawGet(key, arguments);
			if (message != null) {
				return message;
			}
		}

		return keyNotFound(keys[keys.length - 1]);
	}

	/**
	 * Returns all the keys.
	 * <p>
	 * Note: The returned set is immutable.
	 */
	public Set<String> keys() {
		return map.keySet();
	}

	private String keyNotFound(String key) {
		return "## Missing localization for key \"" + key + "\" in \"" + name + "\" ##";
	}

	private String rawGet(String key, Object... arguments) {
		Preconditions.checkNotNull(key);
		Object message = map.get(key);
		if (!(message instanceof MessageFormat)) {
			// Assumes it's either a string or null.
			return (String) message;
		}

		return ((MessageFormat) message).format(arguments);
	}
}

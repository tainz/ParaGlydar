package org.glydar.paraglydar.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Class which handles loading of i18n resources for the given target
 * and locale.
 */
public class I18nLoader {

	private static final Locale UNIVERSAL_LOCALE = Locale.US;
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final Pattern MESSAGE_ARG_PATTERN = Pattern.compile("\\{\\d");

	private final I18nFormatLoader[] loaders;
	private final I18nTarget       target;
	private final Locale[]         locales;

	public I18nLoader(I18nTarget target, Locale... locales) {
		List<I18nFormatLoader> loadedLoaders = Lists.newArrayList(ServiceLoader.load(I18nFormatLoader.class));
		this.loaders = Lists.reverse(loadedLoaders).toArray(new I18nFormatLoader[loadedLoaders.size()]);
		this.target = Preconditions.checkNotNull(target);
		this.locales = normalizeLocales(locales);
	}

	/**
	 * Loads the resources with the given name.
	 */
	public I18n load(String name) {
		return new I18n(name, loadForEachLocale(name));
	}

	private Map<String, Object> loadForEachLocale(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Locale locale : locales) {
			loadForEachFormatLoader(map, locale, name + "_" + locale.toString());
		}
		return map;
	}

	private void loadForEachFormatLoader(Map<String, Object> map, Locale locale, String baseName) {
		for (I18nFormatLoader loader : loaders) {
			loadForEachExtension(map, locale, baseName, loader);
		}
	}

	private void loadForEachExtension(Map<String, Object> map, Locale locale, String baseName,
			I18nFormatLoader loader) {
		for (String extension : loader.getExtensions()) {
			String name = baseName + "." + extension;
			loadForEachLocation(map, locale, loader, name);
		}
	}

	private void loadForEachLocation(Map<String, Object> map, Locale locale, I18nFormatLoader loader,
			String name) {
		for (URL location : target.getI18nLocations(name)) {
			if (location == null) {
				continue;
			}

			actuallyLoad(map, loader, locale, location);
		}
	}

	private void actuallyLoad(Map<String, Object> map, I18nFormatLoader loader, Locale locale,
			URL location) {
		try (Reader reader = reader(location)) {
			putAll(map, loader.load(reader), locale);
		}
		catch (IOException exc) {
		}
	}

	private Reader reader(URL location) throws IOException {
		URLConnection connection = location.openConnection();
		connection.setUseCaches(false);
		InputStream inputStream = connection.getInputStream();
		return new InputStreamReader(inputStream, CHARSET);
	}

	private void putAll(Map<String, Object> map, Map<?, ?> loaded, Locale locale) {
		for (Entry<?, ?> entry : loaded.entrySet()) {
			String key = entry.getKey().toString();
			String message = entry.getValue().toString();
			map.put(key, parse(message, locale));
		}
	}

	private Object parse(String rawMessage, Locale locale) {
		if (!MESSAGE_ARG_PATTERN.matcher(rawMessage).find()) {
			return rawMessage;
		}

		String message = rawMessage;
		message = message.replaceAll("'", "''");
		message = message.replaceAll("\\\\", "'");
		return new MessageFormat(message, locale);
	}

	@VisibleForTesting
	public static Locale[] normalizeLocales(Locale... locales) {
		Set<Locale> localesSet = Sets.newLinkedHashSet();
		for (Locale locale : locales) {
			Preconditions.checkNotNull(locale);
			localesSet.add(locale);
			localesSet.add(new Locale(locale.getLanguage()));
		}
		localesSet.add(UNIVERSAL_LOCALE);
		localesSet.add(new Locale(UNIVERSAL_LOCALE.getLanguage()));

		Locale[] resolved = new Locale[localesSet.size()];
		int i = resolved.length - 1;
		for (Locale locale : localesSet) {
			resolved[i] = locale;
			i--;
		}
		return resolved;
	}
}

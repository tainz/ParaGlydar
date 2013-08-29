package org.glydar.paraglydar.i18n;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Collections;
import java.util.Locale;

import org.junit.Test;

public class I18nTest {

	@Test
	public void testNormalizeLocales() {
		Locale[] locales1 = I18nLoader.normalizeLocales();
		Locale[] locales2 = I18nLoader.normalizeLocales(Locale.US);
		Locale[] locales3 = I18nLoader.normalizeLocales(Locale.FRANCE, Locale.US);
		Locale[] locales4 = I18nLoader.normalizeLocales(
				Locale.CANADA_FRENCH, // fr_CA
				Locale.FRANCE,        // fr_FR
				Locale.CANADA,        // en_CA
				Locale.US);           // en_US

		assertArrayEquals(new Locale[] { Locale.ENGLISH, Locale.US }, locales1);
		assertArrayEquals(new Locale[] { Locale.ENGLISH, Locale.US }, locales2);
		assertArrayEquals(new Locale[] {
				Locale.ENGLISH, Locale.US,
				Locale.FRENCH, Locale.FRANCE
		}, locales3);
		assertArrayEquals(new Locale[] {
				Locale.US,            // en_US
				Locale.ENGLISH,       // en
				Locale.CANADA,        // en_CA
				Locale.FRANCE,        // fr_FR
				Locale.FRENCH,        // fr
				Locale.CANADA_FRENCH  // fr_CA
		}, locales4);
	}

	@Test
	public void testMissingKey() {
		I18nLoader loader = new I18nLoader(new TestI18nTarget());
		I18n i18n = loader.load("test");

		assertEquals("## Missing localization for key \"missing.key\" in \"test\" ##", i18n.get("missing.key"));
	}

	@Test
	public void testOverride() {
		I18nLoader loader = new I18nLoader(new TestI18nTarget(), Locale.FRANCE);
		I18n i18n = loader.load("test");

		assertEquals("key1 fr_FR", i18n.get("key1"));
		assertEquals("key2 fr",    i18n.get("key2"));
		assertEquals("key3 en_US", i18n.get("key3"));
		assertEquals("key4 en",    i18n.get("key4"));
	}

	@Test
	public void testArgs() {
		I18nLoader loader = new I18nLoader(new TestI18nTarget());
		I18n i18n = loader.load("test");

		assertEquals("Message with 2 args *here* and *also here*", i18n.get("with.args", 2, "here", "also here"));
	}

	@Test
	public void testEncoding() {
		I18nLoader loader = new I18nLoader(new TestI18nTarget());
		I18n i18n = loader.load("encoding");

		assertEquals("àéèêç¡™£¢∞§¶•ª", i18n.get("encoding"));
	}
}

class TestI18nTarget implements I18nTarget {

	@Override
	public Iterable<URL> getI18nLocations(String filename) {
		return Collections.singletonList(TestI18nTarget.class.getResource(filename));
	}
}

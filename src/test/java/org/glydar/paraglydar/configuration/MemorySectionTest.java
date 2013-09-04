package org.glydar.paraglydar.configuration;

public class MemorySectionTest extends ConfigurationSectionTest {
    @Override
    public ConfigurationSection getConfigurationSection() {
        return new MemoryConfiguration().createSection("section");
    }
}

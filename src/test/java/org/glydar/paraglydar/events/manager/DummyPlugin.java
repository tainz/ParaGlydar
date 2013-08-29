package org.glydar.paraglydar.events.manager;

import org.glydar.paraglydar.plugin.Plugin;

public class DummyPlugin extends Plugin {

	@Override
	public String getVersion() {
		return "test";
	}

	@Override
	public String getName() {
		return "DummyPlugin";
	}
}

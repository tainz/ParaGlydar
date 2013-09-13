package org.glydar.paraglydar;

import java.util.List;
import java.util.Set;

import org.glydar.paraglydar.models.World;

public interface ServerConfig {

	boolean isDebug();

	int getPort();

	int getFPS();

	int getMaxPlayers();

	void setMaxPlayers(int maxPlayers);

	Set<String> getAdmins();

	boolean addAdmin(String name);

	boolean removeAdmin(String name);

	WorldConfig getDefaultWorldConfig();

	List<WorldConfig> getAllWorldsConfigs();

	List<WorldConfig> getOtherWorldsConfigs();

	void persistWorld(World world);

	interface WorldConfig {

		String getName();

		int getSeed();

		boolean isPvpAllowed();
	}
}

package org.glydar.paraglydar.models;

import java.util.Collection;

public class CustomTarget implements BaseTarget {
	Collection<Player> players;

	public CustomTarget(Collection<Player> players) {
		this.players = players;
	}

	@Override
	public Collection<Player> getPlayers() {
		return players;
	}

}

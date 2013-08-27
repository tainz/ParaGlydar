package org.glydar.paraglydar.models;

import java.util.Collection;

import org.glydar.paraglydar.ParaGlydar;

public class EveryoneTarget implements BaseTarget {
	public static final EveryoneTarget INSTANCE = new EveryoneTarget();

	private EveryoneTarget() {
	}

	;

	@Override
	public Collection<Player> getPlayers() {
		return ParaGlydar.getServer().getConnectedPlayers();
	}
}

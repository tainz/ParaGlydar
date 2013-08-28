package org.glydar.paraglydar.models;

import java.util.Collection;

public class WorldTarget implements BaseTarget {
	private World w;
	
	public WorldTarget(Player player){
		this.w = player.getWorld();
	}
	
	public WorldTarget(World world){
		this.w = world;
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return w.getWorldPlayers();
	}

}

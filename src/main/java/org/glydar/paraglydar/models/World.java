package org.glydar.paraglydar.models;

import java.util.Collection;

public interface World {
	//Is this a good place for these? Should they be Enum'd?
	int BLOCK_SCALE = 65536;
	int CHUNK_SCALE = BLOCK_SCALE * 256;
	int SECTOR_SCALE = CHUNK_SCALE * 64;
	int MAX_POSITION = SECTOR_SCALE * 1024;
	
	public long getWorldId();
	
	public Collection<Player> getWorldPlayers();
	
	public Collection<Entity> getWorldEntities();
	
	public int getSeed();
	
	public void setSeed(int seed);
	
	public void setName(String name);
	
	public String getName();
	
	public void broadcastMessage(String message);
}

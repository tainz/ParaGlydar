package org.glydar.paraglydar.models;

import org.glydar.paraglydar.data.EntityData;

public interface Entity {

	public long getEntityId();

	public World getWorld();
	
	public void changeWorld(World world);
	
	/**
	 * Call this whenever you modify anything in Player.data and wish to update all of the clients.
	 */
	public void forceUpdateData();

	public void forceUpdateData(boolean fullUpdate);

	public EntityData getEntityData();

	public void setEntityData(EntityData ed);
	
	public void sendMessageFromEntity(BaseTarget target, String message);
	
	public void sendMessageFromEntity(String message);
	
	public void damage(float damage);
	
	public void kill();
	
	public void setHealth(float health);
	
	public void heal(float health);

	public boolean equals(Object o);

	public int hashCode();

	public String toString();
}

package org.glydar.paraglydar.models;

import org.glydar.paraglydar.data.EntityData;

public interface Entity {

	public long getEntityId();

	public World getWorld();
	
	/**
	 * Call this whenever you modify anything in Player.data and wish to update all of the clients.
	 */
	public void forceUpdateData();

	public void forceUpdateData(EntityData ed);

	public EntityData getEntityData();

	public void setEntityData(EntityData ed);

	public boolean equals(Object o);

	public int hashCode();

	public String toString();
}

package org.glydar.paraglydar.event.events;

import org.glydar.paraglydar.data.EntityData;
import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.models.Player;

/**
 * Represents the event called when a player's client sends an updated
 * version of their data to the server.
 */
public class EntityUpdateEvent extends Event {

	private boolean cancelled = false;
	private Player player;
	private EntityData ed;

	public EntityUpdateEvent(final Player player, final EntityData ed) {
		this.setPlayer(player);
		this.setEntityData(ed);
	}

	/**
	 * This event cannot be cancelled.
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Gets the player who is updating their entity data.
	 * 
	 * @return Player The player in question.
	 */
	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Gets the {@link EntityData} being sent in this update.
	 * 
	 * @return EntityData The EntityData
	 */
	public EntityData getEntityData() {
		return ed;
	}

	/**
	 * Sets a new set of EntityData that replaces the old update.
	 * Note, the player himself will not see these changes, but the other players on the server will.
	 * 
	 * @param ed The EntityData to be set.
	 */
	public void setEntityData(EntityData ed) {
		this.ed = ed;
	}
}

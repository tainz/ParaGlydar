package org.glydar.paraglydar.event.events;

import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.models.Player;

/**
 * Represents the event called when a player actually joins the server 
 * and is now visible to all other players.
 * This is not to be confused with the connection event that just signifies 
 * an invisible connection to the server.
 */
public class PlayerJoinEvent extends Event {

	private Player player;
	private String joinMessage;

	public PlayerJoinEvent(final Player player) {
		this.player = player;
		joinMessage = "Player " + player.getName() + " (ID: " + player.getEntityId() + ") has joined the server.";
	}

	/**
	 * Gets the {@link Player} instance of the player that just joined.
	 * 
	 * @return Player The Player that has just joined.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the join message of this event.
	 * 
	 * @return String The join message.
	 */
	public String getJoinMessage() {
		return joinMessage;
	}

	/**
	 * Sets the join message of this event.
	 * 
	 * @param m The join message to be set.
	 */
	public void setJoinMessage(String m) {
		this.joinMessage = m;
	}
}

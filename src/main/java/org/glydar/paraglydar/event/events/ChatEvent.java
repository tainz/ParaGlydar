package org.glydar.paraglydar.event.events;

import java.util.Collection;

import org.glydar.paraglydar.event.Cancellable;
import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.models.BaseTarget;
import org.glydar.paraglydar.models.CustomTarget;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.models.WorldTarget;

/**
 * Represents the event called when a player sends a chat message to the server.
 */
public class ChatEvent extends Event implements Cancellable {

	private boolean cancelled = false;
	private Player player;
	private String message;
	private BaseTarget recievers;

	public ChatEvent(final Player player, final String message) {
		this.player = player;
		this.message = message;
		recievers = new WorldTarget(player.getWorld());
	}

	/**
	 * Returns whether or not the event is cancelled.
	 * 
	 * @return boolean If the event is cancelled.
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Sets whether or not the event is cancelled.
	 * 
	 * @param cancelled If the event is cancelled.
	 */
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Gets the chat message sent.
	 * 
	 * @return String The chat message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the chat message to be sent.
	 * 
	 * @param message The chat message to be set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the player who sent the chat message.
	 * 
	 * @return Player The player that sent the message.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the recievers of the message in the form of a {@link BaseTarget}
	 * 
	 * @return BaseTarget The recievers of the message.
	 */
	public BaseTarget getTarget() {
		return recievers;
	}

	/**
	 * Returns the recievers of the message in the form of a Collection of players.
	 * 
	 * @return Collection<Player> The recievers of the message.
	 */
	public Collection<Player> getRecievers() {
		return recievers.getPlayers();
	}

	/**
	 * Sets who will recieve the message
	 * 
	 * @param c The new recievers of the message.
	 */
	public void setRecievers(Collection<Player> c) {
		recievers = new CustomTarget(c);
	}
}

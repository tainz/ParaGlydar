package org.glydar.paraglydar.event.events;

import java.util.Collection;

import org.glydar.paraglydar.event.Cancellable;
import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.models.BaseTarget;
import org.glydar.paraglydar.models.CustomTarget;
import org.glydar.paraglydar.models.EveryoneTarget;
import org.glydar.paraglydar.models.Player;

public class ChatEvent extends Event implements Cancellable {

	private boolean cancelled = false;
	private Player player;
	private String message;
	private BaseTarget recievers = EveryoneTarget.INSTANCE;

	public ChatEvent(final Player player, final String message) {
		this.setPlayer(player);
		this.message = message;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public BaseTarget getTarget(){
		return recievers;
	}
	
	public Collection<Player> getRecievers(){
		return recievers.getPlayers();
	}
	
	public void setRecievers(Collection<Player> c){
		recievers = new CustomTarget(c);
	}
}

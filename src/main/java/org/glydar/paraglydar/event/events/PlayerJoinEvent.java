package org.glydar.paraglydar.event.events;

import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.models.Player;

public class PlayerJoinEvent extends Event {

	private Player player;
	private String joinMessage;

	public PlayerJoinEvent(final Player player) {
		this.player = player;
		joinMessage = "Player " + player.getName() + " (ID: " + player.getEntityId() + ") has joined the server.";
	}

	public Player getPlayer() {
		return player;
	}
	
	public String getJoinMessage(){
		return joinMessage;
	}

	public void setJoinMessage(String m){
		this.joinMessage = m;
	}
}
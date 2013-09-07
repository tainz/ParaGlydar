package org.glydar.paraglydar.command;

import org.glydar.paraglydar.permissions.Permissible;

/**
 * Represents someone or something who/which can send a command
 * 
 * @see {@link ConsoleCommandSender}
 * @see {@link org.glydar.paraglydar.models.Player}
 */
public interface CommandSender extends Permissible {

	/**
	 * Gets the name of the sender.
	 */
	public String getName();

	/**
	 * Sends a (private) message to the sender.
	 */
	public void sendMessage(String message);
}
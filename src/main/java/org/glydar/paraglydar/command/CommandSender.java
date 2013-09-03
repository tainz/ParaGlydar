package org.glydar.paraglydar.command;

import org.glydar.paraglydar.permissions.Permissible;

/**
 * Represents someone or something who/which can send a command
 */
public interface CommandSender extends Permissible {

	public String getName();

	public void sendMessage(String message);
}
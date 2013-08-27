package org.glydar.paraglydar.command;

import org.glydar.paraglydar.permissions.Permissible;

/**
 * @author YoshiGenius
 */
public interface CommandSender extends Permissible {

	public String getName();

	public void sendMessage(String message);

}
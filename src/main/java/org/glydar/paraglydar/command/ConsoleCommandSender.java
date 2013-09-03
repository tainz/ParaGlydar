package org.glydar.paraglydar.command;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.permissions.Permission;

import java.util.logging.Level;

/**
 * Represents a {@link CommandSender} for the console input.
 */
public class ConsoleCommandSender implements CommandSender {

	@Override
	public String getName() {
		return "*Console*";
	}

	@Override
	public void sendMessage(String message) {
		ParaGlydar.getLogger().log(Level.INFO, message);
	}

	@Override
	public boolean hasPermission(String permission) {
		return true;
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return true;
	}
}

package org.glydar.paraglydar.command;

import org.glydar.paraglydar.permissions.Permission;

/**
 * Low level interface which defines how a method should be executed.
 */
public interface CommandExecutor {

	String getUsage();

	Permission getPermission();

	int minArgs();

	int maxArgs();

	CommandOutcome execute(CommandSender sender, String[] args);
}

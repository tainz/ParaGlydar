package org.glydar.paraglydar.command;

import org.glydar.paraglydar.permissions.Permission;

public interface CommandExecutor {

	String getUsage();

	Permission getPermission();

	int minArgs();

	int maxArgs();

	CommandOutcome execute(CommandSender sender, String[] args);
}

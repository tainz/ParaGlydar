package org.glydar.paraglydar.command.manager;

import org.glydar.paraglydar.command.CommandExecutor;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.plugin.Plugin;

public class RegisteredCommand {

	private final Plugin plugin;
	private final CommandExecutor executor;
	private final boolean isAlias;

	public RegisteredCommand(Plugin plugin, CommandExecutor executor, boolean isAlias) {
		this.plugin = plugin;
		this.executor = executor;
		this.isAlias = isAlias;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public boolean isAlias() {
		return isAlias;
	}

	public String getUsage() {
		return executor.getUsage();
	}

	public CommandOutcome execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission(executor.getPermission())) {
			return CommandOutcome.NO_PERMISSION;
		}

		if (args.length < executor.minArgs()) {
			return CommandOutcome.WRONG_USAGE;
		}

		if (args.length > executor.maxArgs()) {
			return CommandOutcome.WRONG_USAGE;
		}

		return executor.execute(sender, args);
	}
}

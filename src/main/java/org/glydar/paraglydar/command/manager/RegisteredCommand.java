package org.glydar.paraglydar.command.manager;

import org.glydar.paraglydar.command.CommandExecutor;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.plugin.Plugin;

public class RegisteredCommand {

	private final Plugin plugin;
	private final CommandExecutor executor;

	public RegisteredCommand(Plugin plugin, CommandExecutor executor) {
		this.plugin = plugin;
		this.executor = executor;
	}

	public Plugin getPlugin() {
		return plugin;
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

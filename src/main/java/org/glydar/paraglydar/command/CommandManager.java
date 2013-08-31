package org.glydar.paraglydar.command;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glydar.paraglydar.permissions.Permission;
import org.glydar.paraglydar.plugin.Plugin;

public class CommandManager {

	private static final String PERMISSION_ERROR = "Sorry, you do not have permission for this command.";
	private static final String INVALID_COMMAND = "Invalid command entered! Type /help for help!";
	private static final String ERROR_OCCURRED = "An error occurred! Please contact the server administrators.";

	private final Logger logger;
	private final List<RegisteredCommand> commands;

	public CommandManager(Logger logger) {
		this.logger = logger;
		this.commands = new ArrayList<RegisteredCommand>();
	}

	public void registerCommand(Plugin p, CommandExecutor ce, String name) {
		for (Method m : ce.getClass().getMethods()){
			if (validateMethod(m)){
				if (m.getAnnotation(Command.class).name().equals(name)){
					registerCommand(p, ce, m, m.getAnnotation(Command.class));
					break;
				}
			}
		}
	}

	public void registerCommandExecutor(Plugin p, CommandExecutor ce){
		for (Method m : ce.getClass().getMethods()){
			if (validateMethod(m)){
				registerCommand(p, ce, m, m.getAnnotation(Command.class));
			}
		}
	}

	private boolean validateMethod(Method m){
		Command cmdAn = m.getAnnotation(Command.class);
		if (cmdAn == null){
			return false;
		}

		if (Modifier.isStatic(m.getModifiers())) {
			logInvalidCommandMethod(m, "is static");
			return false;
		}

		if (!Modifier.isPublic(m.getModifiers())) {
			logInvalidCommandMethod(m, "is not public");
			return false;
		}

		if (m.getReturnType() != CommandOutcome.class) {
			logInvalidCommandMethod(m, "does not return 'CommandOutcome'");
			return false;
		}

		Class<?>[] parameterTypes = m.getParameterTypes();
		if (parameterTypes.length != 2) {
			logInvalidCommandMethod(m, "does not have the required (2) parameters");
			return false;
		}

		if (parameterTypes[0] != CommandSender.class) {
			logInvalidCommandMethod(m, "does not have 'CommandSender' as it's first paremeter");
			return false;
		}

		if (parameterTypes[1] != String[].class) {
			logInvalidCommandMethod(m, "does not have 'String[]' as it's second paremeter");
			return false;
		}
		return true;
	}

	private void logInvalidCommandMethod(Object... args) {
		logger.log(Level.WARNING, "Command Method `{0}` {1}, skipping", args);
	}

	private void registerCommand(Plugin p, CommandExecutor ce, Method m, Command c) {
		RegisteredCommand command = new RegisteredCommand(p, c.name(), ce, c.usage(), m);
		command.setPermission(new Permission(c.permission(), c.permissionDefault()));
		command.setMinMax(c.min(), c.max());
		command.setAliases(c.aliases());
		commands.add(command);
	}

	public CommandOutcome execute(CommandSender cs, String name, String... args) {
		//TODO: Currently only first command in array of commands is used
		for (RegisteredCommand cmd : commands){
			if (cmd.getCommandName().equalsIgnoreCase(name)){
				logger.info("Handling valid command");
				return doExecute(cs, args, cmd);
			}
		}

		cs.sendMessage(INVALID_COMMAND);
		return CommandOutcome.NOT_HANDLED;
	}

	//TODO: This feels a bit messy :P
	private CommandOutcome doExecute(CommandSender cs, String[] args, RegisteredCommand cmd) {
		CommandOutcome outcome = null;
		if (cs.hasPermission(cmd.getPermission())){
			if (validateArgsLength(args.length, cmd)){
				try {
					CommandOutcome o = cmd.execute(cs, args);
					outcome = o;
				} catch (Exception exc) {
					outcome = CommandOutcome.ERROR;
					logger.log(Level.WARNING, "Exception thrown in Event handler", exc);
				}
			} else {
				outcome = CommandOutcome.WRONG_USAGE;
			}
		} else {
			outcome = CommandOutcome.NO_PERMISSION;
		}

		switch (outcome) {
		case SUCCESS:
			break;
		case NO_PERMISSION:
			cs.sendMessage(PERMISSION_ERROR);
			break;
		case WRONG_USAGE:
			cs.sendMessage(cmd.getUsage());
			break;
		case ERROR:
			cs.sendMessage(ERROR_OCCURRED);
			break;
		case NOT_HANDLED:
			cs.sendMessage(ERROR_OCCURRED);
			// Commands shouldn't return this
			outcome = CommandOutcome.FAILURE_OTHER;
			break;
		case FAILURE_OTHER:
			cs.sendMessage(ERROR_OCCURRED);
			break;
		}

		return outcome;
	}

	private boolean validateArgsLength(int length, RegisteredCommand cmd) {
		if (length < cmd.getMinArguments()) {
			return false;
		}
		if (length > cmd.getMaxArguments()) {
			return false;
		}

		return true;
	}
}

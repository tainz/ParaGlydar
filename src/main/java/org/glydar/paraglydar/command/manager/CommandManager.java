package org.glydar.paraglydar.command.manager;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glydar.paraglydar.command.Command;
import org.glydar.paraglydar.command.CommandExecutor;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.command.CommandSet;
import org.glydar.paraglydar.plugin.Plugin;

public class CommandManager {

	private static final String PERMISSION_ERROR = "Sorry, you do not have permission for this command.";
	private static final String INVALID_COMMAND = "Invalid command entered! Type /help for help!";
	private static final String ERROR_OCCURRED = "An error occurred! Please contact the server administrators.";
	private static final String UNSUPPORTED_SENDER_ERROR = "";

	private final Logger logger;
	private final Map<String, RegisteredCommand> commands;

	public CommandManager(Logger logger) {
		this.logger = logger;
		this.commands = new HashMap<>();
	}

	public void register(Plugin plugin, CommandSet set, String name) {
		for (Method method : set.getClass().getDeclaredMethods()) {
			if (validateMethod(method)) {
				Command annotation = method.getAnnotation(Command.class);
				if (annotation.name().equals(name)) {
					registerMethodCommand(plugin, set, method, annotation);
					break;
				}
			}
		}
	}

	public void register(Plugin plugin, CommandSet set) {
		for (Method method : set.getClass().getDeclaredMethods()) {
			if (validateMethod(method)) {
				registerMethodCommand(plugin, set, method, method.getAnnotation(Command.class));
			}
		}
	}

	private boolean validateMethod(Method m) {
		Command cmdAn = m.getAnnotation(Command.class);
		if (cmdAn == null) {
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

		if (!CommandSender.class.isAssignableFrom(parameterTypes[0])) {
			logInvalidCommandMethod(m, "does not have a subclass of 'CommandSender' as it's first parameter");
			return false;
		}

		if (parameterTypes[1] != String[].class) {
			logInvalidCommandMethod(m, "does not have 'String[]' as it's second parameter");
			return false;
		}
		return true;
	}

	private void logInvalidCommandMethod(Object... args) {
		logger.log(Level.WARNING, "Command Method `{0}` {1}, skipping", args);
	}

	private void registerMethodCommand(Plugin plugin, CommandSet instance, Method method, Command annotation) {
		MethodCommandExecutor executor = new MethodCommandExecutor(instance, method, annotation);
		register(plugin, annotation.name(), executor);
	}

	public void register(Plugin plugin, String name, CommandExecutor executor) {
		if (commands.containsKey(name)) {
			logger.log(Level.WARNING, "Tried to register command `{0}` which is already registered", name);
			return;
		}

		RegisteredCommand command = new RegisteredCommand(plugin, executor);
		commands.put(name, command);
	}

	public CommandOutcome execute(CommandSender cs, String name, String... args) {
		RegisteredCommand cmd = commands.get(name);
		if (cmd == null) {
			cs.sendMessage(INVALID_COMMAND);
			return CommandOutcome.NOT_HANDLED;
		}

		logger.info("Handling valid command");
		CommandOutcome outcome;
		try {
			outcome = cmd.execute(cs, args);
		} catch (Exception exc) {
			outcome = CommandOutcome.ERROR;
			logger.log(Level.WARNING, "Exception thrown in Event handler", exc);
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
		case UNSUPPORTED_SENDER:
			cs.sendMessage(UNSUPPORTED_SENDER_ERROR);
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
}

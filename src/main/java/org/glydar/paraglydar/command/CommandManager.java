package org.glydar.paraglydar.command;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.CommandExecutor.CommandOutcome;
import org.glydar.paraglydar.permissions.Permission;
import org.glydar.paraglydar.plugin.Plugin;

public class CommandManager {
	private ArrayList<RegisteredCommand> commands = new ArrayList<RegisteredCommand>();
	
	private static final String PERMISSION_ERROR = "Sorry, you do not have permission for this command.";
	private static final String INVALID_COMMAND = "Invalid command entered! Type /help for help!";
	private static final String ERROR_OCCURRED = "An error occurred! Try again in a bit, maybe.";
	
	public CommandManager(){}
	
	public void registerCommand(Plugin p, CommandExecutor ce, String name){
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
		
		if (parameterTypes[0] != CommandSender.class){
			logInvalidCommandMethod(m, "does not have 'CommandSender' as it's first paremeter");
			return false;
		}
		
		if (parameterTypes[1] != String[].class){
			logInvalidCommandMethod(m, "does not have 'String[]' as it's second paremeter");
			return false;
		}
		return true;
	}
	
	private void logInvalidCommandMethod(Object... args) {
		ParaGlydar.getLogger().log(Level.WARNING, "Command Method `{0}` {1}, skipping", args);
	}
	
	private void registerCommand(Plugin p, CommandExecutor ce, Method m, Command c){
		RegisteredCommand command = new RegisteredCommand(p, c.name(), ce, c.usage(), m);
		command.setPermission(new Permission(c.permission(), c.permissionDefault()));
		command.setMinMax(c.min(), c.max());
		command.setAliases(c.aliases());
		commands.add(command);
	}
	
	public void exec(CommandSender cs, String name, String[] args) {
		//Allows calling a command using: /plugin [commandname] if there is a collision of two commands
		for (Plugin p : ParaGlydar.getPluginLoader().getPlugins()){
			if (name.equalsIgnoreCase(p.getName()) && args.length > 0){
				String newName = args[0];
				String[] newArgs = Arrays.copyOfRange(args, 1, args.length - 1);
				execByPlugin(cs, name, newName, newArgs);
				return;
			}
		}
		
		//TODO: Currently only first command in array of commands is used
		for (RegisteredCommand cmd : commands){
			if (cmd.getCommandName().equalsIgnoreCase(name)){
				handleOutcome(cs, args, cmd);
				return;
			}
		}
		
		cs.sendMessage(INVALID_COMMAND);
	}
	
	private void execByPlugin(CommandSender cs, String pluginName, String name, String[] args){
		//TODO: Currently only first command in array of commands is used
		for (RegisteredCommand cmd : commands){
			if (cmd.getPlugin().getName().equalsIgnoreCase(pluginName)){
				if (cmd.getCommandName().equalsIgnoreCase(name)){
					handleOutcome(cs, args, cmd);
					return;
				}
			}
		}
		
		cs.sendMessage(INVALID_COMMAND);
	}
	
	//TODO: This feels a bit messy :P
	private void handleOutcome(CommandSender cs, String[] args, RegisteredCommand cmd){
		CommandOutcome outcome = null;
		boolean error = false;
		if (cs.hasPermission(cmd.getPermission())){
			if (validateArgsLength(args.length, cmd)){
				try{	
					CommandOutcome o = cmd.execute(cs, args);
					outcome = o;
				} catch (Exception exc) {
					error = true;
					ParaGlydar.getLogger().log(Level.WARNING,
							"Exception thrown in Event handler", exc);
				}
				if (error){
					outcome = CommandOutcome.ERROR;
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
			break;
		case FAILURE_OTHER:
			cs.sendMessage(ERROR_OCCURRED);
			break;
		}
	}
	
	private boolean validateArgsLength(int length, RegisteredCommand cmd){
		if (length < cmd.getMinArguments()){
			return false;
		}
		if (length > cmd.getMaxArguments()){
			return false;
		}
		
		return true;
	}
}

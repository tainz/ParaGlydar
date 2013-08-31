package org.glydar.paraglydar.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.CommandExecutor.CommandOutcome;
import org.glydar.paraglydar.event.manager.MethodEventExecutor.MethodEventExecutorException;
import org.glydar.paraglydar.permissions.Permission;
import org.glydar.paraglydar.plugin.Plugin;

public class RegisteredCommand {	
	private Plugin plugin;
	private CommandExecutor executor;
	private Permission perm;
	private String name;
	private String usage;
	private String[] aliases = new String[]{};
	private int min;
	private int max;
	private Method method;
	
	public RegisteredCommand(Plugin plugin, String name, CommandExecutor executor, String usage, Method method) {
		this.name = name;
		this.executor = executor;
		this.usage = usage;
		this.plugin = plugin;
		this.method = method;
	}
	
	public CommandOutcome execute(CommandSender cs, String[] args){
		CommandOutcome outcome;
		try {
			ParaGlydar.getLogger().info("Execute #1");
			CommandOutcome o = (CommandOutcome) method.invoke(executor, cs, args);
			ParaGlydar.getLogger().info("Execute #2");
			outcome = o;
			ParaGlydar.getLogger().info("Outcome: " + outcome.toString());
		} catch (IllegalAccessException | IllegalArgumentException exc) {
			outcome = CommandOutcome.ERROR;
			throw new MethodEventExecutorException(exc);
		} catch (InvocationTargetException exc) {
			outcome = CommandOutcome.ERROR;
			throw new MethodEventExecutorException(exc.getCause());
		}
		ParaGlydar.getLogger().info("Outcome again: " + outcome.toString());
		return outcome;
		
	}

	public String getCommandName() {
		return this.name;
	}

	public Permission getPermission() {
		return this.perm;
	}
	
	public RegisteredCommand setPermission(Permission perm) {
		this.perm = perm;
		return this;
	}

	public String getUsage() {
		return this.usage;
	}

	public RegisteredCommand setUsage(String usage) {
		this.usage = usage;
		return this;
	}

	public Plugin getPlugin() {
		return this.plugin;
	}

	public String[] getAliases() {
		return this.aliases;
	}

	public RegisteredCommand setAliases(String[] aliases) {
		if (aliases == null || aliases.length == 0) {
			return this;
		} else {
			this.aliases = aliases;
			return this;
		}
	}
	
	public void setMinMax(int min, int max){
		this.min = min;
		this.max = max;
	}
	
	public int getMinArguments(){
		return min;
	}
	
	public int getMaxArguments(){
		return max;
	}
}

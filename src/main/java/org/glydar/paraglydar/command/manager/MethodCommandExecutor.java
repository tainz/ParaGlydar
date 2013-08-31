package org.glydar.paraglydar.command.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.glydar.paraglydar.command.Command;
import org.glydar.paraglydar.command.CommandExecutor;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.command.CommandSet;
import org.glydar.paraglydar.permissions.Permission;

public class MethodCommandExecutor implements CommandExecutor {

	private final CommandSet instance;
	private final Method method;
	private final String usage;
	private final Permission permission;
	private final int min;
	private final int max;

	public MethodCommandExecutor(CommandSet instance, Method method, Command annotation) {
		this.instance = instance;
		this.method = method;
		this.permission = new Permission(annotation.permission(), annotation.permissionDefault());
		this.usage = annotation.usage();
		this.min = annotation.min();
		this.max = annotation.max();

		method.setAccessible(true);
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public Permission getPermission() {
		return permission;
	}

	@Override
	public int minArgs() {
		return min;
	}

	@Override
	public int maxArgs() {
		return max;
	}

	@Override
	public CommandOutcome execute(CommandSender sender, String[] args) {
		if (!method.getParameterTypes()[0].isAssignableFrom(sender.getClass())) {
			return CommandOutcome.UNSUPPORTED_SENDER;
		}

		CommandOutcome outcome;
		try {
			outcome = (CommandOutcome) method.invoke(instance, sender, args);
		} catch (IllegalAccessException | IllegalArgumentException exc) {
			outcome = CommandOutcome.ERROR;
			throw new MethodCommandExecutorException(exc);
		} catch (InvocationTargetException exc) {
			outcome = CommandOutcome.ERROR;
			throw new MethodCommandExecutorException(exc.getCause());
		}

		return outcome;
	}

	public static class MethodCommandExecutorException extends RuntimeException {

		private static final long serialVersionUID = -3382178682196430836L;

		public MethodCommandExecutorException(Throwable throwable) {
			super(throwable);
		}
	}
}

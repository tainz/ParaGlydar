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
	private final boolean hasRest;
	private final int min;
	private final int max;

	public MethodCommandExecutor(CommandSet instance, Method method, Command annotation) {
		this.instance = instance;
		this.method = method;
		this.permission = new Permission(annotation.permission(), annotation.permissionDefault());
		this.usage = annotation.usage();
		Class<?>[] parameterTypes = method.getParameterTypes();
		this.hasRest = parameterTypes[parameterTypes.length - 1] == String[].class;
		this.min = method.getParameterTypes().length - (hasRest ? 2 : 1);
		if (hasRest) {
			this.max = annotation.maxArgs();
		}
		else {
			this.max = min;
		}

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
			Object[] parameters;
			if (hasRest) {
				parameters = parametersWithRest(sender, args);
			}
			else {
				parameters = parameters(sender, args);
			}
			outcome = (CommandOutcome) method.invoke(instance, parameters);
		} catch (IllegalAccessException | IllegalArgumentException exc) {
			outcome = CommandOutcome.ERROR;
			System.out.println(exc);
			throw new MethodCommandExecutorException(exc);
		} catch (InvocationTargetException exc) {
			outcome = CommandOutcome.ERROR;
			System.out.println(exc);
			throw new MethodCommandExecutorException(exc.getCause());
		}

		return outcome;
	}

	private Object[] parameters(CommandSender sender, String[] args) {
		int parametersLength = method.getParameterTypes().length;
		Object[] parameters = new Object[parametersLength];
		parameters[0] = sender;
		System.arraycopy(args, 0, parameters, 1, args.length);
		return parameters;
	}

	private Object[] parametersWithRest(CommandSender sender, String[] args) {
		int parametersLength = method.getParameterTypes().length;
		int mandatoryArgsLength = parametersLength - 2;
		int restLength = args.length - mandatoryArgsLength;

		Object[] parameters = new Object[parametersLength];
		String[] rest = new String[restLength];

		parameters[0] = sender;
		System.arraycopy(args, 0, parameters, 1, mandatoryArgsLength);
		parameters[parametersLength - 1] = rest;
		System.arraycopy(args, mandatoryArgsLength, rest, 0, restLength);

		return parameters;
	}

	public static class MethodCommandExecutorException extends RuntimeException {

		private static final long serialVersionUID = -3382178682196430836L;

		public MethodCommandExecutorException(Throwable throwable) {
			super(throwable);
		}
	}
}

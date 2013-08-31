package org.glydar.paraglydar.command;

/**
 * @author YoshiGenius
 */
public interface CommandExecutor {

	public static enum CommandOutcome {
		SUCCESS, NO_PERMISSION, WRONG_USAGE, ERROR, NOT_HANDLED, FAILURE_OTHER;
	}

}
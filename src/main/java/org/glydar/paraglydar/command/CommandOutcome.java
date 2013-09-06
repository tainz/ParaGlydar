package org.glydar.paraglydar.command;

/**
 * Describes the result of the execution of a Command.
 */
public enum CommandOutcome {

	/**
	 * Everything went well
	 */
	SUCCESS,

	/**
	 * The sender does not have the permission to use this command
	 */
	NO_PERMISSION,

	/**
	 * Arguments did not fit the exepected format
	 * (args count, arg type, ...)
	 */
	WRONG_USAGE,

	/**
	 * This sender cannot use the command
	 */
	UNSUPPORTED_SENDER,

	/**
	 * An error occured
	 */
	ERROR,

	/**
	 * Something undocumented happened
	 */
	FAILURE_OTHER,

	/**
	 * The command does not exists
	 */
	NOT_HANDLED;
}
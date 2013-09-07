package org.glydar.paraglydar.command;

/**
 * Describes the result of the execution of a Command.
 * 
 * This must be the return type of any method invoked by a command. 
 */
public enum CommandOutcome {

	/**
	 * Everything went well.
	 */
	SUCCESS,

	/**
	 * The sender does not have the permission to use this command.
	 * They will recieve a "No Permission" message.
	 */
	NO_PERMISSION,

	/**
	 * Arguments did not fit the exepected format
	 * (args count, arg type, ...)
	 * They will be sent the Command's usage method.
	 * 
	 * @See {@link Command#usage()}
	 */
	WRONG_USAGE,

	/**
	 * This sender cannot use the command.
	 */
	UNSUPPORTED_SENDER,

	/**
	 * An error occured.
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
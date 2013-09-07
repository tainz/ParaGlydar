package org.glydar.paraglydar.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

/**
 * This annotation is used on top of methods that are invoked when a specific command is called.
 * EG. If you wanted a "/clans" command, you'd put this annotation on the method that runs every time someone types "/clans".
 * The command method must also be registered with the {@link CommandManager} instance.
 * @see {@link CommandManager#register}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	/**
	 * This is the name of the command typed. In the example above, it would be "clans".
	 * This also supports nested commands, ie "/clans add". In this case, put "clans" first, then "add".
	 * This must be set.
	 */
	public String[] name();

	/**
	 * These are aliases that can be used instead of the original command name.
	 * Note: In the case of nested commands, the aliases only apply to the last word. Ie. In the example above, they would only apply to "add".
	 */
	public String[] aliases() default {};

	/**
	 * This is the message sent when a {@link CommandSender} does not use a command correctly.
	 * More specifically, this is sent when the command method returns CommandOutcome.WRONG_USAGE.
	 * 
	 * @see {@link CommandOutcome#WRONG_USAGE}
	 */
	public String usage() default "";

	/**
	 * This option dictates the maximum amount of Arguments the {@link CommandSender} should use. 
	 * If they send more, they will receive the usage method above.
	 */
	int maxArgs() default Integer.MAX_VALUE;

	/**
	 * This is the name of the permission associated with this command.
	 * If set the {@link CommandSender} must have this permission to execute the command.
	 */
	public String permission() default "";
	
	/**
	 * This is the Permission Default for the above permissions
	 * 
	 * @see {@link PermissionDefault}
	 */
	public PermissionDefault permissionDefault() default PermissionDefault.ADMIN;
}
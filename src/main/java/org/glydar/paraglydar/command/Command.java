package org.glydar.paraglydar.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	public String name();

	public String[] aliases() default {};

	public String usage() default "";

	int min() default 0;

	int max() default Integer.MAX_VALUE;

	public String permission() default "";

	public PermissionDefault permissionDefault() default PermissionDefault.TRUE;
}
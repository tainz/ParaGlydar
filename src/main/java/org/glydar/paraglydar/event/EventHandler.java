package org.glydar.paraglydar.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Notes methods that handle events inside a listener
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

	EventPriority priority() default EventPriority.NORMAL;

	boolean ignoreCancelled() default false;
}

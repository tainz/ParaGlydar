package org.glydar.paraglydar.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to note methods that handle specific events. These methods will not be invoked otherwise.
 * 
 * All methods using this annotation must be contained in a {@link Listener}.
 * 
 * @see {@link Listener}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

	EventPriority priority() default EventPriority.NORMAL;

	boolean ignoreCancelled() default false;
}

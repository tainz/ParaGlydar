package org.glydar.paraglydar.event;

/**
 * An event listener class for use in plugins.
 *
 * Any methods that are called on execution of an event must be contained in a class
 * that implements this interface.
 * 
 * These method must be registered using the {@link org.glydar.paraglydar.event.manager.EventManager}
 * 
 * @see {@link org.glydar.paraglydar.event.EventManager#register}
 */
public interface Listener {
}

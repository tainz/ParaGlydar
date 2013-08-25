package org.glydar.paraglydar.event.manager;

import org.glydar.paraglydar.event.Event;

/**
 * Low-level callback called when an Event is fired.
 *
 * @param <E> Type of the expected Event
 */
public interface EventExecutor<E extends Event> {

	void execute(E event);
}

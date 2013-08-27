package org.glydar.paraglydar.event;

/**
 * Describes an Event which can be somehow cancelled
 * by a listener
 *
 * @author YoshiGenius
 */
public interface Cancellable {

	boolean isCancelled();

	void setCancelled(boolean cancelled);
}

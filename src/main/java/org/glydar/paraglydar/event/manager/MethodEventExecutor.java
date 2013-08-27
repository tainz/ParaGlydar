package org.glydar.paraglydar.event.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.event.Cancellable;
import org.glydar.paraglydar.event.Event;
import org.glydar.paraglydar.event.EventHandler;
import org.glydar.paraglydar.event.Listener;

/**
 * Implementation of {@link EventExecutor} for the annotation-based API
 */
public class MethodEventExecutor<E extends Event> implements EventExecutor<E> {

	private final Listener listener;
	private final Method method;
	private final boolean ignoreCancelled;

	public MethodEventExecutor(Class<E> eventClass, Listener listener, Method method, EventHandler annotation) {
		this.listener = listener;
		this.method = method;
		this.ignoreCancelled = annotation.ignoreCancelled();
	}

	public Listener getListener() {
		return listener;
	}

	@Override
	public void execute(E event) {
		if (ignoreCancelled && event instanceof Cancellable) {
			Cancellable cancellable = (Cancellable) event;
			if (cancellable.isCancelled()) {
				return;
			}
		}

		try {
			method.invoke(listener, event);
		} catch (IllegalAccessException | IllegalArgumentException exc) {
			throw new MethodEventExecutorException(exc);
		} catch (InvocationTargetException exc) {
			throw new MethodEventExecutorException(exc.getCause());
		} catch (Exception e) {
			//TODO: Catch plugin exceptions properly!
		}
	}

	public static class MethodEventExecutorException extends RuntimeException {

		private static final long serialVersionUID = 1694598385544729424L;

		public MethodEventExecutorException(Throwable throwable) {
			super(throwable);
		}
	}
}

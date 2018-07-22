package me.davidllorca.diworkshop.common;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base class for observable entities in the application
 *
 * @param <ListenerClass> the class of the listeners.
 */
public abstract class BaseObservable<ListenerClass> {

    private Set<ListenerClass> mListeners = Collections.newSetFromMap(
            new ConcurrentHashMap<ListenerClass, Boolean>(1)
    );

    public final void registerListener(ListenerClass listenerClass) { mListeners.add(listenerClass);}

    public final void unregisterListener(ListenerClass listenerClass) { mListeners.remove(listenerClass);}

    /**
     * Get a reference to the unmodifiable set containing all th registered listeners.*
     */
    protected final Set<ListenerClass> getListeners() {
        return Collections.unmodifiableSet(mListeners);
    }

}

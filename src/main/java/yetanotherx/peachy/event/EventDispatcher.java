package yetanotherx.peachy.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import yetanotherx.peachy.exception.EventErrorException;

public class EventDispatcher {
    
    protected HashMap<String, ArrayList<EventDispatcherCallback<?>>> listeners = new HashMap<String, ArrayList<EventDispatcherCallback<?>>>();
    
    public void connect(String name, EventDispatcherCallback<?> callback) {
        if( !listeners.containsKey(name) ) {
            listeners.put(name, new ArrayList<EventDispatcherCallback<?>>());
        }
        listeners.get(name).add(callback);
    }
    
    public void disconnect(String name, Method listener) {
        if( !listeners.containsKey(name) ) {
            return;
        }
        
        listeners.remove(name);
        
    }
    
    public Event notify(Event event) {
        ArrayList<EventDispatcherCallback<?>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( EventDispatcherCallback<?> callback : list ) {
            try {
                callback.invoke(event);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\" (" + ex.getMessage() + ")");
            }
        }
        return event;
    }
    
    public Event notifyUntil(Event event) {
        ArrayList<EventDispatcherCallback<?>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( EventDispatcherCallback<?> callback : list ) {
            try {
                if( callback.invoke(event) != null ) {
                    event.setProcessed(true);
                    return event;
                }
            } catch (Exception ex) {
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\" (" + ex.getMessage() + ")");
            }
        }
        return event;
    }
    
    public Event filter(Event event, Object value) {
        ArrayList<EventDispatcherCallback<?>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( EventDispatcherCallback<?> callback : list ) {
            try {
                Object out = callback.invoke(event);
                event.setReturned(out);
            } catch (Exception ex) {
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\" (" + ex.getMessage() + ")");
            }
        }
        return event;
    }
    
    public Boolean hasListeners(String name) {
        if( !listeners.containsKey(name) ) {
            return false;
        }
        return !listeners.get(name).isEmpty();
    }
    
    public ArrayList<EventDispatcherCallback<?>> getListeners(String name) {
        if( !listeners.containsKey(name) ) {
            return new ArrayList<EventDispatcherCallback<?>>();
        }
        return listeners.get(name);
    }
    
}

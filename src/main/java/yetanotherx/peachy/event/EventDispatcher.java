package yetanotherx.peachy.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import yetanotherx.peachy.exception.EventErrorException;
import yetanotherx.peachy.util.collections.TwoValueCollection;

public class EventDispatcher {
    
    protected HashMap<String, ArrayList<TwoValueCollection<Object, Method>>> listeners = new HashMap<String, ArrayList<TwoValueCollection<Object, Method>>>();
    
    public void connect(String name, Object obj, Method listener) {
        if( !listeners.containsKey(name) ) {
            listeners.put(name, new ArrayList<TwoValueCollection<Object, Method>>());
        }
        listeners.get(name).add(new TwoValueCollection<Object, Method>(obj, listener));
    }
    
    public void disconnect(String name, Method listener) {
        if( !listeners.containsKey(name) ) {
            return;
        }
        
        listeners.remove(name);
        
    }
    
    public Event notify(Event event) {
        ArrayList<TwoValueCollection<Object, Method>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( TwoValueCollection<Object, Method> callback : list ) {
            try {
                callback.getSecond().invoke(callback.getFirst(), new Object[] {event} );
            } catch (Exception ex) {
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\"");
            }
        }
        return event;
    }
    
    public Event notifyUntil(Event event) {
        ArrayList<TwoValueCollection<Object, Method>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( TwoValueCollection<Object, Method> callback : list) {
            try {
                if( callback.getSecond().invoke(callback.getFirst(), new Object[] {event} ) != null ) {
                    event.setProcessed(true);
                    return event;
                }
            } catch (Exception ex) {
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\"");
            }
        }
        return event;
    }
    
    public Event filter(Event event, Object value) {
        ArrayList<TwoValueCollection<Object, Method>> list = listeners.get(event.getName());
        if( list == null ) return event;
        
        for( TwoValueCollection<Object, Method> callback : list) {
            try {
                Object out = callback.getSecond().invoke(callback.getFirst(), new Object[] {event, value} );
                event.setReturned(out);
            } catch (Exception ex) {
                throw new EventErrorException("Could not notify event \"" + event.getName() + "\"");
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
    
    public ArrayList<TwoValueCollection<Object, Method>> getListeners(String name) {
        if( !listeners.containsKey(name) ) {
            return new ArrayList<TwoValueCollection<Object, Method>>();
        }
        return listeners.get(name);
    }
    
}

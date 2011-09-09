package yetanotherx.peachy.util;

import java.util.Collection;
import java.util.HashMap;
import yetanotherx.peachy.config.BaseConfiguration;
import yetanotherx.peachy.event.Event;
import yetanotherx.peachy.event.EventDispatcher;
import yetanotherx.peachy.event.EventDispatcherCallback;
import yetanotherx.peachy.exception.UnsetParameterException;

public class PeachyContext {

    protected EventDispatcher dispatcher;
    protected BaseConfiguration configuration;
    protected static HashMap<String, PeachyContext> instances = new HashMap<String, PeachyContext>();

    public PeachyContext(EventDispatcher dispatcher, BaseConfiguration configuration) {
        this.dispatcher = dispatcher;
        this.configuration = configuration;
    }

    public static void createInstance(String name, BaseConfiguration conf) {
        instances.put(name, new PeachyContext(new EventDispatcher(), conf));
    }

    public static Collection<PeachyContext> getInstances() {
        return instances.values();
    }

    public static PeachyContext getInstance() {
        return getInstance(instances.keySet().iterator().next());
    }

    public static PeachyContext getInstance(String name) {
        if (!instances.containsKey(name)) {
            throw new UnsetParameterException("The \"" + name + "\" context does not exist");
        }

        return instances.get(name);
    }

    public static boolean hasInstance() {
        return hasInstance(instances.keySet().iterator().next());
    }

    public static boolean hasInstance(String name) {
        return instances.containsKey(name);
    }

    public BaseConfiguration getConfiguration() {
        return configuration;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public void dispatch() {
        this.getDispatcher().notify(new Event(this, "peachy.context_dispatch"));
    }

}

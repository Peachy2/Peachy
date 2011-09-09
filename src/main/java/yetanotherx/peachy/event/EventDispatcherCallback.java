package yetanotherx.peachy.event;

public class EventDispatcherCallback<T> {
    
    protected T object;
    protected Class<T> class_inst;
    protected String method;
    protected Class<?>[] parameters;

    @SuppressWarnings("unchecked")
    public EventDispatcherCallback(T object, String method, Class<?> ... parameters) {
        this.object = object;
        this.class_inst = (Class<T>) object.getClass();
        this.method = method;
        this.parameters = parameters;
    }

    public EventDispatcherCallback(Class<T> class_inst, String method, Class<?> ... parameters) {
        this.class_inst = class_inst;
        this.method = method;
        this.parameters = parameters;
    }
    
    public Object invoke(Event event) throws Exception {
        if( parameters.length != 0 ) {
            return class_inst.getMethod(method, parameters).invoke(object, event);
        }
        else {
            return class_inst.getMethod(method, parameters).invoke(object);
        }
    }
    
}

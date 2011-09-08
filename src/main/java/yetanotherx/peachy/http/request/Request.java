package yetanotherx.peachy.http.request;

import java.util.HashMap;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public abstract class Request {

    protected EventDispatcher dispatcher;
    protected String content = "";
    protected RequestType method;
    protected HashMap<String, String> parameters;
    protected ConfigNode options;

    public void initialize(EventDispatcher dispatcher, HashMap<String, String> parameters, ConfigNode options) {
        this.dispatcher = dispatcher;
        this.options = options;
        this.parameters = parameters;
        this.method = RequestType.GET;
    }

    public ConfigNode getOptions() {
        return options;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public RequestType getMethod() {
        return method;
    }

    public void setMethod(RequestType method) {
        this.method = method;
    }
    
}

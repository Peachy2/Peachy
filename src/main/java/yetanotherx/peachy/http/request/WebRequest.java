package yetanotherx.peachy.http.request;

import java.util.HashMap;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class WebRequest extends Request {

    public WebRequest(EventDispatcher dispatcher) {
        this.initialize(dispatcher, new HashMap<String, String>(), new ConfigNode());
    }

    public WebRequest(EventDispatcher dispatcher, HashMap<String, String> parameters) {
        this.initialize(dispatcher, parameters, new ConfigNode());
    }

    public WebRequest(EventDispatcher dispatcher, HashMap<String, String> parameters, ConfigNode options) {
        this.initialize(dispatcher, parameters, options);
    }    
    
}

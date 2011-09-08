package yetanotherx.peachy.http.response;

import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class WebResponse extends Response {
    
    public WebResponse(EventDispatcher dispatcher) {
        this.initialize(dispatcher, new ConfigNode());
    }

    public WebResponse(EventDispatcher dispatcher, ConfigNode options) {
        this.initialize(dispatcher, options);
    }
    
}

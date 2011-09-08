package yetanotherx.peachy.http.response;

import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public abstract class Response {
    
    protected EventDispatcher dispatcher;
    protected ConfigNode options;
    protected Integer code;
    protected String contentType;
    protected String content;

    public void initialize(EventDispatcher dispatcher, ConfigNode options) {
        this.dispatcher = dispatcher;
        this.options = options;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDispatcher(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getContent() {
        return content;
    }

    public ConfigNode getOptions() {
        return options;
    }

    public Integer getHTTPCode() {
        return code;
    }

    public String getContentType() {
        return contentType;
    }
    
    
    
}

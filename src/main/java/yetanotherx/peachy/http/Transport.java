package yetanotherx.peachy.http;

import yetanotherx.peachy.http.request.Request;
import yetanotherx.peachy.http.response.Response;

public abstract class Transport {
    
    protected Request request;
    
    public abstract Response sendURL(Request request);

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
}

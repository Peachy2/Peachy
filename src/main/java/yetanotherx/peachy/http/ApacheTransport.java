package yetanotherx.peachy.http;

import yetanotherx.peachy.http.request.Request;
import yetanotherx.peachy.http.response.Response;

public class ApacheTransport extends Transport {

    @Override
    public Response sendURL(Request request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

package yetanotherx.peachy.debug.logging;

import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class ConsoleLogger extends StreamLogger {

    public ConsoleLogger(EventDispatcher dispatcher, ConfigNode options) {
        super(dispatcher, options);
    }

    public ConsoleLogger(EventDispatcher dispatcher) {
        super(dispatcher);
    }
    
    
    
}

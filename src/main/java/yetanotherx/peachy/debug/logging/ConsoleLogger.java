package yetanotherx.peachy.debug.logging;

import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class ConsoleLogger extends StreamLogger {

    public ConsoleLogger(EventDispatcher dispatcher, ConfigNode options) {
        super(dispatcher, options);
        this.initializeConsole(dispatcher, options);
    }

    public ConsoleLogger(EventDispatcher dispatcher) {
        super(dispatcher);
        this.initializeConsole(dispatcher, new ConfigNode());
    }

    public final void initializeConsole(EventDispatcher dispatcher, ConfigNode options) {
        this.setStream(System.out);
        super.initializeCore(dispatcher, options);
    }
    
}

package yetanotherx.peachy.debug.logging;

import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class NoLogger extends PeachyLogger {

    public NoLogger(EventDispatcher dispatcher) {
        this.initializeCore(dispatcher, new ConfigNode());
    }
    
    public NoLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initializeCore(dispatcher, options);
    }

    @Override
    protected void doLog(String message, Level level) {
    }
    
}

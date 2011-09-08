package yetanotherx.peachy.debug.logging;

import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class FileLogger extends PeachyLogger {
    
    public FileLogger(EventDispatcher dispatcher) {
        this.initialize(dispatcher, new ConfigNode());
    }
    
    public FileLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initialize(dispatcher, options);
    }

    @Override
    protected void doLog(String message, Level level) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

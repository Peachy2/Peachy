package yetanotherx.peachy.debug.logging;

import java.util.ArrayList;
import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class AggregateLogger extends PeachyLogger {
    
    protected ArrayList<PeachyLogger> loggers = new ArrayList<PeachyLogger>();
    
    public AggregateLogger(EventDispatcher dispatcher) {
        this.initializeAggregate(dispatcher, new ConfigNode());
    }
    
    public AggregateLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initializeAggregate(dispatcher, options);
    }

    public final void initializeAggregate(EventDispatcher dispatcher, ConfigNode options) {
        this.dispatcher = dispatcher;
        this.options = options;
        
        if( this.options.hasProperty("loggers") ) {
            for( ConfigNode node : this.options.getNodes("loggers").values() ) {
                this.loggers.add(PeachyLogger.createLogger(this.dispatcher, node));
            }
        }
        
        super.initializeCore(dispatcher, options);
    }

    public ArrayList<PeachyLogger> getLoggers() {
        return loggers;
    }

    public void setLoggers(ArrayList<PeachyLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    protected void doLog(String message, Level level) {
        for( PeachyLogger log : this.loggers ) {
            log.log(message, level);
        }
    }

    @Override
    public void shutdown() {
        for( PeachyLogger log : this.loggers ) {
            log.shutdown();
        }
    }
    
    
    
}

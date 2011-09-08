package yetanotherx.peachy.debug.logging;

import java.util.ArrayList;
import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;
import yetanotherx.peachy.exception.InvalidArgumentException;

public class AggregateLogger extends PeachyLogger {
    
    protected ArrayList<PeachyLogger> loggers;
    
    public AggregateLogger(EventDispatcher dispatcher) {
        this.initialize(dispatcher, new ConfigNode());
    }
    
    public AggregateLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initialize(dispatcher, options);
    }

    @Override
    public final void initialize(EventDispatcher dispatcher, ConfigNode options) {
        this.dispatcher = dispatcher;
        this.options = options;
        
        if( this.options.hasProperty("loggers") ) {
            for( ConfigNode node : this.options.getNodes("loggers").values() ) {
                try {
                    this.loggers.add((PeachyLogger)Class.forName("yetanotherx.peachy.debug.logging." + node.getString("class", "NoLogger")).newInstance());
                } catch (Exception ex) {
                    throw new InvalidArgumentException("No such logger with the name " +  node.getString("class", "NOT GIVEN") + " found!");
                }
            }
        }
        
        super.initialize(dispatcher, options);
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

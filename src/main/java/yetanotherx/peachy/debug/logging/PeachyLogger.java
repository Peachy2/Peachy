package yetanotherx.peachy.debug.logging;

import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public abstract class PeachyLogger {
    
    protected EventDispatcher dispatcher;
    protected ConfigNode options;
    protected Level level = Level.INFO;
    
    public static PeachyLogger createLogger(EventDispatcher dispatcher, ConfigNode conf) {
        LoggerType type = LoggerType.valueOf(conf.getString("type", "NONE").toUpperCase());
        ConfigNode loggingConf = conf;
        
        PeachyLogger logger = null;
        
        switch (type) {
            case AGGREGATE:
                logger = new AggregateLogger(dispatcher, loggingConf);
                break;
            case CONSOLE:
                logger = new ConsoleLogger(dispatcher, loggingConf);
                break;
            case FILE:
                logger = new FileLogger(dispatcher, loggingConf);
                break;
            case NONE:
                logger = new NoLogger(dispatcher, loggingConf);
                break;
            case STREAM:
                logger = new StreamLogger(dispatcher, loggingConf);
                break;
        }
        
        return logger;
    }
    
    public void initializeCore(EventDispatcher dispatcher, ConfigNode options) {
        
        this.dispatcher = dispatcher;
        this.options = options;
        
        if( this.options.hasProperty("level") ) {
            this.setLogLevel(Level.parse(this.options.getString("level", "INFO").toUpperCase()));
        }
        
        if( this.options.getBoolean("auto_shutdown", true) == true ) {
            Runtime.getRuntime().addShutdownHook(new LoggingShutdownThread(this));
        }
    }

    public ConfigNode getOptions() {
        return options;
    }

    public Level getLogLevel() {
        return level;
    }

    public void setLogLevel(Level level) {
        this.level = level;
    }
    
    public void setLogLevel(String level) {
        this.level = Level.parse(level);
    }
    
    public void log(String message) {
        this.log(message, Level.INFO);
    }
    
    public void log(String message, String level) {
        this.log(message, Level.parse(level));
    }
    
    public void log(String message, Level level) {
        if( this.getLogLevel().intValue() > level.intValue() ) {
            return;
        }
        this.doLog(message, level);
    }
    
    protected abstract void doLog(String message, Level level);
    
    public void severe(String message) {
        this.log(message, Level.SEVERE);
    }
    
    public void warning(String message) {
        this.log(message, Level.WARNING);
    }
    
    public void info(String message) {
        this.log(message, Level.INFO);
    }
    
    public void fine(String message) {
        this.log(message, Level.FINE);
    }
    
    public void finer(String message) {
        this.log(message, Level.FINER);
    }
    
    public void finest(String message) {
        this.log(message, Level.FINEST);
    }
    
    public void shutdown() {
        
    }
    
}

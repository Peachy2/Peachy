package yetanotherx.peachy.config;

import java.util.Map;
import yetanotherx.peachy.debug.logging.*;
import yetanotherx.peachy.event.EventDispatcher;
import yetanotherx.peachy.http.Transport;

public abstract class BaseConfiguration {

    protected PeachyLogger logger;
    protected EventDispatcher dispatcher;
    protected Transport transport;
    protected String configFile;
    protected Map<String, Object> configBaseNode;
    protected ConfigNode conf;

    protected void initialize(String configFile) {
        this.configFile = configFile;
        this.loadConfigFile();
        this.dispatcher = new EventDispatcher();
        this.conf = new ConfigNode();
        this.conf.root = this.configBaseNode;
        
        LoggerType type = LoggerType.valueOf(conf.getString("core.logging.type", "NONE").toUpperCase());
        ConfigNode loggingConf = conf.getNode("core.logging");
        
        switch (type) {
            case AGGREGATE:
                this.logger = new AggregateLogger(this.dispatcher, loggingConf);
                break;
            case CONSOLE:
                this.logger = new ConsoleLogger(this.dispatcher, loggingConf);
                break;
            case FILE:
                this.logger = new FileLogger(this.dispatcher, loggingConf);
                break;
            case NONE:
                this.logger = new NoLogger(this.dispatcher, loggingConf);
                break;
            case STREAM:
                this.logger = new StreamLogger(this.dispatcher, loggingConf);
                break;
        }
    }

    protected abstract void loadConfigFile();

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public PeachyLogger getLogger() {
        return logger;
    }

    public void setLogger(PeachyLogger logger) {
        this.logger = logger;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public ConfigNode getConf() {
        return conf;
    }

    public void setConf(ConfigNode conf) {
        this.conf = conf;
    }
}

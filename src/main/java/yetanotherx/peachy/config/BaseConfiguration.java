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
        
        this.logger = PeachyLogger.createLogger(this.dispatcher, this.conf.getNode("core.logging"));
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

package yetanotherx.peachy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import yetanotherx.peachy.exception.ConfigurationException;

public class JavaConfig extends BaseConfiguration {

    public JavaConfig(String configFile) {
        this.initialize(configFile);
    }

    @Override
    protected void loadConfigFile() {
        File file = new File(this.configFile);
        if (!file.exists()) {
            throw new ConfigurationException("No such config file found (" + this.configFile + ")");
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            this.configBaseNode = (Map<String, Object>) inputStream.readObject();
        } catch (Exception ex) {
            throw new ConfigurationException("Could not read from configuration file (" + ex.getMessage() + ")");
        }
        
        if( this.configBaseNode == null ) {
            throw new ConfigurationException("Configuration file does not contain a base node object.");
        }
        
    }
}

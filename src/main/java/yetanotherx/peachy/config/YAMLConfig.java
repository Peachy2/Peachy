package yetanotherx.peachy.config;

import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import yetanotherx.peachy.exception.ConfigurationException;

public class YAMLConfig extends BaseConfiguration {

    public YAMLConfig(String configFile) {
        this.initialize(configFile);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void loadConfigFile() {
        File file = new File(this.configFile);
        if (!file.exists()) {
            throw new ConfigurationException("No such config file found (" + this.configFile + ")");
        }
        
        try {
            this.configBaseNode = (Map<String, Object>) new Yaml().load(Files.newReader(file, Charset.defaultCharset()));
            if( this.configBaseNode == null ) {
                throw new ConfigurationException("Could not parse JSON file (NPE)");
            }
        } catch (Exception ex) {
            throw new ConfigurationException("Could not parse JSON file (" + ex.getMessage() + ")");
        }
        
    }
    
    
    
}

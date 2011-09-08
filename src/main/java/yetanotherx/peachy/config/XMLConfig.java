package yetanotherx.peachy.config;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.simple.parser.JSONParser;
import yetanotherx.peachy.exception.ConfigurationException;

public class XMLConfig extends BaseConfiguration {

    public XMLConfig(String configFile) {
        this.initialize(configFile);
    }

    @Override
    protected void loadConfigFile() {
        File file = new File(this.configFile);
        if (!file.exists()) {
            throw new ConfigurationException("No such config file found (" + this.configFile + ")");
        }

        Document doc;
        try {
            doc = new SAXBuilder().build(file);
        } catch (Exception ex) {
            throw new ConfigurationException("Could not parse XML file (" + ex.getMessage() + ")");
        }

        this.configBaseNode = new HashMap<String, Object>();
        
        childRecurse(doc.getRootElement().getChildren(), this.configBaseNode);
        
        if (this.configBaseNode == null) {
            throw new ConfigurationException("Could not parse JSON file (NPE)");
        }


    }

    @SuppressWarnings("unchecked")
    private void childRecurse(List<?> children, Map<String, Object> map) {
        for (Object child : children) {
            Element chel = ((Element) child);
            if (chel.getChildren().isEmpty()) {
                map.put(chel.getName(), chel.getText());
            } else {
                map.put(chel.getName(), new HashMap<String, Object>());
                childRecurse(chel.getChildren(), (Map<String, Object>) map.get(chel.getName()));
            }
        }
    }
}

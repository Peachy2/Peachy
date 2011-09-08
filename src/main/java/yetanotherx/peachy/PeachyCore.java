package yetanotherx.peachy;

import yetanotherx.peachy.config.BaseConfiguration;
import yetanotherx.peachy.config.ConfigType;
import yetanotherx.peachy.config.JSONConfig;
import yetanotherx.peachy.config.JavaConfig;
import yetanotherx.peachy.config.XMLConfig;
import yetanotherx.peachy.config.YAMLConfig;
import yetanotherx.peachy.util.collections.DoubleValueHashMap;
import yetanotherx.peachy.util.PeachyContext;
import yetanotherx.peachy.util.collections.ThreeValueCollection;

public abstract class PeachyCore {

    protected DoubleValueHashMap<String, ConfigType, String> configs = new DoubleValueHashMap<String, ConfigType, String>();
    protected String[] args;
    
    public void load() {
        this.configure();

        BaseConfiguration conf = null;

        for (ThreeValueCollection<String, ConfigType, String> collection : configs) {
            switch (collection.getSecond()) {
                case JSON:
                    conf = new JSONConfig(collection.getThird());
                    break;
                case JAVA:
                    conf = new JavaConfig(collection.getThird());
                    break;
                case XML:
                    conf = new XMLConfig(collection.getThird());
                    break;
                case YAML:
                    conf = new YAMLConfig(collection.getThird());
                    break;
            }


            PeachyContext.createInstance(collection.getFirst(), conf);
        }
        
        for( PeachyContext context : PeachyContext.getInstances() ) {
            context.dispatch();
        }
    
    }

    public void setConfig(String name, ConfigType type, String file) {
        this.configs.set(file, type, file);
    }
    
    public abstract void configure();
    
    public static void startMain(Class<?> main, String[] args) {
        try {
            PeachyCore inst = (PeachyCore) main.newInstance();
            inst.args = args;
            inst.load();
        }
        catch( Exception e ) {
            System.out.println("ERROR: Could not start Peachy!");
            e.printStackTrace();
        }
    }

}

package yetanotherx.peachy;

import yetanotherx.peachy.config.ConfigType;

public class ExampleScript extends PeachyCore {

    public static void main(String[] args) {
        startMain(ExampleScript.class, args);
    }
    
    @Override
    public void configure() {
        this.setConfig("wiki", ConfigType.YAML, "/Development/config.yml");
    }
}

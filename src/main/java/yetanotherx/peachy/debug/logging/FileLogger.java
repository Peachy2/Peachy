package yetanotherx.peachy.debug.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;
import yetanotherx.peachy.exception.PeachyException;

public class FileLogger extends StreamLogger {

    public FileLogger(EventDispatcher dispatcher, ConfigNode options) {
        super(dispatcher, options);
    }

    public FileLogger(EventDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void initialize(EventDispatcher dispatcher, ConfigNode options) {
        try {
            this.setStream(new FileOutputStream(new File("PEACHYTODO")));
        } catch (FileNotFoundException ex) {
            throw new PeachyException("Internal stream error - " + ex.getMessage());
        }
    }
    
}

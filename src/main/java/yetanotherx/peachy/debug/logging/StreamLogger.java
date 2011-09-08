package yetanotherx.peachy.debug.logging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class StreamLogger extends PeachyLogger {

    protected Writer writer;

    public StreamLogger(EventDispatcher dispatcher) {
        this.initialize(dispatcher, new ConfigNode());
    }
    
    public StreamLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initialize(dispatcher, options);
    }
    
    public Writer getStream() {
        return writer;
        
    }

    public void setStream(OutputStream stream) {
        this.writer = new BufferedWriter(new OutputStreamWriter(stream));
        //PEACHYTODO - Close streams!
    }

    @Override
    protected void doLog(String message, Level level) {
        try {
            this.getStream().write(message);
            this.getStream().write("\n");
            this.getStream().flush();
        } catch (IOException ex) {
        }
    }
    
}

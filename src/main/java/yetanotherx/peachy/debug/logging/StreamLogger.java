package yetanotherx.peachy.debug.logging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;

public class StreamLogger extends PeachyLogger {

    protected Writer writer;

    public StreamLogger(EventDispatcher dispatcher) {
        this.initializeStream(dispatcher, new ConfigNode());
    }
    
    public StreamLogger(EventDispatcher dispatcher, ConfigNode options) {
        this.initializeStream(dispatcher, options);
    }

    public final void initializeStream(EventDispatcher dispatcher, ConfigNode options) {
        
        //PEACHYTODO - use an event, maybe?
        
        super.initializeCore(dispatcher, options);
    }
    
    public Writer getStream() {
        return writer;
        
    }

    public void setStream(OutputStream stream) {
        this.writer = new BufferedWriter(new OutputStreamWriter(stream));
    }

    @Override
    protected void doLog(String message, Level level) {
        try {
            this.getStream().append(message);
            this.getStream().append("\n");
            this.getStream().flush();
        } catch (IOException ex) {
        }
    }

    @Override
    public void shutdown() {
        if( this.writer != null ) {
            try {
                this.writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
    
    
}

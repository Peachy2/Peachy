package yetanotherx.peachy.debug.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import yetanotherx.peachy.config.ConfigNode;
import yetanotherx.peachy.event.EventDispatcher;
import yetanotherx.peachy.exception.ConfigurationException;
import yetanotherx.peachy.exception.PeachyException;

public class FileLogger extends StreamLogger {

    protected String format = "%time% [%priority%] %message%";
    protected String timeFormat = "MMM dd yyyy HH:mm:ss";
    protected SimpleDateFormat formatter;

    public FileLogger(EventDispatcher dispatcher, ConfigNode options) {
        super(dispatcher, options);
        this.initializeFile(dispatcher, options);
    }

    public FileLogger(EventDispatcher dispatcher) {
        super(dispatcher);
        this.initializeFile(dispatcher, new ConfigNode());
    }
    
    public final void initializeFile(EventDispatcher dispatcher, ConfigNode options) {

        this.dispatcher = dispatcher;
        this.options = options;

        if (!this.options.hasProperty("file")) {
            throw new ConfigurationException("You must pass a file: parameter when logging to file.");
        }

        this.format = this.options.getString("format", "%time% [%priority%] %message%");

        this.timeFormat = this.options.getString("time_format", "MMM dd yyyy HH:mm:ss");
        formatter = new SimpleDateFormat(this.timeFormat);

        if (this.options.hasProperty("auto_shutdown")) {
            Runtime.getRuntime().addShutdownHook(new LoggingShutdownThread(this));
        }

        File file = new File(this.options.getString("file"));

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new ConfigurationException("Could not create logging file " + file.getAbsolutePath());
            }
        }

        try {
            this.setStream(new FileOutputStream(file, true));
        } catch (FileNotFoundException ex) {
            throw new PeachyException("Internal stream error - " + ex.getMessage());
        }

        super.initializeStream(dispatcher, options);
    }

    @Override
    protected void doLog(String message, Level level) {
        message = this.format.replaceAll("%time%", formatter.format(new Date())).replaceAll("%priority%", level.getName()).replaceAll("%message%", message);
        super.doLog(message, level);
    }
}

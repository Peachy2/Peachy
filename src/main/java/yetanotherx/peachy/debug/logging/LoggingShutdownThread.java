package yetanotherx.peachy.debug.logging;

class LoggingShutdownThread extends Thread {

    public PeachyLogger log;
    
    public LoggingShutdownThread(PeachyLogger log) {
        this.log = log;
    }

    @Override
    public void run() {
        log.shutdown();
    }
    
    
    
}

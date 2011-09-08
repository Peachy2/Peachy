package yetanotherx.peachy.debug;

public class Timer {
    
    protected long startTime = 0;
    protected long totalTime = 0;
    protected String name;
    protected int calls = 0;

    public Timer(String name) {
        this.name = name;
        this.startTimer();
    }

    public final void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    public long addTime() {
        long spend = System.currentTimeMillis() - this.startTime;
        this.totalTime += spend;
        this.calls++;
        return spend;
    }

    public int getCalls() {
        return calls;
    }

    public long getElapsedTime() {
        if( totalTime == 0 ) {
            this.addTime();
        }
        return totalTime;
    }
    
    
    
}

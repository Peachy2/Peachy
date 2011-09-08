package yetanotherx.peachy.debug;

import java.util.HashMap;

public class TimerManager {
    
    public static HashMap<String, Timer> timers = new HashMap<String, Timer>();
    
    public static Timer getTimer(String name) {
        if( !timers.containsKey(name) ) {
            timers.put(name, new Timer(name));
        }
        timers.get(name).startTimer();
        return timers.get(name);
    }

    public static HashMap<String, Timer> getTimers() {
        return timers;
    }
    
    public static void clearTimers() {
        timers.clear();
    }
}

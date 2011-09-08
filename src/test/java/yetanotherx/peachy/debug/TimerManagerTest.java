package yetanotherx.peachy.debug;

import junit.framework.TestCase;

public class TimerManagerTest extends TestCase {

    public void testTimerManager() {
        Timer timerA = TimerManager.getTimer("timerA");
        Timer timerB = TimerManager.getTimer("timerB");
        assertTrue(timerA instanceof Timer);
        assertEquals(TimerManager.getTimers().size(), 2);
        assertEquals(timerA, TimerManager.getTimers().get("timerA"));
        TimerManager.clearTimers();
        assertEquals(TimerManager.getTimers().size(), 0);
    }
    
}
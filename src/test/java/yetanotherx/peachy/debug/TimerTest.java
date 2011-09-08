package yetanotherx.peachy.debug;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class TimerTest extends TestCase {

    public void testTimer() {
        Timer instance = new Timer("addTimeTimer");
        instance.addTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.addTime();
        assertEquals(instance.getCalls(), 2);
        assertTrue(instance.getElapsedTime() > 0);
    }
    
}

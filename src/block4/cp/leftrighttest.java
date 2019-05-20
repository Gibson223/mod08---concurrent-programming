package block4.cp;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class leftrighttest {
    private static final int THREAD_COUNT = 2;
    private  static final int DO_TIMES = 100;
    private static final LeftRightDeadlock leftright = new LeftRightDeadlock();


    @Test
    @Threaded(count = THREAD_COUNT)
    public void doAdd(@ThreadNumber int threadnr) throws InterruptedException {
        for (int i = 0; i < DO_TIMES; i++) {
            if (threadnr == 0) {
                leftright.leftRight();
            } else {
                leftright.rightLeft();
            }
        }
    }
    @After
    public void afterTest() {
        int x = leftright.num;
        System.out.println(x);
        assert(x == (THREAD_COUNT * DO_TIMES));
    }
}

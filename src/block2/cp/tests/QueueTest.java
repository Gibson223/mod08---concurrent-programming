package block2.cp.tests;

import block2.cp.queue.QueueEmptyException;
import block2.cp.queue.SafeMyQueue;
import block2.cp.queue.MyQueue;
import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class QueueTest {

    private static final int THREAD_COUNT = 4;
    private  static final int DO_TIMES = 100;

    private final SafeMyQueue queue = new SafeMyQueue();

    @Test
    @Threaded(count = THREAD_COUNT)
    public void doAdd() {
        for (int i = 0; i < DO_TIMES; i++) {
            queue.push(new Object());
        }
        for (int i = 0; i < DO_TIMES; i++) {
            try {
                queue.pull();
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void afterTest() {
        System.out.println(queue.getLength());
        assert(queue.getLength() == 0);
    }

}

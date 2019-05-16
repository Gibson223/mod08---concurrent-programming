package block3.cp.locks;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class dekkertest {
    private static final int THREAD_COUNT = 2;
    private  static final int DO_TIMES = 100;
//    private final unsafesequence sequence = new unsafesequence(new dekker1());
//    private final unsafesequence sequence = new unsafesequence(new CompareAndSet());
//    private final unsafesequence sequence = new unsafesequence(new ReCompare());
    private final unsafesequence sequence = new unsafesequence(new Reent());

    @Test
    @Threaded(count = THREAD_COUNT)
    public void doAdd(@ThreadNumber int threadnr) {
        for (int i = 0; i < DO_TIMES; i++) {
            sequence.getNext(threadnr);
//              sequence.doublelock(threadnr);
        }
    }

    @After
    public void afterTest() {
        int x = sequence.value;
        System.out.println(x);
        assert(x == (THREAD_COUNT * DO_TIMES));
    }

}


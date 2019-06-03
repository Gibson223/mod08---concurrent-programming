package block4.cp.tests;

import block3.cp.locks.Reent;
import block3.cp.locks.unsafesequence;
import block4.cp.dekker2;
import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class dekkertest {
    private static final int THREAD_COUNT = 5;
    private  static final int DO_TIMES = 100;
//    private final unsafesequence sequence = new unsafesequence(new dekker2());
//    private final unsafesequence sequence = new unsafesequence(new CompareAndSet());
//    private final unsafesequence sequence = new unsafesequence(new ReCompare());
    private final unsafesequence sequence = new unsafesequence(new dekker2());

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


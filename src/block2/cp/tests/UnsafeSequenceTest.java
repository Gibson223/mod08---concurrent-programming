package block2.cp.tests;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class UnsafeSequenceTest {
    private static final int THREAD_COUNT = 4;
    private  static final int DO_TIMES = 100;

    private final SafeSequence0 sequence = new SafeSequence0();

    @Test
    @Threaded(count = THREAD_COUNT)
    public void doAdd() {
//        for (int i = 0; i < DO_TIMES; i++) {
//            System.out.println(us.getNext());
//        }
        sequence.doFor(DO_TIMES);
    }

    @After
    public void afterTest() {
        int x = sequence.getNext();
        System.out.println(x);
        assert(x == (THREAD_COUNT * DO_TIMES));
    }


}

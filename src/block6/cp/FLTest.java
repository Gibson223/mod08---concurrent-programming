package block6.cp;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class FLTest {
    private static final int TC = 20;
    private FastLane fl = new FastLane(TC);

    @Test
    @Threaded(count = TC)
    public void test(@ThreadNumber int tn) {
        if (tn == 0) {
            FLThread flt = fl.start(true);
            flt.write(tn, "Test");
            flt.commit();
        } else {
            FLThread flt = fl.start(false);
            flt.write(tn, "Test " + tn);
            flt.commit();
        }
    }

    @After
    public void afterTest() {
        for (int i = 0; i < TC; i++) {
            System.out.println(fl.memory[i]);
        }
    }

}

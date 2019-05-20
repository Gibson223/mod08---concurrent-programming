package block4.cp.tests;

import block2.cp.queue.MyQueue;
import block2.cp.queue.Queue;
import block2.cp.queue.QueueEmptyException;
import block2.cp.queue.SafeMyQueue;
import block4.cp.concurrencyqueue;
import block4.cp.multiqueue;
import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class QueueTest {

    private static final int THREAD_COUNT = 5;
    private  static final int DO_TIMES = 100;
    private  Queue queue;
    private final Queue safeMyQueue = new SafeMyQueue();
    private final Queue concur = new concurrencyqueue();

    @Rule
    public TestName name = new TestName();

    private  long time;

    @Before
    public void init() {
        this.time = System.nanoTime();
    }

    @Test
    public void myqueue(){
        this.queue = this.safeMyQueue;
        this.doAdd(queue);
//        queue.push(new Object());

    }

    @Test
    public void concur(){
        this.queue = concur;
        this.doAdd(queue);
//        queue.push(new Object());
    }

    public static final int NUMCONS=1;
    public static final int NUMPRODS=1;
    private final multiqueue prodcons = new multiqueue(NUMPRODS);


    @Test
    @Threaded(count = NUMCONS+ NUMPRODS)
    public void prodconstest(@ThreadNumber int thrdnr) throws QueueEmptyException {
        this.queue = prodcons;
        if (thrdnr < NUMPRODS) {
            this.prods(thrdnr);
        } else {
            for (int p=0; p < (DO_TIMES*NUMPRODS/NUMCONS);p++) {
                if (this.prodcons.pull() == null) {
                    System.out.println("pulled nothing");
                }
                System.out.println("pulled something");
            }
        }
    }

    public void prods(int thrdnr) {
        System.out.println("got here");
        for (int i = 0; i< DO_TIMES; i++) {
//            System.out.println("tried to add object");
            this.prodcons.push(new Object(), thrdnr);
        }
    }


//    @Test
    @Threaded(count = THREAD_COUNT)
    public void doAdd(Queue queue) {
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
        System.out.println( this.name.getMethodName() + " time to run: " + (System.nanoTime() - this.time));
        System.out.println(queue.getLength());
        assert(queue.getLength() == 0);
    }

}

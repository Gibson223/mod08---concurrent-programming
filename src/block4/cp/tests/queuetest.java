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

import java.util.concurrent.atomic.AtomicInteger;

@RunWith(ConcurrentRunner.class)
public class QueueTest {

    private static final int THREAD_COUNT = 5;
    private  static final int DO_TIMES = 10;
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

    public static final int NUMCONS=3;
    public static final int NUMPRODS=1;
    private final multiqueue prodcons = new multiqueue(NUMPRODS);

    private SafeMyQueue savequeue = new SafeMyQueue();

    private final int TOTAL  = 100;
    private AtomicInteger a = new AtomicInteger(0);
    @Test
    @Threaded(count = NUMCONS+ NUMPRODS)
    public void prodconstest(@ThreadNumber int thrdnr) {
        System.out.println(thrdnr);
        if (thrdnr < NUMPRODS) {
            for (int i= 0; i < TOTAL; i++) {
                this.prodcons.push(i);
                System.out.println("val i: "+ i);
            }

        } else {
            while(this.a.get() < TOTAL) {
                Object i = this.prodcons.pull();
                if ( i == null) {
                    continue;
                }
                int l = (int) i;
                this.savequeue.push(l);
                a.incrementAndGet();
                System.out.println(thrdnr + " pulled " + l);
            }
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
        System.out.println(prodcons.getLength());
        System.out.println("savequeue: "+ this.savequeue.getLength());
        assert(this.savequeue.getLength() == 100);
        assert(prodcons.getLength() == 0);
    }

}

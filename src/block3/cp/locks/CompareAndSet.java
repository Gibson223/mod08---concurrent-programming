package block3.cp.locks;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class CompareAndSet implements BasicLock{
    public AtomicInteger locked;
    public PriorityQueue queue;
    public CompareAndSet(){
        this.queue = new PriorityQueue();
        this.locked = new AtomicInteger(-1);
    }
    /**
     * Acquires the lock.
     * * @param threadNumber is the number of the requesting thread,
     * * threadNumber == 0|1
     *
     * @param threadNumber
     */
    @Override
    public void lock (int threadNumber) {
        this.queue.add(threadNumber);
       while(!this.locked.compareAndSet(-1, threadNumber));
    }

    /**
     * Releases the lock.
     * * @param threadNumber is the number of the releasing thread,
     * * threadNumber == 0|1
     *
     * @param threadNumber
     */
    @Override
    public void unlock(int threadNumber) {
        this.locked.set(-1);
    }
}

package block6.cp;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class FastLane {

    public int cntr = 0;
    public long masterID;
    public ReentrantLock ttaslock = new ReentrantLock();
    private ReentrantLock mcslock = new ReentrantLock();
    public Integer threadCount;
    public Object[] memory;
    public Integer[] dirty;

    public FastLane(Integer tc) {
        threadCount = tc;
        dirty = new Integer[threadCount];
        Arrays.fill(dirty, 0);
        memory = new Object[threadCount];
    }

    public FLThread start(boolean pessimistic) {
        if (masterID == Thread.currentThread().getId()%threadCount) {
            ttaslock.lock();
            if (masterID == Thread.currentThread().getId()%threadCount) {
                return new Master(cntr, dirty, memory, ttaslock);
            } else {
                ttaslock.unlock();
                Helper helper = new Helper(cntr, dirty, memory, ttaslock, mcslock);
                helper.start();
                return helper;
            }
        } else if (pessimistic) {
            ttaslock.lock();
            masterID = Thread.currentThread().getId()%threadCount;
            return new Master(cntr, dirty, memory, ttaslock);
        } else {
            Helper helper = new Helper(cntr, dirty, memory, ttaslock, mcslock);
            helper.start();
            return helper;
        }
        
    }

}
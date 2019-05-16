package block3.cp.locks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Reent implements BasicLock {
    private AtomicInteger haslock;
    private ReentrantLock lock;
    public Reent() {
        this.haslock = new AtomicInteger(-1);
        this.lock = new ReentrantLock();
    }
    @Override
    public void lock(int threadNumber) {
        System.out.println(threadNumber + " trying to obtain lock");
        while(!lock.isHeldByCurrentThread()){
            System.out.println(threadNumber + "obtained lock");
            lock.lock();
        }
    }

    @Override
    public void unlock(int threadNumber) {
//        System.out.println(threadNumber + " trying to release lock");
        if (!lock.isHeldByCurrentThread()) {
            System.out.println(threadNumber + " doesnt have lock");
        }
        System.out.println(threadNumber +  " released lock");
        this.lock.unlock();        }
}

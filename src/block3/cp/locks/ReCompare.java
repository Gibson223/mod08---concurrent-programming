package block3.cp.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class ReCompare implements BasicLock {
    private AtomicInteger locked;
    public ReCompare() {
        this.locked = new AtomicInteger(-1);
    }
    @Override
    public void lock(int threadNumber) {
        while(!this.locked.compareAndSet(-1, threadNumber)){
            if (this.locked.get() == threadNumber ) {
                break;
            }
        }
    }

    @Override
    public void unlock(int threadNumber) {
        if (this.locked.compareAndSet(threadNumber, -1)) {
            System.out.println(threadNumber +" released lock");
        } else {
            System.out.println("dont have lock but tried to unlock?");
        }
    }
}

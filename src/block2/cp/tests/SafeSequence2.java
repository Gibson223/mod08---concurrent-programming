package block2.cp.tests;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@NotThreadSafe
public class SafeSequence2 {
    private int value;
    private final ReentrantLock lock = new ReentrantLock();

    public int getNext() {
        return value++;
    }

    public void doFor(int x) {
        for(int i = 0; i < x; i++) {
            lock.lock();
            getNext();
            lock.unlock();
        }
    }
}

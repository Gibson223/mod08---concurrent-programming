package block3.cp.locks;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerArray;

@NotThreadSafe
public class unsafesequence {
    public int value;
    public BasicLock dek;
    public unsafesequence(BasicLock lock) {
//        System.out.println(this.threadnr);
        this.dek = lock;
    }
    public void getNext(int threadnr) {
        this.dek.lock(threadnr);
        value++;
        this.dek.unlock(threadnr);
    }
    public void doublelock(int threadnr) {
        this.dek.lock(threadnr);
        this.dek.lock(threadnr);
        value++;
        this.dek.unlock(threadnr);
//        this.dek.unlock(threadnr);

    }
}

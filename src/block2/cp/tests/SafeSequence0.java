package block2.cp.tests;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class SafeSequence0 {
    private AtomicInteger value = new AtomicInteger(0);

    public int getNext() {
        return value.getAndIncrement();
    }

    public void doFor(int x) {
        for (int i = 0; i < x; i++) {
            getNext();
        }
    }
}

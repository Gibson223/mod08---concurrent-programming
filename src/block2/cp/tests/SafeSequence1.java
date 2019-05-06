package block2.cp.tests;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class SafeSequence1 {
    private int value;

    public int getNext() {
        return value++;
    }

    public void doFor(int x) {
        for(int i = 0; i < x; i++) {
            synchronized (this) {
                getNext();
            }
        }
    }
}

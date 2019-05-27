package block4.cp;

import block2.cp.queue.Queue;
import block2.cp.queue.QueueEmptyException;
import block2.cp.queue.SafeMyQueue;
import block4.cp.tests.QueueTest;

import java.util.ArrayList;
import java.util.List;

public class multiqueue implements Queue {
    public List<SafeMyQueue> multiqueue;
    public multiqueue(int producers) {
        this.multiqueue = new ArrayList<>();
        for (int i = 0; i < producers; i++) {
            this.multiqueue.add(new SafeMyQueue());
        }
    }
    private int i = 0;
    @Override
    public void push(Object x) {
        i =  (int) Thread.currentThread().getId() % QueueTest.NUMPRODS;
        this.multiqueue.get(i).push(x);
    }

    @Override
    public Object pull() {
            for (SafeMyQueue q : this.multiqueue) {
                Object i;
                try {
                    i = q.pull();
                } catch (QueueEmptyException e) {
                    continue;
                }
                return i;
            }
//            }
        return null;
    }

    @Override
    public int getLength() {
        int total= 0;
        for (SafeMyQueue l :this.multiqueue) {
            total += l.getLength();
        }
        return total;
    }

}

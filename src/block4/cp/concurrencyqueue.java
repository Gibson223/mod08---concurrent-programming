package block4.cp;

import block2.cp.queue.Queue;
import block2.cp.queue.QueueEmptyException;

import java.util.concurrent.ConcurrentLinkedQueue;

// queue:
// add: adds to end of queue
// pull: removes the first of the queue
public class concurrencyqueue implements Queue {
    private ConcurrentLinkedQueue queue;
    public concurrencyqueue() {
        this.queue = new ConcurrentLinkedQueue<>();
    }
    @Override
    public void push(Object x) {
        queue.add(x);
    }

    @Override
    public Object pull() throws QueueEmptyException {
        return this.queue.poll();
    }

    @Override
    public int getLength() {
        return this.queue.size();
    }
}

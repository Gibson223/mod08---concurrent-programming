package block2.cp.queue;

public class SafeMyQueue implements Queue {

    public block2.cp.queue.LinkedList llist = new LinkedList();

    /**
     * Pushes an element at the head of the queue.
     *
     * @param x
     */
    @Override
    public synchronized void push(Object x) {
        llist.addFirst(x);
    }

    /**
     * Obtains and removes the tail of the queue.
     */
    @Override
    public synchronized Object pull() throws QueueEmptyException {
        return llist.removeLast();
    }

    /**
     * Returns the number of elements in the queue.
     */
    @Override
    public synchronized int getLength() {
        return llist.getSize();
    }
}

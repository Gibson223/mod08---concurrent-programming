package block2.cp.queue;

import java.util.LinkedList;

public class MyQueue implements Queue {

    private LinkedList<Object> llist = new LinkedList();

    /**
     * Pushes an element at the head of the queue.
     *
     * @param x
     */
    @Override
    public void push(Object x) {
        llist.addFirst(x);
        System.out.println(llist);
    }

    /**
     * Obtains and removes the tail of the queue.
     */
    @Override
    public Object pull() throws QueueEmptyException {
        return llist.removeLast();
    }

    /**
     * Returns the number of elements in the queue.
     */
    @Override
    public int getLength() {
        return llist.size();
    }
}

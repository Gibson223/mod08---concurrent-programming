package block4.cp;

import block2.cp.queue.Queue;
import block2.cp.queue.QueueEmptyException;
import block2.cp.queue.SafeMyQueue;

import java.util.ArrayList;
import java.util.List;

public class multiqueue implements Queue {
    public List<producer> multiqueue;
    public multiqueue(int producers) {
        this.multiqueue = new ArrayList<>();
        for (int i = 0; i < producers; i++) {
            this.multiqueue.add(new producer());
        }
    }

    @Override
    public void push(Object x) {
        // not used
    }
    //doesnt need synchronized, should only have 1 produces per queue
    public void push(Object x, int thr){
        this.multiqueue.get(thr).push(x);
    }

    public final int CHECKS = 10000;
    @Override
    public Object pull() {
        int checks = 0;
        boolean done = false;
        while (checks < CHECKS) {
            for (producer q : this.multiqueue) {
                Object i = null;
                try {
                    i = q.pull();
                } catch (QueueEmptyException e) {
                    System.out.println("pulled nothing");
                    continue;
                }
                done = true;
                break;
                // actually obtained something
            }
            if (done) {
                System.out.println("obtained object");
                break;
            }
            checks = checks + 1;
            }
        System.out.println("checks needed: " + checks);
        return null;
    }

    @Override
    public int getLength() {
        int total= 0;
        for (producer l :this.multiqueue) {
            total += l.queue.getLength();
        }
        return total;
    }

    public class producer {
        public SafeMyQueue queue;
        public producer() {
            queue = new SafeMyQueue();
        }
        // doesnt need synchronized, only that pushes to this queue
        public void push(Object x){
//            System.out.println("pushed object");
            queue.push(x);
//            System.out.println(queue.getLength());
        }
        public synchronized Object pull() throws QueueEmptyException {
            if (this.queue.getLength() != 0) {
                this.queue.pull();
            }
//            System.out.println("queue" + " is empty");
            throw new QueueEmptyException();
        }

    }
}

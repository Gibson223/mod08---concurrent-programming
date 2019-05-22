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
    public synchronized void push(Object x) {
        i = i % QueueTest.NUMPRODS;
        this.multiqueue.get(i).push(x);
    }
    //doesnt need synchronized, should only have 1 produces per queue
//    public void push(Object x, int thr){
//        System.out.println("tried to add");
//        this.multiqueue.get(thr).push(x);
//    }

//    public final int CHECKS = 10000;
    @Override
    public synchronized Object pull() {
//        int checks = 0;
//        boolean done = false;
//        while (checks < CHECKS) {
            for (SafeMyQueue q : this.multiqueue) {
                Object i;
                try {
                    i = q.pull();
                } catch (QueueEmptyException e) {
//                    System.out.println("empty exception");
                    continue;
                }
                // actually obtained something
                return i;
            }
//            checks = checks + 1;
//            }
//        System.out.println("checks needed: " + checks);
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

//    public class producer {
//        public SafeMyQueue queue;
//        public producer() {
//            queue = new SafeMyQueue();
//        }
//        // doesnt need synchronized, only that pushes to this queue
//        public void push(Object x){
//            System.out.println("pushed object");
//            queue.push(x);
////            System.out.println(queue.getLength());
//        }
//        public synchronized Object pull() throws QueueEmptyException {
//            if (this.queue.getLength() != 0) {
//                this.queue.pull();
//            }
////            System.out.println("queue" + " is empty");
////            throw new QueueEmptyException();
//        }

//    }
}

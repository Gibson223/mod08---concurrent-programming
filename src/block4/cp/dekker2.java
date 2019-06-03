package block4.cp;

import block3.cp.locks.BasicLock;

import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class dekker2 implements BasicLock {
        private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        @Override
        public void lock(int threadNumber) {
            queue.add(threadNumber);
            while (queue.peek() != threadNumber) {
//               waits for the lock
            }
//       ends method, now has lock until unlock is called
        }

        @Override
        public void unlock(int threadNumber) {
            queue.poll();
        }
    }

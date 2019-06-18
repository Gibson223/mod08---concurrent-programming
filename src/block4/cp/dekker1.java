package block4.cp;

import block3.cp.locks.BasicLock;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class dekker1 implements BasicLock {
        public int turn;
        public AtomicIntegerArray wantstoenter;
        public dekker1(){
            turn = 0;
            wantstoenter = new AtomicIntegerArray(2);
//        this.wantstoenter.set(0, 0);

        }
        /**
         * Acquires the lock.
         * * @param threadNumber is the number of the requesting thread,
         * * threadNumber == 0|1
         *
         * @param threadNumber
         */
        @Override
        public void lock(int threadNumber) {

            int other = 1 - threadNumber;
            this.wantstoenter.set(threadNumber, 1);
            while (this.wantstoenter.get(other) == 1) {
                // not ones turn
                if (this.turn != threadNumber) {
                    this.wantstoenter.set(threadNumber, 0);
                    while (this.turn != threadNumber) {
                        //
                    }
                    this.wantstoenter.set(threadNumber, 1 );
                }
            }
        }

        /**
         * Releases the lock.
         * * @param threadNumber is the number of the releasing thread,
         * * threadNumber == 0|1
         *
         * @param threadNumber
         */
        @Override
        public void unlock(int threadNumber) {
            // not my turn, however happens to have lock
            if (this.turn != threadNumber) {
            }
            this.wantstoenter.set(threadNumber, 0);
            this.turn = 1- threadNumber;
        }
    }

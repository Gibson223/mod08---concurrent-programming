package block7.cp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBarrier implements Barrier {

	private int threads;
	private volatile int waitFor;
	private final Lock lock = new ReentrantLock();

	public MyBarrier(int threads) {
		this.threads = threads;
		this.waitFor = threads;
	}

	@Override
	public int await() throws InterruptedException {
		synchronized (lock) {
			waitFor--;
			if (waitFor != 0) {
//				while (waitFor != 0) {
				lock.wait();
//				}
			} else {
				lock.notifyAll();
				this.waitFor = threads;
			}
		}
		return 0;
	}
}
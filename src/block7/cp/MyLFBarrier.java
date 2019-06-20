package block7.cp;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLFBarrier implements Barrier {

	private AtomicInteger waitFor;

	/**
	 * Constructs a new object.
	 */
	public MyLFBarrier(int threads) {
		this.waitFor = new AtomicInteger(threads);
	}

	@Override
	public int await() throws InterruptedException {
		waitFor.decrementAndGet();
//		int c;
//		do {
//			c = waitFor.get();
//			if (c != 0) {
//				continue;
//			}
//		}
//		while (!waitFor.compareAndSet(c, 0));
		while (waitFor.get() != 0);
		return 0;

	}
}

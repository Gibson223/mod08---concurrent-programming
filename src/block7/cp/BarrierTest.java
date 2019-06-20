package block7.cp;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class BarrierTest {

	private static final int THREADS = 20;
	private MyBarrier bar = new MyBarrier(THREADS);
	private MyLFBarrier flBar= new MyLFBarrier(THREADS);

	@Test
	@Threaded(count = THREADS)
	public void test(@ThreadNumber int tn) {
		System.out.println("Thread " + tn + " to barrier");
		try {
			bar.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + tn + " finished");
	}

	@Test
	@Threaded(count = THREADS)
	public void lfTest(@ThreadNumber int tn) {
		System.out.println("Thread " + tn + " to FLBarrier");
		try {
			flBar.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + tn + " finished FL");
	}
}

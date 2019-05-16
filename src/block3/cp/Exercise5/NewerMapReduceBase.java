package block3.cp.Exercise5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NewerMapReduceBase implements Runnable{

	private ArrayList<Integer> list;
	private Integer output = 0;

	public Integer getOutput() {
		return output;
	}

	public NewerMapReduceBase(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		Integer size = list.size();
		CyclicBarrier barrier = new CyclicBarrier(size+1);
		for (int i=0; i < size; i++) {
			Thread thread = new Thread(new SubMapReduceBase(barrier, list, i));
			thread.start();
		}
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		SubMapReduceBase reducer = new SubMapReduceBase(barrier, list, 0);
		Integer result = reducer.reduce(list);
		this.output = result;
	}

	public abstract class newerMapReduceBase<I,O,R> {
		public abstract O map(I input);
		public abstract R reduce(ArrayList<O> input);
	}

	public class SubMapReduceBase extends newerMapReduceBase<Integer, Integer, Integer> implements Runnable {

		CyclicBarrier barrier;
		ArrayList<Integer> list;
		int index;

		public SubMapReduceBase(CyclicBarrier barrier, ArrayList<Integer> list, int index) {
			this.barrier = barrier;
			this.list = list;
			this.index = index;
		}

		@Override
		public Integer map(Integer input) {
			return input+1;
		}

		@Override
		public Integer reduce(ArrayList<Integer> input) {
			Integer total = 0;
			for (Integer integer : input) {
				total += integer;
			}
			return total;
		}

		@Override
		public void run() {
			Integer ret = map(list.remove(index));
			list.add(index, ret);
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}

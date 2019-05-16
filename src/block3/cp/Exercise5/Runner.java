package block3.cp.Exercise5;

import org.junit.Test;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		ArrayList<Integer> arrlist = new ArrayList<>();
		arrlist.add(1);
		arrlist.add(2);
		arrlist.add(3);
		arrlist.add(4);
		arrlist.add(5);
		NewerMapReduceBase mrb = new NewerMapReduceBase(arrlist);
		mrb.run();
		System.out.println(mrb.getOutput());
	}

	@Test
	public void doTest1() {
		ArrayList<Integer> arrlist = new ArrayList<>();
		arrlist.add(1);
		arrlist.add(1);
		arrlist.add(1);
		NewerMapReduceBase mrb = new NewerMapReduceBase(arrlist);
		mrb.run();
		assert (mrb.getOutput() == 6);
		assert (arrlist.size() == 3);
	}

	@Test
	public void doTest2() {
		ArrayList<Integer> arrlist = new ArrayList<>();
		arrlist.add(1);
		arrlist.add(2);
		arrlist.add(3);
		arrlist.add(4);
		arrlist.add(5);
		NewerMapReduceBase mrb = new NewerMapReduceBase(arrlist);
		mrb.run();
		assert (mrb.getOutput() == 20);
		assert (arrlist.size() == 5);
	}

}

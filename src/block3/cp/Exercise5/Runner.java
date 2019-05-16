package block3.cp.Exercise5;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		ArrayList<Integer> arrlist = new ArrayList<>();
		arrlist.add(1);
		arrlist.add(1);
		arrlist.add(1);
		arrlist.add(1);
		arrlist.add(1);
		NewerMapReduceBase mrb = new NewerMapReduceBase(arrlist);
		mrb.run();
		System.out.println(mrb.getOutput());
	}
}

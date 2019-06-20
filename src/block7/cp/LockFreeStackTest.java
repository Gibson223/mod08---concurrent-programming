package block7.cp;

import org.junit.Test;

public class LockFreeStackTest {

	@Test
	public void test() {
		LockFreeStack lfs = new LockFreeStack();
		lfs.push("Test1");
		lfs.push("Test2");
		assert (lfs.getLength() == 2);
		assert (lfs.pop() == "Test2");
		assert (lfs.pop() == "Test1");
		assert(lfs.getLength() == 0);
	}
}

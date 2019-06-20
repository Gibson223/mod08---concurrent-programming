package block7.cp;

public interface Barrier {
	public int await() throws InterruptedException;
}

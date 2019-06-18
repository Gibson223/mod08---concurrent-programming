package block7.cp;

public interface LockFreeStackInterface<T> {
	void push(T x);
	T pop();
	int getLength();
}
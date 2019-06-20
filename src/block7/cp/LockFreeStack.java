package block7.cp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> implements LockFreeStackInterface<T> {
	AtomicReference<Node<T>> head = new AtomicReference<Node<T>>();
	private AtomicInteger aint = new AtomicInteger();

	@Override
	public void push(T item) {
		Node<T> newHead = new Node<T>(item);
		Node<T> oldHead;
		do {
			oldHead = head.get();
			newHead.next = oldHead;
		}
		while (!head.compareAndSet(oldHead, newHead));
		aint.incrementAndGet();
	}

	@Override
	public T pop() {
		Node<T> oldHead;
		Node<T> newHead;
		do {
			oldHead = head.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		}
		while (!head.compareAndSet(oldHead, newHead));
		aint.decrementAndGet();
		return oldHead.item;
	}

	@Override
	public int getLength() {
		return aint.get();
	}

	static class Node<T> {
		final T item;
		Node<T> next;
		public Node(T item) {
			this.item = item;
		}
	}}

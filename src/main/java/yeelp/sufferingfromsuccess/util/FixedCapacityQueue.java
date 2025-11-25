package yeelp.sufferingfromsuccess.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class FixedCapacityQueue<E> implements Queue<E> {

	private final int capacity;
	private final Queue<E> delegate;
	
	public FixedCapacityQueue(int capacity) {
		this.capacity = capacity;
		this.delegate = new LinkedList<E>();
	}
	
	@Override
	public int size() {
		return this.delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return this.delegate.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.delegate.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return this.delegate.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.delegate.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.delegate.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return this.delegate.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.delegate.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return this.delegate.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.delegate.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.delegate.retainAll(c);
	}

	@Override
	public void clear() {
		this.delegate.clear();
	}

	@Override
	public boolean add(E e) {
		this.delegate.add(e);
		if(this.size() > this.capacity) {
			this.poll();
		}
		return true;
	}

	@Override
	public boolean offer(E e) {
		return this.add(e);
	}

	@Override
	public E remove() {
		return this.delegate.remove();
	}

	@Override
	public E poll() {
		return this.delegate.poll();
	}

	@Override
	public E element() {
		return this.delegate.element();
	}

	@Override
	public E peek() {
		return this.delegate.peek();
	}

}

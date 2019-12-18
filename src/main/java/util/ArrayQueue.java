package util;

import java.util.Iterator;

public class ArrayQueue<E> {
	private Object[] items;
	
	private int takeIndex;
	
	private int putIndex;
	
	private int capacity;
	
	private int count;
	
	public ArrayQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		this.items = new Object[capacity];
	}

	public void put(E e) {
		if (count == 0) {
			items[takeIndex] = e;
		}
		if (++putIndex == capacity) {
			putIndex = 0;
		}
		items[putIndex] = e;
		count++;
	}
	
	public E take() throws InterruptedException {
		if (count == 0) {
			return null;
		}
		E e = elementData(takeIndex);
		items[takeIndex] = null;
		if (++takeIndex == capacity) {
			takeIndex = 0;
		}
		count--;
		return e;
	}
	
	public E poll() {
		return null;
	}

	public E peek() {
		return elementData(takeIndex);
	}

	public boolean offer(E e) {
		return false;
	}

	public int remainingCapacity() {
		return 0;
	}

	public Iterator<E> iterator() {
		return null;
	}

	public int size() {
		return count;
	}
	
	public String toString() {
		if (count == 0) {
			return "[]";
		}
		String str = "[";
		for (int i = 0; i < capacity; i++) {
			str += items[i];
			if (i == capacity - 1) {
				return str + "]";
			}
			str += ",";
		}
		return str + "]";
		
	}

	@SuppressWarnings("unchecked")
	private E elementData(int index) {
		 return (E) items[index];
	}
}

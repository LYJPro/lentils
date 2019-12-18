package util;

import java.util.Iterator;

/**
 * 实现数组表
 */
public class ArrayList<E> {
	private Object[] elementData;
	
	private int size = 0;
	
	/**
	 * 默认数组大小
	 */
	private final static int DEFAULT_CAPACITY = 10;
	
	/**
	 * 默认数组大小
	 */
	private int capacity = DEFAULT_CAPACITY;
	
	/**
	 * 初始化数组大小默认是DEFAULT_CAPACITY
	 */
	public ArrayList() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * 构造方法，初始化数组大小
	 * @param capacity
	 */
	public ArrayList(int capacity) {
		this.elementData = new Object[capacity];
		this.capacity = capacity;
	}

	/**
	 * 添加元素
	 * @param e
	 */
	public boolean add(E e) {
		if (size == capacity) {
			grow(capacity);
		}
		elementData[size++] = e;
		return true;
	}
	
	/**
	 * 固定位置添加元素
	 * @param e
	 * @param index
	 */
	public void add(E e, int index) {
		rangeCheck(index);
		add(e);
		for (int i = size - 1; i <= index;) {
			elementData[i] = elementData[--i];
		}
		elementData[index] = e;
		size++;
	}
	
	/**
	 * 扩容，数组空间不足，重新分配内存空间
	 * @param oldCapacity
	 */
	private void grow(int oldCapacity) {
		int newCapacity = oldCapacity << 1;
		if (newCapacity - oldCapacity < 0) {
			newCapacity = oldCapacity;
		}
		Object[] tmpElement = new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			tmpElement[i] = elementData[i];
		}
		elementData = tmpElement;
		capacity = newCapacity;
	}
	
	/**
	 * 移除最后一个元素
	 */
	public boolean remove(Object o) {
		if (size == 0) {
			return false;
		}
		elementData[--size] = null;
		return true;
	}
	
	/**
	 * 返回当前表的大小
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 当前表是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		elementData = new Object[capacity];
	}
	
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E spcepify = elementData(index);
		elementData[index] = element;
		return spcepify;
	}

	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	public Iterator<E> iterator() {
		return new _ArrayListInterator();
	}

	private class _ArrayListInterator implements Iterator<E> {
		int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public E next() {
			return elementData(current++);
		}
		
		public void remove() {
			ArrayList.this.remove(current--);
		}
	}
	
	public E get(int index) {
		rangeCheck(index);
		
		return elementData(index);
	}

	public void add(int index, E element) {
		rangeCheckForAdd(index);
	}

	public E remove(int index) {
		return null;
	}

	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData(i))) {
					return i;
				}
			}
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	public E elementData(int index) {
		return (E) elementData[index]; 
	}
	
	/**
	 * 超出范围错误提示
	 * @param index
	 * @return
	 */
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	/**
	 * 校验index是否超出范围
	 * @param index
	 */
	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 检查index范围
	 */
	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		String str = "[";
		for (int i = 0; i < elementData.length;) {
			str += elementData[i++].toString();
			if (i == size) {
				return str + "]";
			}
			str += ", ";
		}
		return str;
	}
}

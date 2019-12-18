package util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> {
	private int size = 0;
	
	private Node<E> first;
	private Node<E> last;
	
	public void addFirst(E e) {
		Node<E> f = first;
		Node<E> node = new Node<>(null, e, f);
		first = node;
		if (size == 0) {
			last = node;
		} else {
			f.pre = node;
		}
		size++;
	}

	public void addLast(E e) {
		Node<E> l = last;
		Node<E> node = new Node<E>(l, e, null);
		last = node;
		if (size == 0) {
			first = node;
		} else {
			l.next = node;
		}
		size++;
	}
	
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	public E removeFirst() {
		Node<E> node = first;
		if (node == null) {
			throw new NoSuchElementException();
		}
		first = first.next;
		
		node.next = null;
		first.pre = null;
		size--;
		return node.item;
	}

	public E removeLast() {
		return null;
	}

	public E pollFirst() {
		return removeFirst();
	}

	public E pollLast() {
		return removeLast();
	}

	public E getFirst() {
		final Node<E> f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return f.item;
	}

	public E getLast() {
		final Node<E> l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return l.item;
	}

	public E peekFirst() {
		final Node<E> f = first;
		return f == null ? null : f.item;
	}

	public E peekLast() {
		final Node<E> l = last;
		return l == null ? null : l.item;
	}

	public boolean offer(E e) {
		return false;
	}

	public E remove() {
		return null;
	}

	public E poll() {
		return null;
	}

	public E element() {
		return null;
	}

	public E peek() {
		return peekFirst();
	}

	public void push(E e) {
		addFirst(e);
	}

	public E pop() {
		return removeFirst();
	}

	public ListIterator<E> listIterator(int index) {
		return null;
	}

	public int size() {
		return size;
	}
	
    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.pre;
            return x;
        }
    }

	@Override
	public String toString() {
		Node<E> node = first;
		if (first == null) {
			return "[]";
		}
		return "[" + linkedPrint("", node) + "]";
	}
	
	private String linkedPrint(String str, Node<E> node) {
		while (node.next != null) {
			str += node.item + ", ";
			node = node.next;
			linkedPrint(str, node);
		}
		return str += node.item;
	}
	
	public String reverseToString() {
		Node<E> node = last;
		if (last == null) {
			return "[]";
		}
		return "[" + reverseLinkedPrint("", node) + "]";
	}
	
	private String reverseLinkedPrint(String str, Node<E> node) {
		while (node.pre != null) {
			str += node.item + ", ";
			node = node.pre;
			reverseLinkedPrint(str, node);
		}
		return str += node.item;
	}
	
	private static class Node<E> {
		public E item;
		public Node<E> pre;
		public Node<E> next;

		public Node(Node<E> pre, E item, Node<E> next) {
			this.item = item;
			this.pre = pre;
			this.next = next;
		}
	}

}

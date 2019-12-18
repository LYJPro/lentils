package util;


public class BSTSet<E extends Comparable<E>> {
	BinarySearchTree<E> bst = new BinarySearchTree<>();
	
	public void add(E e) {
		bst.insert(e);
	}
	
	boolean contains(E e) {
		return bst.contains(e);
	}
}

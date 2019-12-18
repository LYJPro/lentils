package util;

import java.util.ArrayDeque;

/**
 * 二分搜索树
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
	private ArrayDeque<Node<E>> deque = new ArrayDeque<>();
	private Node<E> root;
	
	private int size;
	
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * 将Root跟节点赋值为null，将二分搜索树数据清空
	 */
	public void makeEmpty() {
		root = null;
	}
	
	/**
	 * 查询当前树的大小，包含多少元素
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断二分搜索树是否有数据，判断root是否是null
	 * 也可以判断size为0
	 * @return true：是空，false： 不是空
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * 判断二分搜索树是否包含元素
	 * @param e 查询元素
	 * @return true：包含，false：不包含
	 */
	public boolean contains(E e) {
		if (isEmpty()) {
			return false;
		}
		return contains(root, e);
	}
	
	/**
	 * 判断搜索树中是否包含某个元素
	 * @param node
	 * @param e
	 */
	private boolean contains(Node<E> node, E e) {
		if (node == null || e == null) {
			return false;
		}
		if (e.compareTo(node.element) > 0) {
			return contains(node.right, e);
		} else if (e.compareTo(node.element) < 0) {
			return contains(node.left, e);
		}
		return true;
	}
	
	/**
	 * 查询二分搜索树中最小数值
	 * @return 返回二分搜索树最小数值
	 */
	public E findMin() {
		if (isEmpty()) {
			return null;
		}
		return findMin(root).element;
	}
	
	/**
	 * 查询二分搜索树中最小数值
	 * @param  r 二分搜索树根节点
	 * @return 返回二分搜索树最小值
	 */
	public Node<E> findMin(Node<E> r) {
		if (r.left == null) {
			return r;
		}
		return findMin(r.left);
	}
	
	/**
	 * 查询二分搜索树最大值
	 * @return 返回二分搜索树最大值
	 */
	public E findMax() {
		if (isEmpty()) {
			return null;
		}
		return findMax(root).element;
	}
	
	/**
	 * 查询树中的最大值
	 * @param  r root根节点
	 * @return 返回树中最大值
	 */
	public Node<E> findMax(Node<E> r) {
		if (r.right == null) {
			return r;
		}
		return findMax(r.right);
	}
	
	/**
	 * 搜索树中新增数据
	 * @param e 新增元素
	 */
	public void insert(E e) {
		if (!contains(e)) {
			size++;
		}
		if (isEmpty()) {
			root = new Node<>(e);
		} else {
			insert(e, root);
		}
	}
	
	/**
	 * 搜索树中新增数据
	 * @param e 新增元素
	 * @param r 二分搜索树根节点
	 */
	private Node<E> insert(E e, Node<E> r) {
		if (r == null) {
			return new Node<>(e);
		}
		
		int compare = r.element.compareTo(e);
		if (compare == 0) {
			return new Node<>(e);
		} else if (compare < 0) {
			r.right = insert(e, r.right);
		} else {
			r.left = insert(e, r.left);
		}
		return r;
	}
	
	/**
	 * 移除树中数据
	 * @param 将要移除数据
	 * @return 如果移除成功则返回e，如果未找到元素则返回null
	 */
	public E remove(E e) {
		if (e == null || root == null) {
			return null;
		}
		return remove(root, e);
	}
	
	/**
	 * 移除树中任意一节点
	 * @param node 节点引用
	 * @param e 被移除的元素
	 */
	private E remove(Node<E> node, E e) {
		if (node.element.compareTo(e) > 0) {
			
		}
		// TODO
		if (e.compareTo(node.element) == 0) {
		}
		return e;
	}
	
	/**
	 * 前序遍历二分搜索树
	 */
	public String printPreTree() {
		return "前序遍历 : [" + prePrintTree(root) + "]";
	}
	
	/**
	 * 前序遍历二分搜索树
	 * @param r 二分搜索树根节点
	 */
	private String prePrintTree(Node<E> r) {
		if (r == null) {
			return "";
		}
		
		String str = "";
		str += " " + r.element + " ";
		str += prePrintTree(r.left);
		str += prePrintTree(r.right);
		return str;
	}
	
	/**
	 * 中序遍历二分搜索树
	 */
	public String printTree() {
		return "中序遍历 : [" + printTree(root) + "]"; 
	}
	
	/**
	 * 中序遍历二分搜索树
	 * @param r 二分搜索树根节点
	 */
	private String printTree(Node<E> r) {
		if (r == null) {
			return "";
		}
		
		String str = "";
		str += prePrintTree(r.left);
		str += " " + r.element + " ";
		str += prePrintTree(r.right);
		return str;
	}
	
	/**
	 * 后续遍历二分搜索树
	 */
	public String nextPrintTree() {
		return "后序遍历 : [" + nextPrintTree(root) + "]";
	}
	
	/**
	 * 后续遍历二分搜索树
	 * @param r 二分搜索树根节点
	 */
	public String nextPrintTree(Node<E> r) {
		if (r == null) {
			return "";
		}
		
		String str = "";
		str += prePrintTree(r.left);
		str += " " + r.element + " ";
		str += prePrintTree(r.right);
		return str;
	}
	
	/**
	 * 层序遍历二分搜索树
	 */
	public String levelPrint() {
		deque.push(root);
		return "层序遍历 : [" + levelPrintTree() + "]";
	}
	
	/**
	 * 层序遍历二分搜索树
	 */
	private String levelPrintTree() {
		if (deque.peek() == null) {
			return "";
		}
		
		Node<E> r = deque.remove();
		String str = "";
		str += " " + r.element + " ";
		
		if (r.left != null) {
			deque.add(r.left);
		}
		if (r.right != null) {
			deque.add(r.right);
		}
		str += levelPrintTree();
		return str;
	}
	
	/**
	 * 二分搜索树节点
	 */
	private static class Node<E> {
		Node(E element) {
			this(element, null, null);
		}
		
		Node(E element, Node<E> left, Node<E> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
		
		E element;
		Node<E> left;
		Node<E> right;
	}
	
}

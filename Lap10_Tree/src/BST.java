
import java.util.*;

public class BST<E extends Comparable<E>> {
	private BNode<E> root;

	public BST() {
		this.root = null;
	}

	public void setRoot(BNode<E> root) {
		this.root = root;
	}

	// Task 1
	// Add element e into BST
	public void add(E e) {
		if (this.root == null)
			this.setRoot(new BNode<>(e));
		else
			this.root.add(this.root, e);
	}

	// Add a collection of elements col into BST
	public void add(Collection<E> col) {
		for (E e : col)
			add(e);
	}

	// compute the depth of a node in BST
	public int depth(E node) {
		BNode<E> currentNode = this.root.search(this.root, node);
		return (currentNode != null) ? currentNode.findDepth(currentNode) : -1;
	}

	// compute the height of BST
	public int height() {
		return this.root.findDepth(this.root);
	}

	// Compute total nodes in BST
	public int size() {
		return this.root.countSize(this.root);
	}

	// Check whether element e is in BST
	public boolean contains(E e) {
		return this.root.search(this.root, e) != null;
	}

	// Find the minimum element in BST
	public E findMin() {
		return this.root.findMin(this.root);
	}

	// Find the maximum element in BST
	public E findMax() {
		return this.root.findMax(this.root);
	}

	// Remove element e from BST
	public boolean remove(E e) {
		return this.root.remove(e);
	}

	// get the descendants of a node
	public List<E> descendants(E data) {
		BNode<E> currentNode = this.root.search(this.root, data);
		return (currentNode == null) ? null : currentNode.addDescendants(currentNode, currentNode.getData());
	}

	// get the ancestors of a node
	public List<E> ancestors(E data) {
		return (this.root.search(this.root, data) == null) ? null : this.root.addAncestors(data);
	}

	// Task 2
	// display BST using inorder approach
	public void inorder() {
		this.root.inorderPrint(this.root);
	}

	// display BST using preorder approach
	public void preorder() {
		this.root.preorderPrint(this.root);
	}

	// display BST using postorder approach
	public void postorder() {
		this.root.postorderPrint(this.root);
	}

	public static void main(String[] args) {
		BST<Integer> tree = new BST<>();
		tree.add(25);
		tree.add(15);
		tree.add(50);
		tree.add(10);
		tree.add(22);
		tree.add(35);
		tree.add(70);
		tree.add(4);
		tree.add(12);
		tree.add(18);
		tree.add(24);
		tree.add(31);
		tree.add(44);
		tree.add(66);
		tree.add(90);

		tree.inorder();
		System.out.println();
		tree.preorder();
		System.out.println();
		tree.postorder();
		System.out.println();
		System.out.println(tree.descendants(15));
		System.out.println(tree.ancestors(90));
		System.out.println(tree.contains(25));
		System.out.println(tree.contains(100000));
		System.out.println(tree.depth(25));
		System.out.println(tree.height());
		System.out.println(tree.depth(15));
		System.out.println(tree.size());
		System.out.println(tree.remove(44));
		System.out.println(tree.remove(50));
		System.out.println(tree.remove(1000));
		System.out.println(tree.size());

		tree.inorder();
		System.out.println();
		System.out.println(tree.findMin());
		System.out.println(tree.findMax());
	}

}
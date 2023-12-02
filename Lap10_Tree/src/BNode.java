import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BNode<E extends Comparable<E>> {
	private E data;
	private BNode<E> left;
	private BNode<E> right;

	public BNode(E data, BNode<E> left, BNode<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public BNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public E getData() {
		return data;
	}

	public BNode<E> getLeft() {
		return left;
	}

	public BNode<E> getRight() {
		return right;
	}

	public void setData(E data) {
		this.data = data;
	}

	public void setLeft(BNode<E> left) {
		this.left = left;
	}

	public void setRight(BNode<E> right) {
		this.right = right;
	}
	// add element BST
	public void add(BNode<E> root, E e) {
		if (root.getData().compareTo(e) > 0) {
			if (root.getLeft() == null)
				root.setLeft(new BNode<>(e));
			else
				add(root.getLeft(), e);
		} else {
			if (root.getRight() == null)
				root.setRight(new BNode<>(e));
			else
				add(root.getRight(), e);
		}
	}
	// Add a collection of elements col into BST

	public int depth(E node) {
		if (node.equals(this.data)) {
			return 0;
		} else if (node.compareTo(this.data) < 0 && left != null) {
			return left.depth(node) + 1;
		} else if (node.compareTo(this.data) > 0 && right != null) {
			return right.depth(node) + 1;
		} else {
			return +1;
		}
	}

	// compute the height of BST
	public int height() {
		// TODO
		if (left == null && right == null) {
			return 0;
		} else if (left == null) {
			return 1 + right.height();
		} else if (right == null) {
			return 1 + left.height();
		} else {
			return 1 + Math.max(left.height(), right.height());
		}
	}

	// Compute total nodes in BST
	public int countSize(BNode<E> root) {
		return (root == null) ? 0 : 1 + countSize(root.getRight()) + countSize(root.getLeft());
	}

	// Check whether element e is in BST
	public boolean contains(E e) {
		// TODO
		int re = e.compareTo(data);
		if (re == 0) {
			return true;
		} else if (re < 0) {
			if (left == null) {
				return false;
			}
			left.contains(e);
			return true;
		} else if (re > 0) {
			if (right == null) {
				return false;
			}
			right.contains(e);
			return true;
		}
		return false;
	}

	// for findMin method
	public E findMin(BNode<E> e) {
		return (e.getLeft() == null) ? e.getData() : findMin(e.getLeft());
	}

	// for findMax method
	public E findMax(BNode<E> e) {
		return (e.getRight() == null) ? e.getData() : findMax(e.getRight());
	}

	// Remove element e from BST
	public boolean remove(E e) {
		BNode<E> removeNode = search(this, e);
		if (removeNode == null)
			return false;
		else {
			BNode<E> parent = findNodeParent(this, e);
			// Check node co 1 con
			if (removeNode.getLeft() == null && removeNode.getRight() == null) {
				if (parent.getData().compareTo(removeNode.getData()) < 0)
					parent.setRight(null);
				else
					parent.setLeft(null);
			}
			// Check node co 1 con
			else if (removeNode.getLeft() == null)
				parent.setRight(removeNode.getRight());
			else if (removeNode.getRight() == null)
				parent.setLeft(removeNode.getLeft());
			// Check node co 2 con
			else {
				BNode<E> newNode = findSuccessor(removeNode.getRight());
				// Xoa va tao lai nut ke thua
				remove(newNode.getData());
				newNode.setLeft(removeNode.getLeft());
				newNode.setRight(removeNode.getRight());
				// Them nut ke thua
				if (parent.getData().compareTo(newNode.getData()) < 0)
					parent.setRight(newNode);
				else
					parent.setLeft(newNode);
			}
		}
		return true;
	}

	public BNode<E> search(BNode<E> root, E node) {
		if (root != null) {
			int compareValue = root.getData().compareTo(node);
			// like binary search
			if (compareValue > 0)
				return search(root.getLeft(), node);
			else if (compareValue < 0)
				return search(root.getRight(), node);
			else
				return root;
		} else
			return null;
	}

	public BNode<E> findNodeParent(BNode<E> node, E data) {
		// Check node co 2 con
		if (node.getLeft() != null && node.getRight() != null) {
			if (node.getLeft().getData().compareTo(data) == 0 || node.getRight().getData().compareTo(data) == 0)
				return node;
			else {
				BNode<E> leftVal = findNodeParent(node.getLeft(), data);
				return (leftVal != null) ? leftVal : findNodeParent(node.getRight(), data);
			}
		}
		// Check node 0 con
		else if (node.getLeft() == null && node.getRight() == null)
			return null;
		// Check node co 1 con
		else {
			if (node.getLeft() == null) {
				return (node.getRight().getData().compareTo(data) == 0) ? node : findNodeParent(node.getRight(), data);
			} else
				return (node.getLeft().getData().compareTo(data) == 0) ? node : findNodeParent(node.getLeft(), data);
		}
	}

	public BNode<E> findSuccessor(BNode<E> node) {
		return (node.getLeft() == null) ? node : findSuccessor(node.getLeft());
	}

	// get the ancestors of a node
	public List<E> ancestors(E e) {
		// TODO
		int camp = e.compareTo(data);
		List<E> result = new ArrayList<>();
		if (camp == 0) {
			return result;
		}
		if (left != null) {
			if (left.contains(e)) {
				result.addAll(left.ancestors(e));
				result.add(data);
			}
		}
		if (right != null) {
			if (right.contains(e)) {
				result.addAll(right.ancestors(e));
				result.add(data);
			}
		}
		return result;
	}

	public List<E> addAncestors(E data) {
		List<E> list = new LinkedList();
		int value = this.getData().compareTo(data);
		if (value != 0) {
			list.add(this.getData());
		}
		if (value > 0 && this.left == null) {
			list.addAll(this.left.addAncestors(data));
		} else {
			if (this.right == null) {
				list.addAll(this.right.addAncestors(data));
			}
		}
		return list;

	}

	// display BST using inorder approach
	public void inorderPrint(BNode<E> node) {
		if (node.getLeft() != null) {
			inorderPrint(node.getLeft());
		}
		System.out.println(node + " ");
		if (node.getRight() != null) {
			inorderPrint(node.getRight());
		}
	}

	// display BST using preorder approach
	public void preorderPrint(BNode<E> node) {
		// TODO
		System.out.println(node + " ");
		if (node.getLeft() != null) {
			preorderPrint(node.getLeft());
		}
		if (node.getRight() != null) {
			preorderPrint(node.getRight());
		}
	}

	// display BST using postorder approach
	public void postorderPrint(BNode<E> node) {
		// TODO
		if (node.getLeft() != null) {
			postorderPrint(node.getLeft());
		}
		if (node.getRight() != null) {
			postorderPrint(node.getRight());
		}
		System.out.println(node + " ");
	}

	public int findDepth(BNode<E> node) {
		// Check nut co 2 con
		if (node.getLeft() != null && node.getRight() != null) {
			return Math.max(1 + findDepth(node.getLeft()), 1 + findDepth(node.getRight()));
		}
		// Check nut co con ben trai
		else if (node.getLeft() == null && node.getRight() == null) {
			return 0;

		}
		// Check nut co 1 con
		else
			return (node.getLeft() == null) ? 1 + findDepth(node.getRight()) : 1 + findDepth(node.getLeft());
	}

	public List<E> addDescendants(BNode<E> currentNode, E data) {
		// TODO Auto-generated method stub
		List<E> result = new LinkedList<>();
		if (currentNode != null) {
			if (currentNode.getData().compareTo(data) != 0)
				result.add(currentNode.getData());
			if (currentNode.getLeft() != null) {
				result.addAll(addDescendants(currentNode.left, data));
			}
			if (currentNode.getRight() != null) {
				result.addAll(addDescendants(currentNode.right, data));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
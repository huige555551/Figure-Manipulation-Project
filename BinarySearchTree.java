//Author: Neeraja Murali Dharan
//Date Last Modified: November 20,2015
//File Name: BinarySearchTree.java
/*Description: Class implements an ordered dictionary using a
 * binary search tree. 
 */

public class BinarySearchTree implements BinarySearchTreeADT {
	Node root;

	/*
	 * The constructor creats a binary search tree whose root is a leaf node
	 */
	public BinarySearchTree() {
		this.root = null;
	}

	/*
	 * Returns the DictEntry storing the given key if the key is in the tree:
	 * returns null otherwise
	 */

	public DictEntry find(Position key) {
		// calls a helper method to find node containing key in tree
		Node found = find(this.root, key);
		// returns null node is not in the tree;
		if (found == null) {
			return null;
		}
		// if node is found, returns DictEntry object stored in node
		return found.getDict();
	}

	/*
	 * private method that acts as a helper function that finds and returns the
	 * node containing the key specified or null of the node cannot be found
	 */
	private Node find(Node root, Position key) {
		// returns null of the tree is empty
		if (root == null) {
			return null;
		}

		// if the node contains the key, returns the node
		if (root.getDict().getPosition().compareTo(key) == 0) {
			return root;

			// else, recursive calls performed on either left or right child
		} else if (root.getDict().getPosition().compareTo(key) == 1) {
			return find(root.getLeft(), key);

		} else {
			return find(root.getRight(), key);
		}
	}

	/*
	 * Inserts the given data in the tree of not already in the tree. Throws a
	 * BSTException if node with same key is already int he tree.
	 */
	public void insert(DictEntry data) throws BSTException {
		Node temp = new Node(data);
		// if a node exists with the same key in the tree, throws exception
		if (find(data.getPosition()) != null) {
			throw new BSTException("Value already in tree");
		}
		// if the tree is empty, sets the node as the root
		else if (this.root == null) {
			this.root = temp;

			/*
			 * if the tree is not empty,iterates through the tree until an empty
			 * node is found, and to insert the node in the correct position
			 */
		} else {
			Node current = this.root;
			boolean insert = false;// keeps track of if the node has been
									// inserted

			// iterates through tree while the node has not been inserted in the
			// tree
			while (!insert) {
				// if statements to check if the node is inserted in the
				// appropriate position
				if (data.getPosition().compareTo(current.getDict().getPosition()) == -1) {
					if (current.getLeft() == null) {
						current.setLeft(temp);
						temp.setParent(current);
						insert = true;
					} else {
						current = current.getLeft();
					}
				} else if (data.getPosition().compareTo(current.getDict().getPosition()) == 1) {
					if (current.getRight() == null) {
						current.setRight(temp);
						temp.setParent(current);
						insert = true;
					} else {
						current = current.getRight();
					}
				}
			}
		}
	}

	/*
	 * Removes data item with the given key, if the key is stored in the tree
	 * Else, throws a BSTException if the node cannot be found
	 */
	public void remove(Position key) throws BSTException {
		// checks if node with key is in the tree, throws exception otherwise
		Node found = find(this.root, key);
		if (found == null) {
			throw new BSTException("The key is not in the tree");

		} else {
			/*
			 * CASE ONE: Both the left and right child of the node is null if
			 * the node is a root: set the root to null else: sets the
			 * right/left node of the parent to null, depending on whether the
			 * node is a right or left child
			 */
			if (found.getRight() == null && found.getLeft() == null) {
				if (found == this.root) {
					this.root = null;
				} else if (isRightNode(found.getParent(), found)) {
					found.getParent().setRight(null);
				} else {
					found.getParent().setLeft(null);
				}
			}

			/*
			 * CASE TWO: if the right child of the node is null: if the node is
			 * a root: set the root the left child else: if the node is the
			 * right/left child, set the leftchild of node accordingly as the
			 * right/left child of node's parent
			 */
			else if (found.getLeft() != null && found.getRight() == null) {
				if (found == this.root) {
					this.root = found.getLeft();
					found.getLeft().setParent(null);
				} else if (isRightNode(found.getParent(), found)) {
					found.getParent().setRight(found.getLeft());
				} else {
					found.getParent().setLeft(found.getLeft());
				}
			}

			/*
			 * CASE TWO: if the left child of the node is null: if the node is a
			 * root: set the root the right child else: if the node is the
			 * right/left child, set the right child of node accordingly as the
			 * right/left child of node's parent
			 */
			else if (found.getLeft() == null && found.getRight() != null) {
				if (found == this.root) {
					this.root = found.getRight();
					found.getRight().setParent(null);
				} else if (isRightNode(found.getParent(), found)) {
					found.getParent().setRight(found.getRight());
				} else {
					found.getParent().setLeft(found.getRight());
				}
			}

			/*
			 * CASE FOUR: if both the left and right children are internal node
			 * check if the node to be removed is the root, and remove the node
			 * and reform a binary tree that is balanced
			 */
			else {
				Node smallest = smallestHelper(found.getRight());
				if (found == this.root) {
					this.root = found.getRight();
					found.getLeft().setParent(smallest);
					smallest.setLeft(found.getLeft());
				} else {
					if (isRightNode(found.getParent(), found)) {
						found.getParent().setRight(found.getRight());
					} else {
						found.getParent().setLeft(found.getRight());
					}

					found.getRight().setParent(found.getParent());
					found.getLeft().setParent(smallest);
					smallest.setLeft(found.getLeft());
				}
			}
		}
	}

	// Helper method for remove that checks if a node is the right child of
	// another node
	// (checks if node2 is the right child of node1)
	private boolean isRightNode(Node node1, Node node2) {
		if (node1.getRight() == node2) {
			return true;
		}
		return false;
	}

	/*
	 * This method returns the DictEntry with the smallest key larger than the
	 * given one. Returns null of no successor can be found
	 */
	public DictEntry successor(Position key) {
		Node temp = find(this.root, key);
		
		if (temp.getRight() != null) {
			return smallestHelper(temp.getRight()).getDict();
		
		} else {
			Node p=temp.getParent();

			while (p!=null && p.getRight()==temp ) {
				temp=temp.getParent(); 
				p=temp.getParent(); 
			}
			if (temp.getParent()==null){
				return null;
			}
			else{
				return temp.getParent().getDict();
			}
		}
	}

	/*
	 * This method returns the DictEntry with the largest key smaller than the
	 * given one. Returns null of no successor can be found
	 */
	public DictEntry predecessor(Position key) {
		Node temp = find(this.root, key);
		if (temp == null) {
			return null;
		}
		if (temp.getLeft() != null) {
			return largestHelper(temp.getLeft()).getDict();
		}
		Node parent = temp.getParent();
		Node candidate = temp;

		while (parent != null && candidate == parent.getLeft()) {
			candidate = parent;
			parent = parent.getParent();
		}

		return parent.getDict();
	}

	/*
	 * Returns the DictEntry with the smallest key, or null if tree does not
	 * contain data using a helper method
	 */
	public DictEntry smallest() {
		return smallestHelper(root).getDict();
	}

	/*
	 * private helper method that finds the smallest node in the tree and
	 * returns this node, or null otherwise
	 */
	private Node smallestHelper(Node node) {
		if (node == null) {
			return null;
		}
		// recursive call to find the smallest node in tree
		else if (node.getLeft() == null) {
			return node;
		} else {
			return smallestHelper(node.getLeft());
		}
	}

	/*
	 * Returns the DictEntry with the largest key, or null if tree does not
	 * contain data using a helper method
	 */
	public DictEntry largest() {
		return largestHelper(root).getDict();
	}

	/*
	 * private helper method that finds the largest node in the tree and returns
	 * this node, or null otherwise
	 */
	private Node largestHelper(Node node) {
		if (node == null) {
			return null;
		}
		// recursive call the find the largest node
		if (node.getRight() == null) {
			return node;
		} else {
			return largestHelper(node.getRight());
		}
	}

}

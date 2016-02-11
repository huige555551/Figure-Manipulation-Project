//Author: Neeraja Murali Dharan 
//Last Modified: November 20th, 2015
//FileName: Node.java
/*Description: This class represents the Node object inserted into the 
binary search tree */

/*Fields in the Node object are a DictEntry object, 
 * left and right child nodes and parent node
 */
public class Node {
	private DictEntry dict; 
	private Node left; 
	private Node right;
	private Node parent;

	/*Constructor initialises DictEntry object in the node and 
	 * initalizes left, right child nodes and parent node to null
	 */
	public Node(DictEntry dict){
		this.dict=dict; 
		left=null;
		right=null;
		parent=null;
	}

	//getter method returns left child node
	public Node getLeft(){
		return left; 
	}

	//setter method sets value of left child node to the node sent as a parameter
	public void setLeft(Node left){
		this.left=left; 
	}

	//getter method returns the right child node
	public Node getRight(){
		return right; 
	}

	//setter method sets value of right child node to node sent as a parameter
	public void setRight(Node right){
		this.right=right;
	}

	//getter method returns DictEntry object in node 
	public DictEntry getDict(){
		return dict; 
	}

	//setter method sets DictEntry object in node to DictEntry object sent in as parameter
	public void setDict(DictEntry dict){
		this.dict=dict;
	}

	//getter method returns the parent of node 
	public Node getParent(){
		return this.parent; 
	}

	//setter method sets the parent of node to node sent in as parameter 
	public void setParent(Node parent){
		this.parent=parent;
	}

}

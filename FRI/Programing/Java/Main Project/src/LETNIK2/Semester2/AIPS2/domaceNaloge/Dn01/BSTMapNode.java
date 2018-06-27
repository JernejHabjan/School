package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn01;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;

//Implementation of the (unbalanced) Binary Search Tree set node.
public class BSTMapNode {
	private static int counter;
	private BSTMapNode left, right, parent;
	private int key;
	private String value;

	public BSTMapNode(BSTMapNode l, BSTMapNode r, BSTMapNode p,
			int key, String value) {
		super();
		this.left = l;
		this.right = r;
		this.parent = p;
		this.key = key;
		this.value = value;
	}

	public BSTMapNode getLeft() {
		return left;
	}
	public void setLeft(BSTMapNode left) {
		this.left = left;
	}
	public BSTMapNode getRight() {
		return right;
	}
	public void setRight(BSTMapNode right) {
		this.right = right;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int compare(BSTMapNode node) { counter++; return node.key-this.key;}
	public int getCounter() {
		return counter;
	}
	public void resetCounter() {
		counter = 0;
	}

	public boolean add(BSTMapNode element) {

		BSTMapNode current = this;
		//kaj ce ze obstaja value -> return false

		if (element.key < current.key){ //prvi < drugi
			if(current.left == null) {
				current.left = element;
				return true;
			}else
				return current.left.add(element);
		}else if(element.key > current.key){
			if(current.right == null) {
				current.right = element;
				return true;
			}else
				return current.right.add(element);
		}return false;
	}

	void deconstructor(){
		left = null;
		right = null;
		parent = null;
		key = -1;
		value = null;
	}

	void deconstructor(BSTMapNode element){
		left = element.left;
		right = element.right;
		parent = element.parent;
		key = element.key;
		value = element.value;
	}

	public boolean remove(BSTMapNode element) {

		BSTMapNode root = this;
		BSTMapNode parent = this;
		BSTMapNode current = this;

		//poiscemo ali je left ali right child
		boolean isLeftChild = false; // ali je current levi ali desni sin od svojga parenta
		while(element.key != current.key){ //primerjaj dokler ga ne dobis
			parent = current;
			if(current.key > element.key){
				isLeftChild = true;
				current = current.left;

			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){ //ga ni
				return false;
			}
		}

		//1. No Children
		if(current.left == null && current.right == null){
			if(current.key == root.key) root.deconstructor();
			if(isLeftChild) parent.left = null;
			else parent.right = null;
		}

		//2. One child
		else if(current.right == null){
			if(current.key == root.key) root.deconstructor(current.left); //cej root ga skensla
			else if (isLeftChild) parent.left = current.left;//- ga sam preveze na kida od currenta
			else parent.right = current.left; //right child od parenta postane currentov left - narisan
		}
		else if(current.left == null){
			if(current.key == root.key) root.deconstructor(current.right);
			else if (isLeftChild) parent.left = current.right;
			else parent.right = current.right;
		}

		//3. Two children

		else { //nben ni null
			BSTMapNode minInSubtree = current.findMin();
			System.out.println(minInSubtree.key);

			if(current.key == root.key) root.deconstructor(minInSubtree);
			else if(isLeftChild) parent.left = minInSubtree;
			else parent.right = minInSubtree;
			minInSubtree.left = current.right;
		}


		return true;


	}

	public boolean contains(BSTMapNode element) {
		BSTMapNode current = this;


		int compare = element.compare(current);

		if(compare == 0)
			return true;
		if(compare > 0 && current.left != null){
			return current.left.contains(element);
		}else if(current.right != null){
			return current.right.contains(element);
		}return false;


	}

	public String get(BSTMapNode element) {
		BSTMapNode current = this;



		if(element.key == current.key)
			return current.value;
		if(element.key < current.key && current.left != null){
			return current.left.get(element);
		}else{
			if(current.right != null){
				return current.right.get(element);

			}else{
				return null;
			}
		}
	}

	private BSTMapNode findMin() { //smallest element in subtree

		BSTMapNode current = this;
		if (current.left == null)
			return current;
		else
			return current.left.findMin();
	}

	List<Integer> traversePreOrder() {
		List<Integer> order = new ArrayList<Integer>();

		BSTMapNode current = this;

		order.add(current.key);

        if(current.left != null) //-> ce je levi sin in obstaja
        	order.addAll(current.left.traversePreOrder());
		if(current.right != null) //-> ce je levi sin in obstaja
			order.addAll(current.right.traversePreOrder());
        return order;
	}

	List<Integer> traverseInOrder() {
		List<Integer> order = new ArrayList<Integer>();

		BSTMapNode current = this;

		if(current.left != null) //-> ce je levi sin in obstaja
			order.addAll(current.left.traverseInOrder());
		order.add(current.key);
		if(current.right != null) //-> ce je levi sin in obstaja
			order.addAll(current.right.traverseInOrder());
		return order;
	}

	List<Integer> traversePostOrder() {
		List<Integer> order = new ArrayList<Integer>();

		BSTMapNode current = this;

		if(current.left != null) //-> ce je levi sin in obstaja
			order.addAll(current.left.traversePostOrder());

		if(current.right != null) //-> ce je levi sin in obstaja
			order.addAll(current.right.traversePostOrder());
		order.add(current.key);
		return order;
	}

	List<Integer> traverseLevelOrder() {
		//misel 1 -> da naredim DFS z iterativnim poglabljanjem

		List<Integer> order = new ArrayList<Integer>();
		BSTMapNode root = this;

		int h = root.height();

		for(int i = 1; i <= h; i++){
			order.addAll(getLevel(root, i));
		}

		return order;
	}

	private int height(){

		BSTMapNode node = this;
		int leftHeight = 0;
		int rightHeight = 0;

		if(node.left != null)
			leftHeight = node.left.height();
		else if(node.right != null)
			rightHeight = node.right.height();

		//izberemo vecjo globino
		return max(leftHeight, rightHeight) +1;

	}

	private List<Integer> getLevel(BSTMapNode node, int level){
		List<Integer> order = new ArrayList<Integer>();

		if(level == 1 && node != null){
			order.add(node.key);

		}else{
			if(node!= null){
				if(node.left != null){
					order.addAll(getLevel(node.left, level -1));
				}
				if(node.right!=null){
					order.addAll(getLevel(node.right, level - 1));
				}
			}

		}
		return order;


	}

}

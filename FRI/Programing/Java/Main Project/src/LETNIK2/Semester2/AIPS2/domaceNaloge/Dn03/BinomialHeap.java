package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn03;


import java.util.Vector;

/**
 * This class is an implementation of the Binomial min-heap.
 */
public class BinomialHeap {
	Vector<BinomialNode> data; // list of root nodes
	int n;                     // number of elements
	
	BinomialHeap(){
		data = new Vector<BinomialNode>();
	}
	
	/**
	 * Inserts a new key to the binomial heap and consolidates the heap.
	 * Duplicates are allowed.
	 * 
	 * @param key Key to be inserted
	 * @return True, if the insertion was successful; False otherwise.
	 */
	public boolean insert(int key) {
		BinomialNode node = new BinomialNode(key);
		data.add(node);
		consolidate();
		++n;
		return true;
	}
	
	/**
	 * Returns the minimum element in the binomial heap. If the heap is empty,
	 * return the maximum integer value.
	 * 
	 * @return The minimum element in the heap or the maximum integer value, if the heap is empty.
	 */
	public int getMin() {
		BinomialNode minNode = null;
		
		for (BinomialNode node: data){
			if (minNode == null || minNode.compare(node) < 0){
				minNode = node;
			}
		}
		
		return minNode != null ? minNode.getKey() : Integer.MAX_VALUE;
	}
	
	/**
	 * Find and removes the minimum element in the binomial heap and
	 * consolidates the heap.
	 * 
	 * @return True, if the element was deleted; False otherwise.
	 */
	public boolean delMin() {
		if(data.size() == 0)
			return false;

		int bn = getMin();
		if (bn != Integer.MAX_VALUE){
			for (BinomialNode bn1: data) {
				if(bn1.getKey() == bn){
					data.remove(bn1);

					n--;
					consolidate(); // todo MANJKA CONSOLIDATE??????
					return true;
				}

			}
		}
		return false;
	}
	
	/**
	 * Merges two binomial trees.
	 * 
	 * @param t1 The first tree
	 * @param t2 The second tree
	 * @return Returns the new parent tree
	 */
	public static BinomialNode mergeTrees(BinomialNode t1, BinomialNode t2) { //-todo -- not sure ce dela

		if(t1.compare(t2) > 0){ //ce prvi vecji od 2. -> vrne -1
			t1.addChild(t2);
			return t1;
		}else{ //ce prvi mansji ali enak od 2.
			t2.addChild(t1);
			return t2;
		}


	}
	
	/**
	 * This function consolidates the binomial heap ie. merges the binomial
	 * trees with the same degree into a single one.
	 * 
	 * @return True, if changes were made to the list of root nodes; False otherwise.
	 */

	private boolean consolidate() {
		data.sort(BinomialNode::compare);

		for (BinomialNode bn1: data) {
			for (BinomialNode bn2: data) {
				if(bn1 != bn2 && bn1.getDegree() == bn2.getDegree()){ //ce mata stopno enako, damo enga pod druzga
					if(bn1.compare(bn2) > 0){ //ce prvi vecji od 2. -> vrne -1
						bn1.addChild(bn2);
						data.remove(bn2);
						//System.out.println(bn2.getKey() + " added as kid to " + bn1.getKey());
						return true;

					}else{ //ce prvi mansji ali enak od 2.
						bn2.addChild(bn1);
						data.remove(bn1);
						//System.out.println(bn1.getKey() + " added as kid to " + bn2.getKey());
						return true;
					}
				}

			}
		}


		//System.out.println();

		return false;

	}
}


package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02;

/**
 * Hash map with open addressing.
 */
public class HashMapOpenAddressing {
	private Element table[]; // table content, if element is not present, use Integer.MIN_VALUE for LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element's key
	private HashFunction.HashingMethod h;
	private CollisionProbeSequence c;
	
	public static enum CollisionProbeSequence {
		LinearProbing,    // new h(k) = (h(k) + i) mod m
		QuadraticProbing, // new h(k) = (h(k) + i^2) mod m
		DoubleHashing     // new h(k) = (h(k) + i*h(k)) mod m
	};


	public HashMapOpenAddressing(int m, HashFunction.HashingMethod h, CollisionProbeSequence c) {
		this.table = new Element[m];
		this.h = h;
		this.c = c;
		
		// init empty slot as MIN_VALUE
		for (int i=0; i<m; i++) {
			table[i] = new Element(Integer.MIN_VALUE, "");
		}
	}

	public Element[] getTable() {return this.table;}

	private int getIndex(int k){
		if (this.h== HashFunction.HashingMethod.DivisionMethod)
			return HashFunction.DivisionMethod (k, this.table.length);
		else if(this.h == HashFunction.HashingMethod.KnuthMethod)
			return HashFunction.KnuthMethod (k, this.table.length);
		else return Integer.MIN_VALUE;
	}

	private int getOpenIndex(int H_k, Element ele){
		int tableLength = this.table.length;

		int index= H_k ;
		for (int i = 0; i < tableLength; ++i) {
			if(!this.table[index].equals(ele)){
				switch (this.c){
					case LinearProbing:
						index = (index + 1) % tableLength;
						break;
					case QuadraticProbing:
						index = (H_k + (int)Math.pow(i + 1, 2)) % tableLength;
						break;
					case DoubleHashing:
						index = (H_k + i * H_k) % tableLength;
						break;
				}
			}
		}return index;
	}


	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 *
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @param v LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		int startIndex = getIndex(k);
		Element ele = new Element(Integer.MIN_VALUE, "");

		int index= getOpenIndex(startIndex, ele);

		if (this.table[index].equals(ele)){ //if its empty - min value
			this.table [index] = new Element(k,v);
			return true;
		}
		return false;
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		int startIndex = getIndex(k);
		Element ele = new Element(k, "");

		int index= getOpenIndex(startIndex, ele);

		if (this.table[index].equals(ele)) { //if its NOT empty
			this.table[index].key = Integer.MIN_VALUE;
			return true;
		}return false;
	}

	/**
	 * Finds the element.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		int startIndex = getIndex(k);
		Element ele = new Element(k, "");

		int index= getOpenIndex(startIndex, ele);

		return this.table[index].equals(ele);
	}

	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		int startIndex = getIndex(k);
		Element ele = new Element(k, "");

		int index= getOpenIndex(startIndex, ele);

		return this.table[index].value;
	}
}


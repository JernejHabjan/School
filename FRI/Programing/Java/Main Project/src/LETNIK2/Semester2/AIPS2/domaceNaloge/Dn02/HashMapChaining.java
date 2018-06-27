package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02;

import java.util.LinkedList;

/**
 * Hash map employing chaining on collisions.
 */
public class HashMapChaining {
	private LinkedList<Element> table[];
	private HashFunction.HashingMethod h;
	
	public HashMapChaining(int m, HashFunction.HashingMethod h) {
		this.h = h;
		this.table = new LinkedList[m];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<Element>();
		}
	}
	
	public LinkedList<Element>[] getTable() {
		return this.table;
	}


	public int getIndex(int k){
		if (this.h == HashFunction.HashingMethod.DivisionMethod)
			return HashFunction.DivisionMethod(k, this.table.length);
		else if(this.h == HashFunction.HashingMethod.KnuthMethod)
			return HashFunction.KnuthMethod(k, this.table.length);
		else return Integer.MIN_VALUE;
	}


	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @param v LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		Element ele = new Element(k, v);

		int index = getIndex(k);

		if (!this.table[index].contains(ele)) {
			this.table[index].add(ele);
			return true;
		}return false;
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		Element ele = new Element(k, "");

		int index = getIndex(k);
		return this.table[index].remove(ele);
	}

	/**
	 * Finds the element.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		Element ele = new Element(k, "");

		int index = getIndex(k);

		return this.table[index].contains(ele);
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02.Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */

	public String get(int k) {
		Element ele = new Element(k, "");

		int index = getIndex(k);

		LinkedList<Element> ll = this.table[index];
		for (Element e : ll){ //iterate linearly
			if(e.equals(ele))
				return e.value;
		}return null;




	}
}


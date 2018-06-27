package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn00;


public class ReverseStringFindMax {
	/**
	 * This function takes the string argument and reverses it.
	 * 
	 * @param str Input string.
	 * @return Reverse version of the string or null, if str is null.
	 */
	public String reverseString(String str) {
		if(str == null) return null;

		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * This function finds and returns the maximum element in the given array.
	 * 
	 * @param arr Initialized input array.
	 * @return The maximum element of the given array, or the minimum Integer value, if array is empty.
	 */
	public int findMax(int[] arr){

		if(arr.length == 0) return Integer.MIN_VALUE;

		//find max
		int max = arr[0];
		for(Integer i : arr)
			if(i>max) max = i;
		return max;

	}
}

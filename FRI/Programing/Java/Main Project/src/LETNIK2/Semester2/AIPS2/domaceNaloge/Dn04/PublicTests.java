package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class PublicTests extends TestCase {
	SuffixArrayIndex sa;
	
	final String text = "mississippi";
	
	protected void setUp() throws Exception {
		sa = new SuffixArrayIndex(text);
	}
	
	public void testSuffixArray() {
		int[] resultingSA = new int[]{10,7,4,1,0,9,8,6,3,5,2};
		assertTrue(Arrays.equals(resultingSA, sa.getSuffixArray()));
	}


	public void testsuffixSuffixCompare(){ ///// TODO NEVEM ČEJ GOOD
		assertTrue(sa.suffixSuffixCompare(2,4));

		assertTrue(sa.stringSuffixCompare("ana", 2));
		assertTrue(sa.stringSuffixCompare("issippi", 2));
	}


	public void testsandbox(){
		System.out.println("ss".compareTo("ssipi"));
	}

	public void testLocate() {
		String query = "s";
		Set<Integer> locations = new HashSet(Arrays.asList(2,3,5,6));
		
		assertEquals(locations, sa.locate(query));
	}

	public void testLongestRepeatedSubstring() {
		System.out.println("issippi".compareTo("ississippi"));
		assertEquals("issi", sa.longestRepeatedSubstring());
	}

	public void testLongestCommonPrefixLen() {
		assertEquals(0, sa.longestCommonPrefixLen(0, 1)); // none
		assertEquals(1, sa.longestCommonPrefixLen(4, 7)); // i
		assertEquals(4, sa.longestCommonPrefixLen(1, 4)); // issi
	}
}

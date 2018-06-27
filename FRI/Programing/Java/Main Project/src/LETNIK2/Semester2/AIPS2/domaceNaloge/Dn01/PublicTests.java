package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn01;

import java.util.List;

import junit.framework.TestCase;

public class PublicTests extends TestCase {
	private BSTMap bst;

	protected void setUp() throws Exception {
		bst = new BSTMap();
	}

	public void testBSTContainsGet() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.contains(4000));
		assertEquals(bst.get(4000), "Kranj");
	}
	
	public void testBSTNotContainsGet() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertFalse(bst.contains(7000));
		assertEquals(bst.get(7000), null);
	}
	
	public void testBSTRemove() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
	}
	
	public void testBSTNumberOfCompares() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		bst.resetCounter();
		bst.contains(5000);
		assertEquals(5, bst.getCounter());
	}
	
	public void testBSTTraversePreOrder() {
		bst.add(4, "Kranj");
		bst.add(3, "Maribor");
		bst.add(1, "Koper");
		bst.add(8, "Celje");
		bst.add(10, "Novo Mesto");
		bst.add(11, "Kranj");
		bst.add(15, "Maribor");
		bst.add(16, "Koper");


		List<Integer> neki = bst.traversePostOrder();
		for(Integer i : neki){
			System.out.println(i);
		}

	}

	public void testLevelOrder() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		List<Integer> neki = bst.traverseLevelOrder();
		for(Integer i : neki){
			//System.out.println(i);
		}

	}
}

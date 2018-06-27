package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn02;


	 import static java.lang.Math.sqrt;

	 public class HashFunction {
	 public static enum HashingMethod {
	 DivisionMethod,
	 KnuthMethod
	 };

	 /**
	 * Hash function using division method.
	 * If negative key is given, first multiply it by -1.
	 *
	 * @param k Key
	 * @param m Table size
	 * @return Index in the table of size m.
	 */
	public static int DivisionMethod(int k, int m) {
		return k < 0 ? -k % m : k % m;

	}

	/**
	 * Hash function using multiplication method implemented by Knuth:
	 * h(k) = m (k A mod 1)
	 * 
	 * Where A is the inverse golden ratio (\sqrt(5)-1)/2.
	 * Use double precision number type.
	 * If negative key is given, first multiply it by -1.
	 * 
	 * @param k Key
	 * @param m Table size
	 * @return Index in the table of size m.
	 */
	public static int KnuthMethod(int k, int m) {
		double A = (sqrt(5) - 1) / 2.0f;
		return k < 0 ? (int)(m * (-k * A % 1)) : (int)(m * (k * A % 1));
	}
}
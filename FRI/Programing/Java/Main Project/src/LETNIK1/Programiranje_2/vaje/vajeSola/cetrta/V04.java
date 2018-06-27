package LETNIK1.Programiranje_2.vaje.vajeSola.cetrta;

public class V04 {
	public static long getCrko(int[] vrstice) {
		long rezultat = 0;
		for (int i = 0; i < vrstice.length; i++) {
			rezultat = (long) (rezultat << 8) + vrstice[i];
		}
		return rezultat;
	}

	public static void izpisi(long crke[]) {
		for (int i = 7; i >= 0; i--) {
			for (int s = 0; s < crke.length; s++) {
				for (int j = 7; j >= 0; j--) {
					long bit = (long) 1 << (8 * i + j);
					System.out.print((crke[s] & bit) == bit ? "*" : " ");
				}
				System.out.print("  ");
			}
			System.out.println();
		}
	}

	public static void izpisi(long crka) {
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				long bit = (long) 1 << (8 * i + j);
				long in = (long) (crka & bit);
				System.out.print((crka & bit) == bit ? "*" : " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		izpisi(71917410474557695l);

		long crkaO = getCrko(new int[]{60, 66, 129, 129, 129, 129, 66, 60});
		izpisi(crkaO);

		izpisi(new long[]{
				4821103401091611672l, 0, 144680345680364600l, 1739555224076567106l,
				-9114862049243683816l, 1739555224076567106l, 0, 4821103401091611672l
		});
	}
}
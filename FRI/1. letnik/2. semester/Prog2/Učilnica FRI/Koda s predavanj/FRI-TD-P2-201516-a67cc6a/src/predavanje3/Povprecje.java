import java.util.*;

// Program iz tipkovnice bere ocene in na koncu izpise 
// povprecje vseh prebranih ocen.
public class Povprecje {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int ocena;
		int vsota = 0;  // vsota vseh prebranih ocen
		int n     = 0;  // stevilo vseh prebranih ocen
		do {
		  System.out.print("Vpisi oceno: ");
		  ocena = sc.nextInt();

		  vsota = vsota + ocena;
		  n = n + 1;		  
		} while (ocena != 0);

		// stevec ocen smo enkrat prevec povecali (pri branju zadnje
		// ocene z vrednostjo 0), zato ga moramo zmanjsati za 1
		n = n - 1;

		if (n == 0) {
			System.out.println("Nisi vpisal ocen!");
		} else {
		  // operator / je za celoštevilsko deljenje
		  double povprecje = 1.0 * vsota / n;
		  System.out.printf("Povprecje %d prebranih ocen je %.2f\n", n, povprecje);
		}
	}
}
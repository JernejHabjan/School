import java.util.*;

// Program iz tipkovnice prebere ime in starost osebe
class BranjeTipkovnice {
	
	public static void main(String args[]) {
		// z objektom sc se povezemo s tipkovnico ...
		Scanner sc = new Scanner(System.in);

		System.out.print("Vpisi ime: ");		
		// ... in preberemo najprej ime (String), ...
		String ime = sc.next();

		System.out.print("Vpisi starost: ");
		// ... nato pa se starost (int)
		int starost = sc.nextInt();		

		System.out.printf("Pozdravljen, %s, star si %d let.\n", ime, starost);		
	}
}
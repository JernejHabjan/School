// Program izpise statistiko niza.
public class StatistikaNiza {
	
	public static void main(String args[]) {
		String niz = "Danes je res lep dan!";

		System.out.println("Niz:  " + niz);
		System.out.printf ("Prva   crka: %s\n", niz.substring(0,1));
		// System.out.printf ("Prva   crka: %c\n", niz.charAt(0));
		System.out.printf ("Zadnja crka: %c\n", niz.charAt( niz.length() - 1 ));
		System.out.printf ("Brez presledkov: %s\n", niz.replaceAll(" ", ""));

		int steviloBesed=1;
		for(int i=0; i < niz.length(); i=i+1) {
			if (niz.charAt(i) == ' ') {
				steviloBesed = steviloBesed + 1;
			}
		}
		System.out.printf ("Stevilo besed: %d\n", steviloBesed);

		System.out.printf ("Obrnjen niz: ");
		for(int i=0; i < niz.length(); i=i+1) 
			System.out.print(niz.charAt(niz.length()-1-i));

		System.out.println();


	}
}
import java.util.*;
import java.io.File;

// Program izpise vsebino datoteke, katere ime je podano v 
// prvem argumentu ob klicu programa.
public class IzpisDatoteke {
	
	public static void main(String args[]) throws Exception {
		// ime datoteke je podano v prvem argumentu
		String imeDatoteke = args[0];

		// objekt sc povezemo z datoteko
		Scanner sc = new Scanner(new File(imeDatoteke));

		// dokler so v datoteki se podatki ...
		while (sc.hasNextLine()) {
			// ... beremo po eno vrstico
			String vrstica = sc.nextLine();

			System.out.println(vrstica);
		}

		// na koncu moramo datoteko OBVEZNO zapreti!
		sc.close();
	}
}
// Program izracuna obresti na vezano vlogo
public class Obresti {
	public static void main(String args[]) {
		// stevilo let
		int n;    // n bo celo   stevilo

		// obrestna mera
		double p; // p do realno steilo

		// glavnica
		double G;

		// koncna vsota
		double Gn;

		n = 5;    // 5 let
		p = 4;    // 4% obrestna mera
		G = 1000; // glavnica je 1000 EUR

		// izracun stanja po formuli iz prosojnice
		Gn = G * Math.pow(1+p/100, n);

		System.out.println("Glavnica:            " + G);
		System.out.println("Stevilo let:         " + n);
		System.out.println("Obrestna mera (v %): " + p);
		System.out.println("Stanje na racunu:    " + Gn);
	}
}
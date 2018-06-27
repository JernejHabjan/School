package predavanje11.vmesnik;

/**
 * Abstrakten razred, ki opisuje funkcijo: funkcija je vsak razred, 
 * ki ima metode vrednost() in odvod().
 * Ker na tem nivoju delovanje metod vrednost() in odvod() se ni
 * znano, smo te metode naredili abstraktne.
 */
public interface Funkcija {
  public double vrednost(double x);
  public double odvod   (double x);  
}

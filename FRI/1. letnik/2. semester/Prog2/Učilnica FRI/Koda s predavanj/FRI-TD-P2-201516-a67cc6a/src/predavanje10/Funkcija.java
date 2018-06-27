package predavanje10;

/**
 * Abstrakten razred, ki opisuje funkcijo: funkcija je vsak razred, 
 * ki ima metode vrednost() in odvod().
 * Ker na tem nivoju delovanje metod vrednost() in odvod() se ni
 * znano, smo te metode naredili abstraktne.
 */
abstract public class Funkcija {
  abstract double vrednost(double x);
  abstract double odvod   (double x);
}


import java.util.Random;

// Program izpise loto listek
public class Loto {

  // Metoda izpise n stevil med 1 in 39
  static void izpisiLoto(int n) {
    Random rnd = new Random();

    for (int i = 1; i <= n; i = i + 1) {
      int s = rnd.nextInt(39) + 1;

      System.out.printf("%d ", s);
    }
    System.out.println();
  }

  public static void main(String args[]) {
    // veckrat klicem isto metodo z razlicnimi parametri
    izpisiLoto(7);  // izpis sedmih stevil
    izpisiLoto(8);  // izpis osmih  stevil
    izpisiLoto(9);  // izpis devetih stevil
  }

}

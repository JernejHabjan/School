package predavanje6;

/**
 * Preprost program za demonstracijo javanskih komentarjev.
 * 
 * @author Tomaz Dobravec
 * @version (1.0), 2016
 */
public class Pozdrav {
  /**
   * Ob klicu "java Pozdrav ime n" program n-krat izpise 
   * "Pozdravljen, ime!"
   * <p>
   * 
   * Primer klica: java Pozdrav Tomaz 5
   * <p>
   * 
   * Za izpis se uporablja metoda {@link #izpisi}
   * 
   * @param args
   *          Tabela argumentov ob klicu programa 
   *           prvi argument: ime, 
   *          drugi argument: stevilo izpisov
   * 
   */
  public static void main(String[] args) {
    // program zahteva dva argumenta
    if (args.length != 2) {
      izpisiNacinUporabe();
      System.exit(1);
    }

    int n; // stevilo izpisov
    n = Integer.parseInt(args[1]);

    String ime = args[0];

    izpisi(n, ime);
  }

  /**
   * Izpis nacina uporabe programa Pozdrav
   */
  public static void izpisiNacinUporabe() {
    System.out.println("Uporaba programa: java Pozdrav ime n");
  }

  /**
   * Metoda n-krat izpise besedilo "Pozdravljen, ime" 
   * (klice se iz metode {@link #main})
   * 
   * @param n
   *          ... izpisov
   * @param ime
   *          ... ime, ki se izpisuje
   * @return stevilo znakov v imenu
   * @see Pozdrav
   */
  public static int izpisi(int n, String ime) {
    for (int i = 0; i < n; i++) {
      System.out.println("Pozdavljen, " + ime);
    }
    return ime.length();
  }
}
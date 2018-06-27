package si.fri.math.geometrija;

public class Trikotnik {
  // stranice trikotnika
  static int a=3;
  static int b=4;
  static int c=5;
  
  public static void izpis() {
    System.out.printf("Obseg trikotnika s stranicami "
            + "%d, %d in %d je %d\n", a, b, c, a+b+c);
  }
  
  public static void main(String[] args) {
    izpis();
  }
}

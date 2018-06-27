package predavanje8;

/**
 * Napisi program za risanje grafov matematiènih 
 * funkcij na tekstoven zaslon.
 *
 */
public class Graf {

  static int W = 79;
  static int H = 25;

  static char[][] zaslon = new char[H][W];

  static void zbrisiPodatkeVTabeli() {
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        zaslon[i][j] = ' ';
      }
    }
  }

  static void koordinatniSistem() {
    for (int i = 0; i < W; i++) {
      zaslon[H / 2][i] = '-';
    }
    for (int i = 0; i < H; i++) {
      zaslon[i][W / 2] = '|';
    }
  }

  static void izpisi() {
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        System.out.print(zaslon[i][j]);
      }
      System.out.println();
    }
  }

  static void graf() {
    double x1 = -2 * Math.PI;
    double x2 = 2 * Math.PI;
    double y1 = -1;
    double y2 = 1;

    for (int i = 0; i < W; i++) {
      double x = i * (x2 - x1) / W + x1;

      double y = 1.0 / 2 * Math.sin(x);

      int j = (int) Math.floor(H * (y - y1) / (y2 - y1));

      if (j >= 0 && j < H) {
        zaslon[H - j - 1][i] = '*';
      }
    }

  }

  public static void main(String[] args) {
    zbrisiPodatkeVTabeli();
    koordinatniSistem();
    graf();
    izpisi();
  }

}

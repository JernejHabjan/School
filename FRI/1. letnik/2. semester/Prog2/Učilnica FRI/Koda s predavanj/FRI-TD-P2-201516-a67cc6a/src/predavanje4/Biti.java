package predavanje4;

/**
 * Program izpise stevilo prizganih bitov v danem stevilu
 */
public class Biti {

  // metoda vrne stevilo prizganih bitov v stevilu n
  // pri tem uporabi samo bitne operatorje
  static int steviloBitov(int n) {
    int steviloPrizganih = 0;
    while (n != 0) {
      steviloPrizganih += (n & 1);
      n = n >> 1;
    }
    return steviloPrizganih;
  }

  public static void main(String args[]) {
    int x = 255;

    int sB = steviloBitov(x);
    System.out.println(sB);
  }
}

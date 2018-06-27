package predavanje6a;

/**
 * Razred3 ni v istem paketu kot Razred1, zato vidim le 
 * javne (public) atribute razreda Razred1.
 * 
 * @author tomaz
 */
public class Razred3 {
  public static void main(String[] args) {
    System.out.println(predavanje6.Razred1.publicX);
    // System.out.println(predavanje6.Razred1.protectedX);
    // System.out.println(predavanje6.Razred1.packageX);
    // System.out.println(predavanje6.Razred1.privateX);
  }
}

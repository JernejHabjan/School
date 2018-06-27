package predavanje6;

/**
 * Ker je razred Razred2 v istem paketu kot Razred1, potem 
 * vidim vse atribute, razen privateX, ki je private.
 * 
 * @author tomaz
 */
public class Razred2 {
  public static void main(String[] args) {
    System.out.println(Razred1.publicX);
    System.out.println(Razred1.protectedX);
    System.out.println(Razred1.packageX);
    // System.out.println(Razred1.privateX);
  }
}

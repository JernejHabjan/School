package predavanje6a;

/**
 * Razred4 je naslednik (extends) razreda Razred1, zato 
 * poleg javnih vidim tudi zascitene (protedted) atribute 
 * razreda Razred1.
 * 
 * @author tomaz
 */
public class Razred4 extends predavanje6.Razred1 {

  public static void main(String[] args) {
    System.out.println(predavanje6.Razred1.publicX);
    System.out.println(predavanje6.Razred1.protectedX);
    //System.out.println(predavanje6.Razred1.packageX);
    // System.out.println(predavanje6.Razred1.privateX);
  }
  
}

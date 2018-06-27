package predavanje9;

/**
 *
 * @author tomaz
 */
public class TestMath {
  
  public static void main(String[] args) {  
    // metode iz razreda Math lahko klicem "na razredu"
    // (ne potrebujem primerka razreda Math)
    // Zakaj? Ker so metode staticne!
    // Prav tako so staticne konstante (Pi in E) in zato
    // jih lahko uporabljam na razredu.
    Math.sin(3.14);    
    System.out.println(Math.PI);
    
    // Ni treba takole:
    // Math m = new Math();
    // m.sin(3.14);    
  }

}

package predavanje6;

/**
 * Demonstracija dostopnostnih dolocil. Razred ima 4 atribute 
 * (publicX, protectedX, packageX in privateX). V razredih 
 * Razred2, predavanje6a.Razred3 in predavanje6a.Razred4
 * ti atributi niso vedno vidni. 
 * 
 * @author tomaz
 */
public class Razred1 {

  public    static int publicX = 5;
  protected static int protectedX = 13;
            static int packageX = 42;
  private   static int privateX = 100;

  public static void main(String[] args) {
    System.out.println(publicX);
    System.out.println(protectedX);
    System.out.println(packageX);
    System.out.println(privateX);
  }
}

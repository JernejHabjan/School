package predavanje5;

/**
 * Naloga: napisi program, ki izpise stevilo dni, ki so 
 * minili do danega datuma
 * @author tomaz
 */
public class SteviloDni {

  // V tej metodi uporabimo ukaz switch BREZ dodanih stavkov 
  // "break". Ko racunamo dni do nekega meseca (recimo aprila), 
  // moramo pristeti dneve vseh predhodnih mesecev (marec, 
  // februar, januar). Zato je pravilno, da z switch "skocimo" 
  // na pravo mesto (v tem primeru 4) in nadaljujemo s 
  // pristevanjem vrednosti za vse prejsnje mesece.
  public static int steviloDni(int mesec, int dan) {
    int dni = 0;
    
    mesec--;
    switch (mesec) {
      case 11: dni += 30;      
      case 10: dni += 31;
      case 9:  dni += 30;
      case 8:  dni += 31;
      case 7:  dni += 31;
      case 6:  dni += 30;
      case 5:  dni += 31;
      case 4:  dni += 30;
      case 3:  dni += 31;
      case 2:  dni += 28;
      case 1:  dni += 31;
    }
    
    dni += dan;  // pristejem se dni tekocega meseca
    return dni;    
  }
  
  public static void main(String[] args) {
    System.out.println(steviloDni(12, 31));
  }
  
}

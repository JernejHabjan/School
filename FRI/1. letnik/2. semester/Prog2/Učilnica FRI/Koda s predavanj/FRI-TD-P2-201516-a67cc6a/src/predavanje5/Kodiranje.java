package predavanje5;

/**
 * Razred, v katerem sta metodi kodiraj() in odkodiraj(). 
 * Razred nima metode main(),saj ni misljeno, da bi se 
 * izvajal samostojno. Metode kodiraj() in odkodiraj() se 
 * uporabljajo v drugih razredih (glej, na primer, 
 * TestKodiranje.java)
 * 
 * @author tomaz
 */
public class Kodiranje {
  
  // zamik pri kodiranju - za koliko znakov zamaknem abecedo
  static int zamik = 3;
  
  // Ker se kodiranje in odkodiranje razlikuje le v malenkosti 
  // (predznak pri izracunu kodirane crke), smo napisali eno 
  // samo metodo, ki jo uporabljata obe omenjeni metodi; prva 
  // to metodo klice s predznak=1, druga pa s predznak=-1
  static String kodirajOdkodiraj(String niz, int predznak) {
    String zakodirano = "";
    for(int i=0; i<niz.length(); i++) {
      char crka = niz.charAt(i);
      // uporabiti moram modulo 26, da ostanem v obmocju 
      // angleske abecede od A do Z
      char kodiranaCrka = 
         (char) ((26 + crka - 'A' + predznak * zamik) % 26 + 'A');
      zakodirano += kodiranaCrka;
    }
    return zakodirano;
  }
  
  public static String kodiraj(String niz) {
    return kodirajOdkodiraj(niz, 1);
  }
  
  public static String odkodiraj(String niz) {
    return kodirajOdkodiraj(niz, -1);  
  }  
}

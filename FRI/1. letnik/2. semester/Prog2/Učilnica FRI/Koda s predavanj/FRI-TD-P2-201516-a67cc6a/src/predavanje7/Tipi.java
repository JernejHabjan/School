package predavanje7;

import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class Tipi {

  public static void main(String[] args) {
    // za "cudne" znake uporabim znak \ - namesto ''' pisem '\''
    char c = '\'';
    
    // pri izpisu se lahko z znakoma \b (znak nazaj) in 
    // \r (na zacetk) pomikam po vrstici levo
    System.out.println("Beseda\b\bDA");
    
    // ce se celostevilska konstanta se zacne z niclo, 
    //  java to razume kot stevilo v osmiskem sistemu
    int oct = 012;  // 12 osmisko == 10 desetisko
    
    // zapis v sesnajstiskem sistemu
    int hex = 0xFF; // FF sesnajstisko == 255 desetisko             
    
    // zapis v dvojiskem
    int bin = 0b101010;  // 101010 dvojisko = 42 desetisko
    
    // za zapis velikega stevila (ki presega obseg int), uporabimo znak l na koncu
    long x1  = 123412342342345234l;
    
    // za zapis stevila tipa float uporabim znak f na koncu
    float x4 = 1.25f;
    

    
    Scanner sc = new Scanner(System.in);
    String ime = sc.next(); // preberem niz (ime) iz tipkovnice
    
    // za primerjavo dveh nizov nikoli NE uporabljam operatorja ==
    // spodnji if bo vedno vrnil false, tudi ce bo uporabnik kot ime
    // vpisal "Lojze"
    if (ime == "Lojze") {
      System.out.println("ime == Lojze");
    } else {
      System.out.println("ime != Lojze");
    }

    // pravilna primerjava nizov je s pomocjo metode equals
    if (ime.equals("Lojze")) {
      System.out.println("ime equals Lojze");
    } else {
      System.out.println("ime !equals lojze");
    }
    
    
    String dolgNiz = "To je  FRI niz";
    // za iskanje podniza v nizu uporabim metodo indexOf, ki vrne
    // indeks prve pojavitve podniza (oziroma -1, ce se podniz ne pojavi)
    int friLocation = dolgNiz.indexOf("FRI");
    System.out.println("FRI je na mestu: " + friLocation);
    
    //             0123456789
    String niz1 = "Danes je lep dan.";
    String niz2 = niz1.substring(3,5);  // preberem znake [3,5), torej "es"
    System.out.println(niz2);
    
    // za zamenjavo dela niza uporabim metodo replaceAll
    // uporaba niz3.replaceAll("A", "X"); 
    // je napacna, saj se niz ne more spreminjati. Pravilna 
    // uporaba je taka: niz3 = niz3.replaceAll("A", "X");
    String niz3 = "BLA BLA BLA";
    niz3 = niz3.replaceAll("A", "X");
    System.out.println(niz3);
  }
  
}

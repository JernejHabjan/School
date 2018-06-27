
import java.util.*;

class Branje {

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Ime: ");
    String ime = sc.nextLine();

    System.out.print("Starost: ");
    int starost = sc.nextInt();

    System.out.print("Stevilka noge: ");
    int noga = sc.nextInt();

    // po branju celega stevila v bralnem pomnilniku
    // ostane znak za prehod v novo vrstico; ta znak 
    // preberem z ukazom nextLine() in tako dosežem, da
    // naslednja prebrana vrstica ne bo prazna!   
    sc.nextLine();

    System.out.print("Mesto: ");
    String naslov = sc.nextLine();

    System.out.printf("Ime: %s, Starost: %d, Noga: %d, Mesto: %s\n",
            ime, starost, noga, naslov);
  }
}

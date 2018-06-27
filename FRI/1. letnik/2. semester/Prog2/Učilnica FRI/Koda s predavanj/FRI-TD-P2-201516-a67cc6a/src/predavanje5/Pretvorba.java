package predavanje5;

/**
 *
 * @author tomaz
 */
public class Pretvorba {
  
  
  // Pretvorba iz niza (String)  v stevilo (int). Pri tem 
  // uporabim formulo, ki je/ opisana na prosojnicah: na 
  // vsakem koraku prejsnjo vrednost pomnozim z osnovo 
  // (10, ce niz predstavlja stevilo v desetiskem oziroma 2, 
  // ce niz predstavlja stevilo v dvojiskem sistemu) in 
  // jih pristejem naslednjo stevko.
  static int string2int(String stevilo, int osnova) {
    int rezultat = 0;
    
    for (int i = 0; i < stevilo.length(); i++) {
      char znak = stevilo.charAt(i); // '0', '1', '2', ...
      rezultat = rezultat * osnova + (znak - '0');
    }
    
    return rezultat;
  }

  // Preoblozena metoda - metoda z istim imenom ze obstaja 
  // (glej zgoraj), a ima drugacno stevilo parametrov - 
  // tista jih ima 2, ta pa le enega. V tej metodi klicem 
  // metodo string2int z dvema parametroma. "Manjkajoci" 
  // parameter nastavim na 2.
  static int string2int(String stevilo) {
    return string2int(stevilo, 10);
  }

  
  public static void main(String[] args) {
    System.out.println(string2int("135"));
    System.out.println(string2int("101010", 2));
  }

}

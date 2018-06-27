package predavanje8;

import java.util.Random;

/**
 *
 * @author tomaz
 */
public class Anagram {
  public static void main(String[] args) {
    String beseda = "PONEDELJEK";
    
    char tabelaZnakov[] = beseda.toCharArray();
    
    Random rnd = new Random();
    int kolikoZamenjav = rnd.nextInt(100);
    for (int x = 0; x < kolikoZamenjav; x++) {
      int i = rnd.nextInt(beseda.length());
      int j = rnd.nextInt(beseda.length());
            
      // .... 
      tabelaZnakov[i] = (char) ((int)tabelaZnakov[i] + (int)tabelaZnakov[j]);
      tabelaZnakov[j] = (char) ((int)tabelaZnakov[i] - (int)tabelaZnakov[j]);
      tabelaZnakov[i] = (char) ((int)tabelaZnakov[i] - (int)tabelaZnakov[j]);
    }
    
    System.out.println(new String(tabelaZnakov));
  }
}

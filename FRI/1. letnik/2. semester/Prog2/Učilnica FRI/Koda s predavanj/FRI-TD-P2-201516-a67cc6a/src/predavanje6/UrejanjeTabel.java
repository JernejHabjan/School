package predavanje6;

import java.util.Arrays;

/**
 * Demonstracija uporabe metode Arrays.sort()
 * @author tomaz
 */
public class UrejanjeTabel {

  
  public static void main(String[] args) {    
    //  ustvarim tabelo in vanjo dam 4 besede
    String nizi[] =  {"abc", "xz", "def", "123"};
    
    // tabelo uredim po abecedi ...
    Arrays.sort(nizi);
    
    // ... in jo izpisem na zaslon
    for (int i = 0; i < nizi.length; i++) {
      System.out.println(nizi[i]);
    }
  }
}

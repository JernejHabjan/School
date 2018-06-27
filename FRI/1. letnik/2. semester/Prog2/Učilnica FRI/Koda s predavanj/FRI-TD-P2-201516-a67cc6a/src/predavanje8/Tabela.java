package predavanje8;

/**
 *
 * @author tomaz
 */
public class Tabela {
  static void uredi(int tabela[]) {
    // ....    
  }
  
  public static void main(String[] args) {
    int tabela[] = {1, 2, 3};
    
    uredi(tabela);
    // pri klicu moram dodati tudi "new int[]" 
    //   - brez tega ne dela!
    uredi(new int[] {1,2,3});
    
    // Razlika med tabelo in nizom pri doloèanju dolzine:
    
    // tabela ima atribut "length"
    System.out.println(tabela.length);
    
    String niz = "abc";
    // razred String pa ima metodo "length()"
    System.out.println(niz.length());
  }
}

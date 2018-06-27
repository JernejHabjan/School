package predavanje10;

/**
 * Demonstracija uporabe tabele splosnega tipa (Drevo). V tabeli hranimo
 * tako dreveda, kot tudi bonsaje. Na drevesih lahko izvajmo metode, ki
 * se deklarirane v razredu Drevo, na bonsajih pa poleg tega se metode, 
 * ki so deklarirane v razredu Bonsai. Med objekti razlikujemo s pomocjo 
 * operatorja instanceof
 * @author tomaz
 */
public class TabelaDreves {
    
  public static void main(String[] args) {
    // Tabela dreves...
    Drevo tabelaDreves[] = new Drevo[2];
    // ... vanjo damo eno Drevo ...
    tabelaDreves[0] = new Drevo(); 
    // ... in en Bonsai
    tabelaDreves[1] = new Bonsai();
    
    // Na vseh objektih lahko klicemo metodo pomlad() 
    for (int i = 0; i < tabelaDreves.length; i++) {
      tabelaDreves[i].pomlad();
    }
    
    // Metodo povecajSirino() pa lahko klicemo le pri bonsajih
    for (int i = 0; i < tabelaDreves.length; i++) {
      // preverimo ce je i-ti objekt Bonsai ...
      if (tabelaDreves[i] instanceof Bonsai) {
        // ... ce je, lahko na objekt pogledamo kot na Bonsai 
        // in klicemo metode, ki jih ima Bonsai
        ((Bonsai)tabelaDreves[i]).povecajSirino();
      }
    }
    
  }
}

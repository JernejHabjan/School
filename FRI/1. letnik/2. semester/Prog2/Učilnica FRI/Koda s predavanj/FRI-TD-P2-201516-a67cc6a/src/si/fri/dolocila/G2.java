package si.fri.dolocila;

public class G2 {
  
  // ker je konstruktor G2 zasciten, ga lahko klicem samo
  // v razredih paketa si.fri.dolocila; v paketu, na primer, 
  // si.fri.tests tega konstruktorja ne morem poklicati
  protected G2() {
    // lahko ustvarim nov objekt, saj je konstruktor G1 zasciten (protected),
    // razred G2 pa se nahaja v istem paketu
    G1 g1 = new G1();
     
    // ker je G2 v istem paketu kot G1, lahko dostopa do 
    // vseh atributov, ki niso privatni v G1
    g1.nasAtribut = 15;
    g1.atributOdVseh=0;
    g1.paketniAtribut=0;
    
    // ne gre, saj je atribut mojAtribut v G1 privatnen
    //g1.mojAtribut=0;
  }
  
}

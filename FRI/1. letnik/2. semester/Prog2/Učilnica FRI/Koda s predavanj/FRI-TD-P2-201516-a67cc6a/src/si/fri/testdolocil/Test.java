package si.fri.testdolocil;

import si.fri.dolocila.*;

public class Test {

  Test() {
    // lahko ustvarim objekt razreda G1, saj je njegov konstruktor javen
    G1 g1 = new G1();
    // ker sem v drugem paketu kot G1, lahko dostopam samo do javnih atributov
    g1.atributOdVseh = 6;
    
    // ostali atrubuti so tu nevidni
    //g1.mojAtribut = 5;    
    //g1.nasAtribut = 7;
    //g1.paketniAtribut = 8;

    // ne morem ustvariti objekta G2, saj je njegov konstruktor
    // zasciten (protected), razred Test pa ni v istem paketu
    //G2 g2 = new G2();
  }
}

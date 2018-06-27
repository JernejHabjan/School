package si.fri.testdolocil;

import si.fri.dolocila.*;

public class ETest extends G1 {
  
  ETest() {
    // v ETest imam dostop samo do javnih (ker so od vseh) 
    // in zascitenih (ker ETest razsirja G1) atributov razreda G1
    atributOdVseh = 6;
    nasAtribut = 7;
    
    // ne gre, saj je mojAtribut privaten
    //mojAtribut = 5;
    // ne gre, saj je paketniAtribut viden le znotraj paketa si.fri.dolocila
    //paketniAtribut = 8;
  }
}

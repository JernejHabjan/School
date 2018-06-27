package si.fri.dolocila;

public class G3 extends G1 {

  private G3() {
    // v G3 imam dostop do vseh atributov G1, razen do skritih
    atributOdVseh = 6;
    nasAtribut = 7;
    paketniAtribut = 8;

    // ne gre, saj je mojAtribut privaten
    // mojAtribut = 5;
  }  
}

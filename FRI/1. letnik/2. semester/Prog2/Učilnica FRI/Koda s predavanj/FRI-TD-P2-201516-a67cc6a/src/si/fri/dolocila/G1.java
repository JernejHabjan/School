package si.fri.dolocila;

public class G1 {
  // privatna atribut, ki bo viden samo znotraj tega razreda  
  private int mojAtribut = 1;
  
  // javni atribut, ki bo viden povsod
  public int atributOdVseh = 2;
  
  // zasciten atribut bo viden vsem razredom, ki so v istem paketu,
  // in vsem podrazredom razreda G1 (tudi v drugih paketih)
  protected int nasAtribut =3;

  // atribut brez dolocila bo viden samo v razredih znotraj tega paketa
  int paketniAtribut=4;

  // javen konstructor, primerke razreda G1 bom lahko 
  // ustvaril v kateremkoli razredu
  public G1() {   
    // v metodah in konstruktorjih razreda vidim 
    // VSE atribute, ki so v njem deklarirani  
    mojAtribut = 5;
    atributOdVseh = 6;
    nasAtribut = 7;
    paketniAtribut = 8;
  } 
}

package predavanje10;

/**
 * Podrazred razreda Drevo. Ker Bonsai razsirja razred Drevo, je od 
 * njega podedoval vse atribute in metode. Od razreda Drevo se torej 
 * razlikuje le po na novo dodanih atributih (sirina) in metodah 
 * (povecajSirino) ter po metodah, ki so v Bonsai na novo napisane.
 */
public class Bonsai extends Drevo {

  // sirina bonsaja v cm
  int sirina;

  void povecajSirino() {
    sirina = sirina + 2;
  }

  @Override
  /**
    *  Vsako pomlad se zgodi isto, kot z vsemi drevesi (super.pomlad()),
    * dodatno pa se poveca sirina
    */
  void pomlad() {
    super.pomlad(); // klicem pomlad() od razreda Drevo

    // klic dodatne metoda za povecanje sirine bonsaja
    povecajSirino();
  }
  
  
  
  @Override
  /**
   * Rast bonsaja je popolnoma drugacna od rasti drevesa, 
   * zato je meto rast() tu na novo napisana.
   */
  void rast() {
    if (getStarost() <= 2)
      visina += 0.05;
  }  

  @Override
  /**
   * Dopolnotev metode toString() - vrne isto kot toString() metoda
   * drevesa, doda pa se dodatek "Sem bonsai"
   */
  public String toString() {
    return super.toString() + ". Sem bonsai";
  }
  
  
}

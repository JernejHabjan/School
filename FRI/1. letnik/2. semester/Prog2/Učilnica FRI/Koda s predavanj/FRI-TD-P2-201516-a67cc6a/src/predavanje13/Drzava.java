package predavanje13;

/**
 * Razred, ki predstavlja eno drzavo.
 * @author tomaz
 */
public class Drzava {
  
  private String kratica;
  private String glavnoMesto;
  private int prebivalci;
  
  Drzava(String kratica, String ime, int prebivalci) {
    this.kratica = kratica;
    this.glavnoMesto = ime;
    this.prebivalci = prebivalci;
  }

  @Override
  public String toString() {
    return String.format("%s: %s, %d", 
            this.kratica, this.glavnoMesto, this.prebivalci);
  }  

  
  public String getGlavnoMesto() {
    return glavnoMesto;
  }
  
  
}

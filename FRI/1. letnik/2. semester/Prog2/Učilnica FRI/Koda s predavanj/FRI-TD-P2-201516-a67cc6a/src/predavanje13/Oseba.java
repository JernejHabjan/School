package predavanje13;

import java.io.Serializable;

/**
 *
 * @author tomaz
 */
public class Oseba implements Comparable, Serializable {
  
  String ime;
  String priimek;
  int starost;
  
  String zadnjicVidel;

  public Oseba(String ime, String priimek, int starost) {
    this.ime = ime;
    this.priimek = priimek;
    this.starost = starost;
  }

  @Override
  public String toString() {
    return String.format("%s %s, %d", ime, priimek, starost);
  }

  @Override
  public int compareTo(Object o) {
    Oseba oseba = (Oseba) o;

//    if (this.starost == oseba.starost)
//      return 0;
//    else if (this.starost < oseba.starost)
//      return -1;
//    else
//      return 1;

    // krajse kot z zgornjim if-stavkom to naredim takole:
    return this.starost - oseba.starost;
  }
  
  
}

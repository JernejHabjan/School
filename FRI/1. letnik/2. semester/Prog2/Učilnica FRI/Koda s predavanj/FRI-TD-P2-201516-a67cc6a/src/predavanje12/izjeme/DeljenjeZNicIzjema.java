package predavanje12.izjeme;

/**
 * Razred: DeljenjZNicIzjema ... razred, ki razsiri nepreverljivo izjemo 
 * RuntimeException.
 * @author tomaz
 */
public class DeljenjeZNicIzjema  extends RuntimeException {

  // Dovolj je, da implementiramo metodo getMessage(); metoda toString()
  // klice metodo getMessage() ter doda informacije o razredu.
  @Override
  public String getMessage() {
    return "Deljenje z nic ni dovoljeno";
  }

  
  
}

package predavanje11.hoteli;

/**
 *
 * @author tomaz
 */
public interface OsnovneZahteve {
  
  public boolean registracija(String ime, int soba);
  
  public String odjava(int soba);
  
  public double promet();
  
}

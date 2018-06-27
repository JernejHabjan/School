package predavanje11.hoteli;

/**
 *
 * @author tomaz
 */
public class Hotel implements OsnovneZahteve {
  
  static double cenaNocitve = 30;
  
  String knjigaGostov[];
  
  String imeHotela; 
  
  int steviloSob;
  
  int zasedenost;
  
  
  Hotel(String imeHotela, int steviloSob) {
    this.imeHotela  = imeHotela;
    this.steviloSob = steviloSob;
    
    // null vrednost v sobi pomeni, da je soba prosta
    knjigaGostov = new String[steviloSob];    
    zasedenost=0;
  }
  
  public static double eur2sit(double eur) {
    return 239.64 * eur;
  }

  @Override
  public boolean registracija(String ime, int soba) {
    zasedenost++;
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String odjava(int soba) {
    zasedenost--;
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public double promet() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

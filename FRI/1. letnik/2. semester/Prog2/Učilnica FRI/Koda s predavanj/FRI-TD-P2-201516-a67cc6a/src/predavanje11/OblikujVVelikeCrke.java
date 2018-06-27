package predavanje11;

public class OblikujVVelikeCrke implements OblikovalecBesedila {

  @Override
  public String oblikujBesedilo(String besedilo) {
    return besedilo.toUpperCase();
  }
  
}

package predavanje11.hoteli;

/**
 *
 * @author tomaz
 */
public class HotelskaVeriga {
  
  public static void main(String[] args) {
    Hotel.cenaNocitve = 50;
    System.out.println(Hotel.eur2sit(100));
    
    Hotel prviHotel = new Hotel("Pri Micki", 50);
    System.out.println(prviHotel.imeHotela);
    
    Hotel drugiHotel = new HotelZBazenom("Pri Janezu", 30, 10000);
    
    
  }

}

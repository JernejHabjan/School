package predavanje5;

/**
 * @author tomaz
 */
public class BSDCheckSum {

  // Metoda izracuna BDS kontrolno vsoto. Pri tem poleg pristevanje 
  // posameznega znaka opravi se ciklicni zamik bitov 
  //     checksum = (checksum >> 1) + ((checksum & 1) << 15)
  // in operacijo modulo 2^16
  //     checksum &= 0xffff;    
  static int bsdChecksumFromFile(String niz) {
    int checksum = 0;             

    for(int i=0; i<niz.length(); i++) {
        checksum = (checksum >> 1) + ((checksum & 1) << 15); 
        checksum += niz.charAt(i);
        checksum &= 0xffff;    
    }
    return checksum;
  }
  
  
  public static void main(String[] args) {
    System.out.println(bsdChecksumFromFile("ABC"));
    System.out.println(bsdChecksumFromFile("ACB"));
  }
}

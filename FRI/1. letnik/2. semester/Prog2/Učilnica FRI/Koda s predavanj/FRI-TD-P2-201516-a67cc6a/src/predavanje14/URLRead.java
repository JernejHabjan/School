package predavanje14;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Branje datoteke s podanim URL naslovom. 
 * @author tomaz
 */
public class URLRead {

  public static void main(String[] args) throws Exception {        
    String urlAddress = "http://www.vreme-on.net/temperature-veter.html";
    
    // preprosti nacin odpiranja datoteke s podanim URL naslovom
    URL url = new URL(urlAddress);
    Scanner sc = new Scanner(url.openStream());
    
    // Ce zgornji nacin ne dela, je treba streznik na nek nacin "preslepiti" in se 
    // predstaviti kot brskalnik. Namesto zgornjih dveh vrstic uporabi spodnje tri:
    // URLConnection url = new URL(urlAddress).openConnection();
    // url.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");        
    // Scanner sc = new Scanner(url.getInputStream());
        
    while (sc.hasNextLine()) {
      System.out.println(sc.nextLine());
    }
  }
}

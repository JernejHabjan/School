
import java.util.Scanner;
import java.io.File;

// Program prebere datoteko s tecajem CHF in izpise 
// najmanjso in najvecjo vrednost tecaja
public class Tecaj {

  public static void main(String args[]) throws Exception {

    // ime datoteke podam v prvem argumentu
    String imeDatoteke = args[0];
    Scanner dat = new Scanner(new File(imeDatoteke));

    double najmanjsiTecaj = 10;
    String najmanjsiDatum = "";

    double najvecjiTecaj = 0;
    String najvecjiDatum = "";

    while (dat.hasNext()) {
      // preberem podatke trenutne vrstice ...
      String datum = dat.next();
      double tecaj = dat.nextDouble();

      // ... in jih primerjam z dosedaj najmanjsim ...
      if (tecaj < najmanjsiTecaj) {
        najmanjsiTecaj = tecaj;
        najmanjsiDatum = datum;
      }

      // ... in najvecjim prebranim tecajem
      if (tecaj > najvecjiTecaj) {
        najvecjiTecaj = tecaj;
        najvecjiDatum = datum;
      }

      System.out.println(datum);
    }

    dat.close();

    System.out.printf("%s, %.4f\n", najmanjsiDatum, najmanjsiTecaj);
    System.out.printf("%s, %.4f\n", najvecjiDatum, najvecjiTecaj);
  }
}

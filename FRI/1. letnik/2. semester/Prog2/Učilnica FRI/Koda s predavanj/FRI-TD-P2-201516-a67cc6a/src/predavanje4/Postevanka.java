
import java.io.*;

class Postevanka {

  public static void main(String args[]) throws Exception {
    int a = 5, b = 15; // spodnja in zgornja meja za izpis tabele 
    int n = 7;       // izpisujemo veckratnike stevila n od a od b

    // "odprem" datoteko, v katero bom pisal
    PrintWriter pw = new PrintWriter(new File("veckratniki.txt"));

    for (int i = a; i <= b; i = i + 1) {
      // pisem podobno kot na zaslon
      pw.printf("%2d * %2d = %2d \n", i, n, i * n);
    }

    // ne smem pozabiti na zapiranje datoteke!!!!
    pw.close();
  }
}

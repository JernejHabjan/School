// Program izpise vsoto prvih dveh argumentov

public class Racunalo {

  public static void main(String args[]) {
    if (args.length == 2) {
      int x = Integer.parseInt(args[0]);
      int y = Integer.parseInt(args[1]);

      System.out.printf("%d + %d = %d\n", x, y, x + y);
    } else {
      System.out.println("Napaka - vpisi dva argumenta!");
    }
  }
}

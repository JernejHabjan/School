// Izpis vseh argumentov programa

public class Args {

  public static void main(String args[]) {
    System.out.printf("Stevilo podanih argumentov: %d\n", args.length);

    for (int i = 0; i < args.length; i = i + 1) {
      System.out.printf("Argument %d: %s\n", i, args[i]);
    }
  }
}

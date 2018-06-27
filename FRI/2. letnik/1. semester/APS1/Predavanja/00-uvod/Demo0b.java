/* Demo0a + procesiranje po vrsticah
1. processLines()
2. processInts(Scanner sc)
3. Argumenti: "full" in "lines"
*/

import java.util.Scanner;

public class Demo0b {
    static void processInts(Scanner sc) {
        int cnt = 0;
        int sum = 0;
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            cnt++;
            sum += x;
        }
        System.out.println("> " + cnt + ": " + (float)sum / cnt);
    }

    static void processLines() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == null || line.length() == 0) continue;
            processInts(new Scanner(line));
        }
    }

    public static void main(String[] args) {
        String arg = args.length > 0 ? args[0] : null;
        if ("full".equals(arg))
            processInts(new Scanner(System.in));
        else if ("lines".equals(arg))
            processLines();
        else {
            System.err.println("Ufff.");
            System.exit(42);
        }
    }
}

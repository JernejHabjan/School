/* Demo0b + več razredov v isti datoteki.
1. Dodaten razred Stat
2. Popravki v ostalem delu
*/

import java.util.Scanner;

class Stat {
    int cnt;
    int sum;
    int min = Integer.MAX_VALUE;

    void enter(int x) {
        cnt++;
        sum += x;
        if (x < min) min = x;
    }

    public String toString() {
        return "> " + cnt + ": " + (float)sum / cnt + ", " + min;
    }
}


public class Demo0c {
    static void processInts(Scanner sc) {
        Stat stat = new Stat();
        while (sc.hasNextInt()) {
            stat.enter(sc.nextInt());
        }
        System.out.println(stat);
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


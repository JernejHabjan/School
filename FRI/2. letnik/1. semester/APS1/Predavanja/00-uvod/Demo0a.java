/* Računanje povprečja int-ov prebranih s standardnega vhoda.
1. Ogrodje: public class + public main
2. Branje intov s Scannerjem
3. Povprečje naj bo float
*/

import java.util.Scanner;

public class Demo0a {
    static void processInts() {
        int cnt = 0;
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            cnt++;
            sum += x;
        }
        System.out.println("> " + cnt + ": " + (float)sum / cnt);
    }

    public static void main(String[] args) {
        processInts();
    }
}

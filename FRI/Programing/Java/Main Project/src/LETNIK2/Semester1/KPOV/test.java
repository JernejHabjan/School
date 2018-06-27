package LETNIK2.Semester1.KPOV;

import java.util.Random;

/**
 * Created by Jernej Habjan on 18. 01. 2017.
 */
public class test {
    public static void main(String[] args){
        Random rand = new Random();
        System.out.println(rand.nextInt() % 4094);
        System.out.println(rand.nextInt() % 4096);
        System.out.println((rand.nextInt() % 4094) + 1);
        System.out.println((rand.nextInt() % 4095) + 1);

    }
}

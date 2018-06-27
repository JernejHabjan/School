package LETNIK2.Semester1.AIPS.Izzivi.Izziv1; /**
 * Created by Jernej Habjan on 17. 10. 2016.
 */

public class Izziv1 {

    public static void main(String[] args){
        tabeliranje();
    }

    private static int[] generateTable(int n){
        int[] t = new int[n];
        for(int i = 0; i < n; i++){
            t[i] = i + 1;
        }
        return t;
    }

    static int findLinear(int[] a, int v){ //v je value
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v){
                return i;
            }
        }
        return -1; //če ni vrne -1 če pa najde pa vrne index
    }

    static int findBinary(int[] a, int l, int r, int v){
        int middle = (l + r)/2;

        while( l <= r ) {
            if ( a[middle] < v ){
                l = middle + 1;
            }else if ( a[middle] == v ){
                return middle;
            }else{
                r = middle - 1;
            }
            middle = (l + r)/2;
        }
        return -1;
    }

    static int findOptimal(int[] a, int v){
        if (v >0 && v < a.length - 1){
            return v - 1;
        }
        return -1;
    }

    static long timeLinear(int n){
        int[] t = generateTable(n);
        long startTime = System.nanoTime();
        int numIterations = 100;
        for (int i = 0; i < numIterations; i++) { //loops 100x
            int randSt = (int)(Math.random() * n)+1;

            findLinear(t, randSt);
        }

        long executionTime = System.nanoTime() - startTime; // dobimo rez v nanosec

        return executionTime;
    }

    static long timeBinary(int n){

        int[] t = generateTable(n);
        long startTime = System.nanoTime();
        int numIterations = 100;


        for (int i = 0; i < numIterations; i++) { //loops 100x
            int randSt = (int)(Math.random() * n)+1;

            findBinary(t,0, t.length-1, randSt);
        }

        long executionTime = System.nanoTime() - startTime; // dobimo rez v nanosec

        return executionTime;
    }

    static void tabeliranje(){
        System.out.println("n    | linIskanje | dvojISKANJE");
        for(int i = 1000000; i < 10000000; i+=1000000){
            System.out.printf("%d | %d | %d \n", i, timeLinear(i),timeBinary(i));
        }
    }

    /*
    e) Razmislite

    Zakaj so na časi pri vas drugačni kot v zgornji tabeli?
        - Ker so števila naključna in najde element v različnem času, prav tako sta si računalnika različna.
    Kateri algoritem je hitrejši?
        - Linearni... ups.... binarni seveda
    Kdaj bi lahko bil počasnejši algoritem hitrejši?
        - takrat ko bi bil element na začetku
    Kako se čas odvisen od velikosti naloge (linearno, kvadratno, ...)?
        - pri linearnem linearno, pri binarnem logaritemsko
    Je časovna odvisnost dvojiškega iskanja bližje linearni ali konstantni?
        - konstantni
     */
}







package LETNIK2.Semester1.AIPS.Izzivi.izziv5;

import java.util.Random;
import java.util.Scanner;


class Global{
    static int atr = 0;
    static int smer = 1;
}

class izziv5{
    public static void main(String[] args){
        int STEVILOOSEB = 0;


        String line;
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            System.out.println("koliko oseb naj ima tabela?");

            Oseba[] tt = new Oseba[0];

            if(sc.hasNext()) {
                STEVILOOSEB = sc.nextInt();

                tt = new Oseba[STEVILOOSEB];
                for(int i = 0; i < STEVILOOSEB; i++){ //generated osebe
                    tt[i] = new Oseba();

                }
                System.out.println("Zacetni seznam: ");
                printArray(tt);

            }

            boolean running = true;
            boolean execute = true;
            while(running){
                if(execute){



                    System.out.println("vpisi atribut (0-2) in smer(-1 ali 1)");

                    if(sc.hasNextInt()) {
                        Global.atr = sc.nextInt();
                    }
                    if(sc.hasNextInt()) {
                        Global.smer = sc.nextInt();
                    }

                    objectInsertionSort(tt);

                    printArray(tt);

                }
                System.out.println("vpisi 'ponovi' ali karkoli drugega za exit");
                if(sc.hasNext()){
                    String str = sc.next();
                    if(!str.equals("ponovi")){
                        running = false;
                    }else{
                        execute = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sc != null) {
                sc.close();
            }
        }


    }

    static void printArray(Oseba[] a){
        for(Oseba ele: a){
            System.out.println(ele.toString());
        }
    }

    static void objectInsertionSort(Comparable[] a){
        //pri padajočem je vrednost pomnožena z -1 -je že done
        for (int i = 0; i < a.length; i++) {
            Comparable k = a[i];
            int j = i;
            while (j > 0 && a[j - 1].compareTo(k) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = k;
        }
    }


}

class Oseba implements Comparable{
    String ime;
    String priimek;
    int letoR;

    final String[] IMENA={"ana", "neza", "anja", "dora"};
    final String[] PRIIMKI ={"kondic", "gogala", "novak", "maloku"};

    public Oseba(){
        letoR = (int)(Math.random() * 107 + 1910);

        int rndIme = new Random().nextInt(IMENA.length);
        ime = IMENA[rndIme];

        int rndPriimek = new Random().nextInt(PRIIMKI.length);
        priimek = PRIIMKI[rndPriimek];
    }

    public String toString(){
        return ime + " " + priimek + ", " + letoR;
    }

    public int compareTo(Object o){
        if(! (o instanceof Oseba)) return -1;
        Oseba os = (Oseba) o;
        switch (Global.atr){
            case 0:
                return this.ime.compareTo(os.ime) * Global.smer;
            case 1:
                return this.priimek.compareTo(os.priimek) * Global.smer;
            case 2:
                return (this.letoR - os.letoR) * Global.smer;
            default:
                System.out.println("unknown");
                return -1;
        }
    }

}



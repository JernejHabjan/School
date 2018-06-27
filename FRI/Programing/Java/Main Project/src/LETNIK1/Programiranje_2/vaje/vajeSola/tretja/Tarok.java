package LETNIK1.Programiranje_2.vaje.vajeSola.tretja;

import java.util.Arrays;
import java.util.Random;

public class Tarok {
	static char pik  = '\u2660';  // ♠
    static char kriz = '\u2663';  // ♣
    static char srce = '\u2665';  // ♥ 
    static char karo = '\u2666';  // ♦
    
    static char   barve[]        = {srce, karo, kriz, pik};
    static String prazneRdece[]  = {"1", "2", "3", "4"};
    static String prazneCrne[]   = {"7", "8", "9", "10"};
    
    static String figure[]       = 
      {"Fant", "Kaval", "Dama", "Kralj"};
    
    static String taroki[]       = 
      {"I", "II", "III", "IIII", "V", "VI", "VII",
       "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
       "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
       "XXI", "SKIS"    
      };
    
    public static void main(String[] args){
        String karte[] = ustvariKarte();
        premesajKarte(karte, 200);

        System.out.println("Prvi kup kart:");
        int kupcek1= natancnoStetje(karte, true, 0, 25);

        System.out.println("Drugi kup kart:");
        int kupcek2= natancnoStetje(karte, true, 25, 54);

        System.out.println("Natancno  stetje 1. kupa kart: " + kupcek1);
        System.out.println("Natancno  stetje 2. kupa kart: " + kupcek2);

    }

    static String[] ustvariKarte(){
        String[] tabela = new String[54];
        for(int i=0;i<barve.length;i++){
            for(int j=0;j<prazneRdece.length+figure.length;j++){
                if(i<2){
                    if(j<4){
                        tabela[i*8+j]=barve[i]+prazneRdece[j];	
                    }else{
                        tabela[i*8+j]=barve[i]+figure[j-4];
                    }
                }else{
                    if(j<4){
                        tabela[i*8+j]=barve[i]+prazneCrne[j];
                    }else{
                        tabela[i*8+j]=barve[i]+figure[j-4];
                    }
                }
            }
        }
        for(int i=0;i<taroki.length;i++){
                tabela[32+i]=taroki[i];
        }
        return tabela;
    }

    static void izpisi(String karte[]){
            for (int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if (i*8+j<karte.length){
                        System.out.printf("%7s",karte[i*8+j]);
                    }
                }
            System.out.println();
            }
    }

    static void premesajKarte(String karte[], int koliko){
            Random randomno = new Random();
            for( int i=0 ; i<karte.length-1 ; i++ ){
            int j = randomno.nextInt(karte.length-1);
            String temp = karte[i]; 
            karte[i] = karte[j];
            karte[j] = temp;
        }       
    }

    static int preprostoStetje(String karte[], int z, int k){
        int vsota=0;
        for(int i=z;i<k;i++){
//            String x=null;
//            if(Arrays.asList(karte).contains(karte[0])){
//                    x= karte[i].substring(1, karte[i].length());
//            }else{
//                    x=karte[i];
//            }
            String x = karte[i];   
            if(x.contains("Kralj")||x.contains("XXI")||x.contains("Pagat")||x.contains("SKIS")||x.contains("I")){
                    vsota+=5;
            }
            if(x.contains("Dama")){
                    vsota+=4;
            }
            if(x.contains("Kaval")){
                    vsota+=3;
            }
            if(x.contains("Fant")){
                    vsota+=2;
            }else{
                    vsota++;
            }
        }
        return vsota;
    }

    static int natancnoStetje(String karte[], boolean izpis, int z, int k){
        int vsota=0;
        String subArray[] = Arrays.copyOfRange(karte,z,k);
        if(izpis==true){
                izpisi(subArray); 
        }
        for(int i=z; i<k; i+=3){
            if(i+3<k){
                vsota += preprostoStetje(karte, i, i+3)-2;
            }else{
                vsota += preprostoStetje(karte, i, i+1)-1;
            }
        }
        return vsota;
    }
}
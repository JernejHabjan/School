package LETNIK1.Programiranje_2.vaje.vajeSola.sedma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
//https://ucilnica.fri.uni-lj.si/mod/assign/view.php?id=14308
public class V07 {

    public static void main(String[] args) throws FileNotFoundException{
       String vhod="resources/java.txt";
       String izhod ="resources/izhod.txt"; //še ni narejena
       String izhod1 ="resources/izhod1.txt";
       //stisni(vhod,izhod);
       razsiri(izhod,izhod1);
    }
    
    public static void stisni(String vhod, String izhod) throws FileNotFoundException{
        
        File f= new File(vhod);
        Scanner sc= new Scanner(f);
        sc.useDelimiter("\\Z");
        File fizhod= new File(izhod);
        PrintWriter pw= new PrintWriter(fizhod);
        while(sc.hasNext()){
            String line =sc.next();
            String rezultat=stisni(line);
            pw.println(rezultat);
            System.out.println(rezultat);
        }
        sc.close();
        pw.close();
    }
    
    public static String stisni(String niz){
        String rezultat="";
        niz=niz+"!";
        int i=0;
        char temp=niz.charAt(0);
        for (int j= 0; j < niz.length(); j++) {
            if(temp!=niz.charAt(j)){
                
                rezultat+=(j-i)+""+temp;
                i=j; // premaknemo števec
                temp=niz.charAt(i);
            }
            
        }
        return rezultat;

    }
    
    public static void razsiri(String vhod, String izhod) throws FileNotFoundException{
         File f= new File(vhod); //kodirano
         File fizhod= new File(izhod); //normal
         String rezultat="";
         Scanner sc= new Scanner(f);
         while(sc.hasNext()){
             String niz= sc.nextLine();
             rezultat+= razsiri(niz);
             rezultat+="\n";   
         }
         System.out.println(rezultat);
         sc.close();  
    }
    
    public static String razsiri(String niz){
        String rezultat="";
        int ponovitve=0;
        int j=0;
        for (int i = 0; i < niz.length(); i++) {
            if (niz.charAt(i) >= '0' &  niz.charAt(i) <= '9'){ //če je int
                ponovitve++;
            }else{
                int ponov = Integer.parseInt(niz.substring(j, i));
                char crka= niz.charAt(i);
                rezultat += izpis(ponov, crka);
                ponovitve=0;
                j=i+1; //premakne counter
            }
        }return rezultat;
    }
    
    public static String izpis(int ponov, char crka){
        String rezultat="";
        for(int i=0;i<ponov;i++){
            rezultat+=crka;
        }return rezultat;
    }

	//še dodatna naloga na učilnici
	public static  char poisci(String vhod){
		return 0;
		
	}
	
	public static void stisniX(String vhod, String izhod, char znak){
		
	}
	
	public static void razsiriX(String vhod, String izhod, char znak){
		
	}
}

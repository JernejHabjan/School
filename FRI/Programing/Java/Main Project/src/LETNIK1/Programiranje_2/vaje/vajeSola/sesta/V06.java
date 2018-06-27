package LETNIK1.Programiranje_2.vaje.vajeSola.sesta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class V06 {
    public static void main(String[] args) throws Exception{
        File pesem = new File("pesem.txt");
        File kodirano = new File("kodirano.txt");
        
        odkodiraj(pesem,kodirano);
        System.out.println();
	kodiraj(pesem, "pomlad pomlad pomlad");

    }
    
    static void odkodiraj(File f1, File f2) throws IOException{

        String a[]=reader(f1);
        String b[]=reader(f2);
        for (int i=0;i<b.length;i+=2){
            if("0".equals(b[i]) && "0".equals(b[i+1]) && "0".equals(b[i+2])){
                System.out.println();
                i+=3;
            }if("0".equals(b[i])&&"0".equals(b[i+1])){
                System.out.print(" ");
                i+=2;
            }if("0".equals(b[i])){
                i++;
                System.out.print(Character.toUpperCase(a[Integer.parseInt(b[i])-1].charAt(Integer.parseInt(b[i+1])-1)));
                i+=2;
            }if(b[i]!=null){
                System.out.print(a[Integer.parseInt(b[i])-1].charAt(Integer.parseInt(b[i+1])-1));
            }
        }
    }

    static void kodiraj(File kodirnaKnjiga, String niz) throws Exception {
        StringBuffer besede[] = new StringBuffer[1000];
        int steviloBesed = 0;

        Scanner sc = new Scanner(kodirnaKnjiga);
        while (sc.hasNext()) {
          besede[steviloBesed++] = new StringBuffer(sc.next());
        }
        sc.close();

        boolean nasel;
        for (int i = 0; i < niz.length(); i++) {
          if (niz.charAt(i)== ' ') {
            System.out.print("0 0 ");
            continue;
          }
          nasel = false;
          for (int j = 0; j < steviloBesed; j++) {
            for (int k = 0; k < besede[j].length(); k++) {
              if (Character.toUpperCase(besede[j].charAt(k)) == Character.toUpperCase(niz.charAt(i))) {
                if (Character.isUpperCase(niz.charAt(i)))
                  System.out.println("0 ");
                System.out.printf("%d %d ", j+1, k + 1);
                besede[j].setCharAt(k, '-');
                nasel=true;
                break;
              }
            }
            if (nasel)
              break;
          }
          if (!nasel)
            System.out.printf("%d %d ", 99, 99);
        }
      }
    
    @SuppressWarnings("unused")
	public static String[] reader(File file) throws FileNotFoundException, IOException{
        String[] array=new String[100];
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int i=0;
        String line;
        while ((line = reader.readLine()) !=null) {
            if(line==null)
                break;
            for(String y:line.split(" ")){
                array[i]=y;
                i++;
            }
        }
        reader.close();
        return array;
        
    }
}



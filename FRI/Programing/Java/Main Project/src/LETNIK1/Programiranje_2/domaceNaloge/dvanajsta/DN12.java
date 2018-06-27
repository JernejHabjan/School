package LETNIK1.Programiranje_2.domaceNaloge.dvanajsta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;





public class DN12 {
	static final char[] Z= {' ', '.', '\'', ':', 'o', '&', '8', '#', '@'};
	static final int S[] = {230, 200, 180, 160, 130, 100, 70, 50};
	static ArrayList<Integer> slika= new ArrayList<Integer>();
	static String dimenzije="";
	public static void main(String[] args) throws AsciiArtException{
		

		readPicture(args);
		readArray(args);
		

		
	}
	
	public static void readPicture(String[] args) throws AsciiArtException{
		File f = new File(args[0]);
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(f);
			
			
			
			Boolean removed=false;
			int vrstica=0;
			int content;
			int iterate=0;
			String errorChecker ="";
			
			
			while ((content = fis.read()) != -1) {
				if(vrstica==0){
					
					if(iterate==2 && !errorChecker.equals("P6")){ //če po dveh ne najde
						System.out.println(new AsciiArtException("Napacen format slike!").toString());
						//System.out.println("AsciiArtException: Napacen format slike!"); //KER IZPIS PRIDE DRUGAČEN -.-
						//System.exit(0);
					}
					
					
					errorChecker+=(char) content;
					iterate++;
				}

				if(vrstica==1 && args.length==3){
					System.out.print((char) content);
				}
				if (vrstica==1){
					dimenzije+=(char) content;
				}
				if((char) content=='\n' ){
					if(vrstica==1 && args.length==3){
						System.exit(0);
						
					}
					else{
						vrstica++;
					}
					
				}

				if(vrstica>2){
					if(removed==false){
						int lol=(int)content;
						removed=true;
					}else{
						
						slika.add((int)content);
					}
					
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	public static void readArray(String[] args){
		/*if(args.length==3){
			String[] dim= dimenzije.split(" ");
			int counter=0;
			for(int i=0; i+2<slika.size();i+=3){ //*Integer.parseInt(args[2])
				int sivina = (slika.get(i) + slika.get(i+1) + slika.get(i+2))/3;
				char znak = '@';
				for (int j=0;j<8;j++){
					if (sivina >= S[j]){
						znak = Z[j];
						break;
					}
				}if(counter!=Integer.parseInt(dim[0])){
					counter++;
				}else{
					
					System.out.println();
					counter=1;
				}
				System.out.print(znak);
			}
			*/

			String[] dim= dimenzije.split(" ");
			int counter=0;
			for(int i=0; i+2<slika.size();i+=3){
				int sivina = (slika.get(i) + slika.get(i+1) + slika.get(i+2))/3;
				char znak = '@';
				for (int j=0;j<8;j++){
					if (sivina >= S[j]){
						znak = Z[j];
						break;
					}
				}if(counter!=Integer.parseInt(dim[0])){
					counter++;
				}else{
					
					System.out.println();
					counter=1;
				}
				System.out.print(znak);
			}
		
	}

}
class AsciiArtException extends Exception {
	  public AsciiArtException(String message) { super(message); }
}



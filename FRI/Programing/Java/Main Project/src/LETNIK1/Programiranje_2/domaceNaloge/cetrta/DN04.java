package LETNIK1.Programiranje_2.domaceNaloge.cetrta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DN04 {
	public static void main (String[] args){
		DN04.reader(args);
		
	}
	
	static void reader(String[] args){
        String fileName = args[0];
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(DN04.wordCounter(line)); //izpiše števila 
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file "+fileName);                
        }
        catch(IOException ex) {
            System.out.println("Error reading "+fileName);                  
        }
	}

	static int wordCounter(String vrstica){
		String[] charArray = vrstica.split("");
		int words=0;
		Boolean active=false;
		for(int i=1;i<charArray.length;i++){
			if(charArray[i].equals(" ")|| charArray[i].equals("\t")){
				active=false;
			}else{
				if((active==false)&&((!charArray[i].equals(" ")||! charArray[i].equals("\t")))){
					words++;
					active=true;
				}
			}
		}
		return words;
	}
}


package LETNIK1.Programiranje_2.domaceNaloge.peta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DN05{
	public static void main(String[] args) throws IOException{
		String fileName = args[0];
		String line = null;
		try {
		    FileReader fileReader = new FileReader(fileName);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    while((line = bufferedReader.readLine()) != null) {
		    	for(int i = 0; i < line.length(); i += 8){
					String BYTE = line.substring(i, i + 8);
					int decimalValue = Integer.parseInt(BYTE, 2);
					String Sign = Character.toString ((char) decimalValue);
					System.out.print(Sign);
				}
		    }
		    bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
		    System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
		    System.out.println("Error reading file '" + fileName + "'");                  
		}
	}
}

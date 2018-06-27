package LETNIK1.Programiranje_2.vaje.vajeSola.deveta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Racunalnik {
	

	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Komponenta> komponente = new ArrayList<Komponenta>();
		Scanner sc= new Scanner(new File("src\\LETNIK1\\vaje\\vajeSola\\deveta\\komponente.txt"));
		while(sc.hasNext()){
			
			String array[]= sc.nextLine().split(";");
			for(String a: array){

			}
			switch (array[0]){
			case "Pomnilnik":
				komponente.add(new Pomnilnik(array[1],Double.parseDouble(array[2]),Integer.parseInt(array[3]),Integer.parseInt(array[4]),Integer.parseInt(array[5])));
				break;
			
			case "Procesor":
				komponente.add(new Procesor(array[1],Double.parseDouble(array[2]),0,array[3],Integer.parseInt(array[4]),Integer.parseInt(array[5])));
				break;
				
			case "Maticna":
				komponente.add(new MaticnaPlosca(array[1],Double.parseDouble(array[2]),0,array[3],array[4],Integer.parseInt(array[5])));
				break;
			}
			
			
		}
		for(Komponenta k:komponente){
			
			System.out.println(k.toString());
		}
		
	}
	
	
	
	
	
	
}

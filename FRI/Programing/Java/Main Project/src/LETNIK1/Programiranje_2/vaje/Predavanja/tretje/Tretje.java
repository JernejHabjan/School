package LETNIK1.Programiranje_2.vaje.Predavanja.tretje;
import java.util.*;
import java.io.File;

public class Tretje {
	public static void main(String args[])throws Exception{
	/*scanner
	next -prebere vse do naslednjega whitespaca
	*/
		
	//do while
	int i=4;
	do{
		System.out.println("lolo");
		i++;
	}while(i<4);
		
	String niz="lol";
	System.out.printf("%c\n",niz.charAt(0));
	
	// file reading
	//DATOTEKE SE OBVEZNO ZAPRE
	
	//throws exception
	//public static void main(String args[]) throws Exception{
//*********************************************************************************
	//FILEREADER
	// ime datoteke je podano v prvem argumentu
	String imeDatoteke = args[0];

	// objekt sc povezemo z datoteko
	Scanner sc = new Scanner(new File(imeDatoteke));

	// dokler so v datoteki se podatki ...
	while (sc.hasNextLine()) {
		// ... beremo po eno vrstico
		String vrstica = sc.nextLine();

		System.out.println(vrstica);
	}

	// na koncu moramo datoteko OBVEZNO zapreti!
	sc.close();

//********************************************************************************
	
	}
}

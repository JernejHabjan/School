package LETNIK1.Programiranje_2.domaceNaloge.enajsta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DN11 {
	static Boolean zacetek=true;
	public static void main(String[] args) throws FileNotFoundException {
		OblikovalecBesedila oB = null;
		
		
		
		switch (args[0]){
		case "OblikaStavka":
			oB = new OblikaStavka();
			break;
		case "OblikaNaslova":
			oB = new OblikaNaslova();
			break;
				
		
		default:
			System.out.println(args[0]);
			
			break;
		}
		
		Scanner sc = new Scanner(new File(args[1]));
		while(sc.hasNext()){
			if(oB!=null){

				System.out.println(oB.oblikujBesedilo(sc.nextLine()));
			}else{
				System.out.println("Oblikovalec besedila not declared");
				break;
			}
		}
		sc.close();
	}

}

interface OblikovalecBesedila {
	  /**
	   * Metoda prejme besedilo, ga oblikuje (spremeni velikost crk, doda 
	   * presledke, ...) in spremenjeno besedilo vrne.
	   */
	  String oblikujBesedilo(String vrstica);
}



class OblikaStavka implements OblikovalecBesedila{
	static Boolean pika=false;
	@Override
	public String oblikujBesedilo(String vrstica) {//ŠEE PIKO DJSystem.out.println(vrstica);
		
		
		String newString="";
		String s = vrstica;

		for (int i = 0; i < s.length(); i++){
		    char c = s.charAt(i);
		    
		    if(c=='.'){
		    	pika=true;
		    	newString+=c;
		    }else{
		    	
			   
			    if(pika || DN11.zacetek){
			    	if(c!=' '){
			    	 newString+=Character.toUpperCase(c);
			    	 pika=false;
			    	 DN11.zacetek=false;
			    	}else{
			    		newString+=Character.toLowerCase(c);
			    	}
			    	 
			    }
			    else{
			    	newString+=Character.toLowerCase(c);
			    }
			    //Process char
		    }
		}
		return newString;
		
	}
	
	
}


class OblikaNaslova implements OblikovalecBesedila{

	@Override
	public String oblikujBesedilo(String vrstica) {
		DN11.zacetek=true;
		String newString="";
		String s = vrstica;
		Boolean space=false;
		for (int i = 0; i < s.length(); i++){
		    char c = s.charAt(i);
		    if(c==' '){
		    	space=true;
		    	newString+=c;
		    }else{
		    	
			    
			    if(space||DN11.zacetek){
			    	 newString+=Character.toUpperCase(c);
			    	 space=false;
			    	 DN11.zacetek=false;
			    	 
			    }
			    else{
			    	newString+=Character.toLowerCase(c);
			    }
			    //Process char
		    }
		}
		return newString;
	}
	
}
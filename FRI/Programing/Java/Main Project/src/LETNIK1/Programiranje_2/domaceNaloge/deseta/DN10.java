package LETNIK1.Programiranje_2.domaceNaloge.deseta;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DN10 {

	static Lik[] tabelaLikov = new Lik[100];
	public static int stElementovTabele=0;
	public static void main(String[] args) throws FileNotFoundException {
		preberi(args[0]);
		izracunaj();
	}
	
	public static void preberi(String datoteka) throws FileNotFoundException{ //void zaenkrat
		Scanner sc = new Scanner(new File(datoteka));
		int i=0;
		while(sc.hasNext()){
			String vrstica= sc.nextLine();
			String[] elements = vrstica.split(":");
			switch (elements[0]){
			case "kvadrat":
				Kvadrat kv = new Kvadrat(Integer.parseInt(elements[1])); 
				tabelaLikov[i]=kv;
				i++;
				stElementovTabele++;
				break;
			case "krog":
				Krog kr = new Krog(Integer.parseInt(elements[1]));
				tabelaLikov[i]=kr;
				i++;
				stElementovTabele++;
				break;
			case "pravokotnik":
				Pravokotnik pr = new Pravokotnik(Integer.parseInt(elements[1]),Integer.parseInt(elements[2]));
				tabelaLikov[i]=pr;
				i++;
				stElementovTabele++;
				break;
			default:
				System.out.println("error");
				break;
			}
			
		}
		sc.close();
	}
	
	public static void izracunaj(){ //zaenkrat void
		float sumPloscina=0f;
		
		for(int i=0;i<stElementovTabele;i++){
			sumPloscina+=tabelaLikov[i].povrsina();
		}
		System.out.printf("%.2f",sumPloscina);
	}
	

}
abstract class Lik {
    // Lik zna izracunati svojo povrsino
    abstract double povrsina();
}

class Krog extends Lik{
	private int radius =0;
	
	Krog(int length){
		this.radius=length;
	}
	
	double povrsina(){
		return Math.PI*Math.pow(this.radius, 2);
	}
}

class Pravokotnik extends Lik{
	private int stranicaA =0;
	private int stranicaB =0;
	
	Pravokotnik(int A, int B){
		this.stranicaA=A;
		this.stranicaB=B;
	}
	
	double povrsina(){
		return stranicaA*stranicaB;
	}
}

class Kvadrat extends Lik{
	private int stranica =0;
	
	Kvadrat(int length){
		this.stranica=length;
	}
	
	double povrsina(){
		return this.stranica*this.stranica;
	}
}
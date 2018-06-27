package LETNIK1.Programiranje_2.vaje.vajeSola.deveta;

public class Komponenta {
	String ime;
	double cena;
	int popust;
	Komponenta(String ime, double cena, int popust){
		this.ime=ime;
		this.cena=cena;
		this.popust=popust;
	}
	public String toString(){
		
		return ime+" cena s popustom je: "+cenaSPopustom();
	}
	public double cenaSPopustom(){
		return cena*((1-popust)/100);
	}
}

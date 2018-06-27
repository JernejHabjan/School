package LETNIK1.Programiranje_2.vaje.vajeSola.deveta;

public class Pomnilnik extends Komponenta {
	int velikost;
	int hitrost;
	

	public Pomnilnik(String ime, double cena, int popust, int velikost, int hitrost) {
		super(ime, cena, popust);
		this.hitrost=hitrost;
		this.velikost=velikost;
	}

	public String toString(){
		return "Pomnilnik "+super.toString()+" "+velikost+" "+hitrost; 
	}
}

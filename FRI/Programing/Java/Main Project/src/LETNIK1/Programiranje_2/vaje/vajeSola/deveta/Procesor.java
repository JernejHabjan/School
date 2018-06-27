package LETNIK1.Programiranje_2.vaje.vajeSola.deveta;

public class Procesor extends Komponenta{
	String podnozje;
	int steviloJeder;
	int hitrostJedra;
	
	Procesor(String ime, double cena, int popust,String podnozje,int steviloJeder,int hitrostJedra) {
		super(ime, cena, popust);
		this.podnozje=podnozje;
		this.steviloJeder=steviloJeder;
		this.hitrostJedra=hitrostJedra;
		
	}
	
	public String toString(){
		return "Procesor "+super.toString()+" "+podnozje+" "+hitrostJedra+" "+steviloJeder;
		
	}

}

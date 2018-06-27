package LETNIK1.Programiranje_2.vaje.vajeSola.deveta;

public class MaticnaPlosca extends Komponenta {
	String format;
	String podnozje;
	int steviloPomnilniskihRez;
	MaticnaPlosca(String ime, double cena, int popust, String format, String podnozje, int steviloPomnilniskihRez) {
		super(ime, cena, popust);
		this.format=format;
		this.podnozje=podnozje;
		this.steviloPomnilniskihRez=steviloPomnilniskihRez;
	}
	public String toString(){
		return "Maticna "+super.toString()+" "+format+" "+podnozje+" "+steviloPomnilniskihRez;
	}
	
//	public boolean jeKompatibilna(Procesor p){
//		
//		
//		
//	}

}

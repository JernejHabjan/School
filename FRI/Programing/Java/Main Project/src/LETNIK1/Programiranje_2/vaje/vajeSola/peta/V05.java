package LETNIK1.Programiranje_2.vaje.vajeSola.peta;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class V05 {
	
	private long[] tabelaIzris= new long[]{
			getCrko(new int[]{0, 0, 0, 0, 0, 0, 0, 255}),
			getCrko(new int[]{64, 64, 64, 64, 64, 64, 64, 255}),
			getCrko(new int[]{124, 64, 64, 64, 64, 64, 64, 255}),
			getCrko(new int[]{124, 68, 74, 68, 64, 64, 64, 255}),
			getCrko(new int[]{124, 68, 74, 68, 95, 64, 64, 255}),
			getCrko(new int[]{124, 68, 74, 68, 95, 68, 74, 255})};
    private String tabelaFraz[]={"SONCE SIJE", "TRAVA RASTE", "KOLESAR BRZI",
            "ZIDAR ZIDA", "METLA POMETA", "VELIKA NOGA", "RDECA VRTNICA",
            "SLEPA ULICA", "MODRINA NEBA", "SABLJA", "NETOPIR", "KOLOVRAT",
            "SMUCANJE", "BELE STRMINE", "OTROCI SE ZOGAJO", "BABICA LIKA",
            "PROGRAMER PROGRAMIRA", "SKAF IMA LUKNJO", "VISLICE SO ZABAVNE"};
    private String fraza;
    private Boolean tabela[];
    private int steviloNapak;
    
    public static void main(String[] args){
    	//System.out.println(255<<56);
        V05 v = new V05();
        v.novaIgra();
        while(v.jeUganil()==false){
	        if(v.postaviVprasanje()==-1){
	        	break;
	        }v.izpisiUganko();
        }System.out.print("GAME OVER");
    }
    
    public static String getRandom(String[] array) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(array.length);
        return array[randomIndex];
    }
    
    public void novaIgra(){
        this.fraza=getRandom(this.tabelaFraz);
        System.out.println(fraza);
        this.tabela=new Boolean[this.fraza.length()];
        for(int i=0;i<this.tabela.length;i++){
            this.tabela[i]=false;
        }this.steviloNapak=0;
    }
    
    public Boolean jeUganil(){
        for(boolean b : this.tabela){
        if(!b) return false;
        }
        return true;
    }
    
    public void izpisiUganko(){
        for(int i=0; i<this.tabela.length;i++){
        	char crka=this.fraza.charAt(i);
            if(this.tabela[i]==true){
            System.out.print(crka);
            }else{
            	if(crka ==' '){
            		System.out.print(" ");
            		this.tabela[i]=true;
            	}else{
		            System.out.print("_");
            	}
            }
        }System.out.println();
    }
    
    public int postaviVprasanje(){
    	int maxMistakes=6;
    	 int stevilo=maxMistakes-this.steviloNapak;
    	 if(stevilo<1){
    		 Arrays.fill(this.tabela, true);
    		 return -1;
    	 }
         System.out.printf("Naredite lahko še %3s napak.\n",stevilo);
         Scanner sc= new Scanner(System.in);
         System.out.println("Vpisite char ali celo besedo: ");
         String s=sc.nextLine();
         if (s.equals(this.fraza)){
        	System.out.println("Ugotovili ste besedo");
        	Arrays.fill(this.tabela, true);
        	return -1;
         }else{
        	 char c = s.charAt(0);
	         Boolean temp=false;
	         for(int i=0; i<this.tabela.length;i++){
	             if(this.fraza.charAt(i)==c){  
	            	 this.tabela[i]=true;
	            	 temp=true;
	             }
	         }if(temp.equals(false)){
	        	 this.steviloNapak++;
	        	 izpisi(tabelaIzris[steviloNapak-1]); 
	         }
	         return 0;
         }
    }
    
    // OD TU NAPREJ GRAFIČNO
    public static void izpisi(long crka) {
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				long bit = (long) 1 << (8 * i + j);
				System.out.print((crka & bit) == bit ? "*" : " ");
			}
			System.out.println();
		}
	}
    
    public static long getCrko(int[] vrstice) {
		long rezultat = 0;
		for (int i = 0; i < vrstice.length; i++) {
			rezultat = (long) (rezultat << 8) + vrstice[i];
		}
		return rezultat;
	}
}

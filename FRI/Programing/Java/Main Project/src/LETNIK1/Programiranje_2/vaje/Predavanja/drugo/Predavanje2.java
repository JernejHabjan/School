package LETNIK1.Programiranje_2.vaje.Predavanja.drugo;

public class Predavanje2 {
	public static void main(String[] args){
		System.out.printf("%d,%d,%d\n",6,5,4); 
		/*%f - float
		%c - char
		%s - string
		%x - hex
		%5d -da na levo 4 presledke če vneseš št manjše od 10..
		*/
		System.out.printf("%5d\n",5);
		//lahko se poravnava z ničlami spredaj
		System.out.printf("%05d\n",5);
		
		//SREČKE
		Srecke.srecke();
		
		//PRINT ARGS
		System.out.printf("stevilo podanih argumentov: %d\n", args.length);
		
		//RANDOM:
		System.out.println(Random.random(3, 6));
		//Random.newRandom(3, 6);
		
	}
	
}
class Srecke{
	static void srecke(){
		System.out.println("lolololololololo to je glava");
		for (int i=0; i<30;i++){
			System.out.print("-");
		}System.out.println();
		for(int i=1;i<11;i++){
			System.out.printf("   %02d %9s%5.2f\n",i,"|",i*1.25); 		   //.2f poglej ta line za FORMAT
		}
	}
}
class Random{
	static int random(int min, int max){
		int range = (max - min) + 1;     
		return (int) ((Math.random() * range) + min);
	}
	static void newRandom(){
		Random randomno = new Random();
		//System.out.println("Next int value: " +randomno.nextInt(10000)); V TEMU FILU NE DELA NEXTINT
	}
}


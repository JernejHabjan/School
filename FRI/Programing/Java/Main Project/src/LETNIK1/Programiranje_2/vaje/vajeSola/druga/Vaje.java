package LETNIK1.Programiranje_2.vaje.vajeSola.druga;
import java.util.Scanner;

public class Vaje {
	public static void main(String[]args){
		Vaja.vaja();
		System.out.println();
		System.out.println();
		TwentyOne.twentyOne();
	}
}

class Vaja{
	public static void vaja(){
		Vaja vaja = new Vaja();

		int j = (int)(Math.log10(vaja.fakulteta(20))+1); //copied   //CHANGE IT TO FOR TO DECRASE WITH J
		
		System.out.println(" n            n!               Stirling(n)         napaka (%)");
		System.out.println("--------------------------------------------------------------");
		for(int i=1;i<21;i++){
			long str=vaja.stirling(i);
			long fac=vaja.fakulteta(i);
			double str1= (double) str;
			double fac1=(double) fac;
			System.out.printf("%02d",i);

			vaja.odmik(j-(int)(Math.log10(str)));
			System.out.print(" "+fac);

			vaja.odmik(j-(int)(Math.log10(str)));
			double povprecje=(((fac1-str1)/fac1))*100;
			System.out.printf(" "+str+"       %.07f%% \n",povprecje);
		}
	}
	void odmik(int n){
		for(int i=0;i<n;i++){
			System.out.print(" ");
		}
	}
	long fakulteta(int n){
		if(n==1)
			return n;
		else{
			return n*fakulteta(n-1);
		}
	}
	long stirling(int n){
		//return (long) (Math.sqrt(2*Math.PI*n)*Math.pow((n/Math.E), n));
		return (long) Math.round((Math.sqrt((2*n+1/3)*Math.PI)*Math.pow(n, n)*Math.pow(Math.E, -n))); //upgraded strling
	}
}

class TwentyOne {
	static int vzigalica=21;
	static Player player1=new Player();
	static Player player2=new Player();
	public static void twentyOne(){
		TwentyOne.readUsers();
		
		while (vzigalica>0){
			TwentyOne.play(player1);
			if (vzigalica<=0){
				System.out.print("ZMAGAL JE: "+player2.getName());
				break;
			}
			TwentyOne.play(player2);
			if (vzigalica<=0){
				System.out.print("ZMAGAL JE: "+player2.getName());
				break;
			}
		}
	}
	static void play(Player player){
		System.out.println("Vzigalice:"+vzigalica);
		TwentyOne.izris(vzigalica);
		System.out.println("Your move: "+ player.getName());
		if (player.getName().equals("X")||player.getName().equals("x")){
			int pulled=(int)((Math.random() * (3 - 1) + 1) + 1);
			System.out.println("AI pulled "+pulled+" matches.");
			vzigalica-=pulled;
			TwentyOne.sleep(1000);
		}else{
		TwentyOne.pull();
		}
	}
	static void sleep(int milisec){
		try {
		    Thread.sleep(milisec);                
		}catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	static void readUsers(){
		System.out.println("igralec 1: vpisi ime: -if computer->write X");
		String player1Name= TwentyOne.scanner();
		
		player1.setName(player1Name);
		
		System.out.println("igralec 2: vpisi ime: -if computer->write X");
		String player2Name= TwentyOne.scanner();
		player2.setName(player2Name);
	}

	static void izris(int vzigalic){
		for(int i=0;i<vzigalica;i++){
			System.out.print("o");
		}
		System.out.println();
		for(int i=0;i<vzigalica;i++){
			System.out.print("|");
		}
		System.out.println();
		for(int i=0;i<vzigalica;i++){
			System.out.print("|");
		}
		System.out.println();
	}
	
	static String scanner(){
		Scanner sc=new Scanner(System.in);
		return sc.nextLine();
	}
	static void pull(){
		System.out.println("vleci vzigalice: ");
		int pulled =Integer.parseInt(TwentyOne.scanner());
		if(pulled==1||pulled ==2||pulled==3){
			System.out.println("potegnu si "+pulled+" vzigalic.");
			vzigalica-=pulled;
		}else{
			System.out.println("pull again -vleci števila med 1-3");
			TwentyOne.pull();
		}
	}
}
class Player{
	String playerName="";
	public void setName(String name){
		playerName=name;
	}
	public String getName(){
		return playerName;
	}
}

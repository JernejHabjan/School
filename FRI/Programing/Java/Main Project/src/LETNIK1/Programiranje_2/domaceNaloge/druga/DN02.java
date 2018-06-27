package LETNIK1.Programiranje_2.domaceNaloge.druga;


public class DN02 {
	public static void main(String[] args){
		if(args.length == 0){
			DN2.all();
		}else{
			int a = Integer.parseInt(args[0]);
			int b= Integer.parseInt(args[1]);
			System.out.print("a = "+a+" , b = "+b+"    ");
			DN02.pravokotnik(a, b);
		}
	}
	
	static void pravokotnik(int a, int b) {
		for (int i=0; i<a;i++){
			if (i>0){
				System.out.print("\n");
				System.out.print("                 ");
			}
			for (int j=0; j<b;j++){
				System.out.print("X");
			}
		}
	}
}

class DN2{
	static void all(){
		for (int i=1; i<6;i++){
			for (int j=1; j<6;j++){
				System.out.print("a = "+i+" , b = "+j+"    ");
				DN02.pravokotnik(i, j);
				System.out.print("\n");
				System.out.println();
			}
		}
	}
}
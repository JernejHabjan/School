package LETNIK1.Programiranje_2.vaje.vajeSola.prva;

public class Zvezdice {
    public static void main(String args[]){
    	Zvezdice.pravokotnik(3,6,7);
    	Zvezdice.trikotnik(3, 5);
    	Zvezdice.okvir(3, 6, 5);
    	Zvezdice.hisa(3, 7, 7);
    	Zvezdice.dvaPravokotnika(2, 3, 4, 5);
    	Zvezdice.raketa(2, 4, 5, 5);
    	Zvezdice.X(4);
    }
    static void odmik(int odmik){
    	System.out.print(new String(new char[odmik]).replace("\0", " "));
    }
    static void odmik2(int odmik){
    	for (int i=0;i<odmik;i++){
    		System.out.print(" ");
    	}
    }
    static void drawStars(int sirina){
    	for (int i=0;i<sirina;i++){
    		System.out.print("*");
    	}
    }
    static void pravokotnik(int odmik, int visina, int sirina){
    	for(int i=0;i<visina;i++){
    		if (i>0){
    			System.out.println();
    		}
    		Zvezdice.odmik(odmik);
    		Zvezdice.drawStars(sirina);
    	}
    	System.out.println();
    }
    static void trikotnik(int odmik, int visina){
    	int l=0-1;
    	for(int i=1;i<=visina;i++){
    		l+=2;
    		Zvezdice.odmik(odmik);

    		for (int k=0; k < (visina - l / 2)-1; k++){
    	        System.out.print(" ");
    	    }
    		for(int j=0;j<l;j++){
    			System.out.print("*");
    		}
    		System.out.println();
    	}
    }
    static void okvir(int odmik, int visina, int sirina){
    	for (int i=0; i<visina-1;i++){
    		if (i==0||i==visina-2){
    			Zvezdice.odmik(odmik);
    	    	Zvezdice.drawStars(sirina);
    	    	System.out.println();
    		}else{
    		Zvezdice.odmik(odmik);
    		Zvezdice.drawStars(1);
    		for (int j=0;j<(sirina-2);j++){
    			System.out.print(" ");
    		}
    		Zvezdice.drawStars(1);
    		System.out.println();
    		}
    	}	
    }
    static void hisa(int odmik, int visina, int sirina){
    	int j=-1;
    	for(int i=0;i<visina;i++){
    		j+=2;
    	}    	
    	Zvezdice.trikotnik(odmik, visina);
    	Zvezdice.okvir(odmik+(j-sirina)/2, visina, sirina);
    }
    static void dvaPravokotnika(int odmik, int razmik, int visina, int sirina){
    	for(int i=0;i<visina;i++){
    		Zvezdice.odmik(odmik);
    		Zvezdice.drawStars(sirina);
    		Zvezdice.odmik(razmik);
    		Zvezdice.drawStars(sirina);
    		System.out.println();
    	}
    }
    static void raketa(int odmik, int razmik, int visina, int sirina){
    	int j=-1;
    	for(int i=0;i<visina;i++){
    		j+=2;
    	}
    	Zvezdice.trikotnik(odmik, visina);
    	Zvezdice.pravokotnik(odmik, visina, j);
    	Zvezdice.dvaPravokotnika(odmik+(j-(3*j/5+2*j/5)), 1*j/5, visina, 2*j/5);
    }
    static void X(int n){
    	for(int i=0;i<2*n-1;i++){ //visina
    		for(int j=0;j<3;j++){	//po 3 vrstice za kvadratek	
    			if (i<(2*n-1)/2){
	    			System.out.print(new String(new char[i*5]).replace("\0", " "));
			    	Zvezdice.drawStars(5);
    				System.out.print(new String(new char[((2*n-1)-2-2*i)*5]).replace("\0", " "));
			    	Zvezdice.drawStars(5);
			    	System.out.println();	
    			}else{
    				System.out.print(new String(new char[(2*n-2-i)*5]).replace("\0", " "));
    				Zvezdice.drawStars(5);
    				if (i==((2*n-1)/2)){ //za sredino
    					System.out.println();
    				}
    				if (i>(2*n-1)/2){	//za od sredine naprej
		    			System.out.print(new String(new char[(2*i-n-(n-1))*5]).replace("\0", " "));
				    	Zvezdice.drawStars(5);
				    	System.out.println();
    				}
    			}
    		}
    	}
    }
}
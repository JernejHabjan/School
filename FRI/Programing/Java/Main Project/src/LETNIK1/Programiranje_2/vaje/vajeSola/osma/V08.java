package LETNIK1.Programiranje_2.vaje.vajeSola.osma;

import LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class V08 {
	static ArrayList<Zogica> tabela = new ArrayList<Zogica>();
    public static void main(String[] args) {
        // nastavimo koordinatni sistem
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        constructBalls(10);
        
        // glavna zanka
        while (true)  { 
        	StdDraw.show(20);
        	// brisemo ozadje
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledSquare(0, 0, 1.0+1.0*0.2);

        	for(Zogica z: tabela){
	            // trk ob steno
        		
        		
        		for(Zogica z1:tabela){ //collision
        			if (z.x!= z1.x && z.y!=z1.y){
	        			if ( Math.sqrt( Math.pow((z.x-z1.x),2)+Math.pow((z.y-z1.y),2) ) <= z.radius){
	        				
	        				z.vy = -z.vy;
	        				z.vx = -z.vx;
	        			}
        			}
        		}
        		
        		
        		
	            if (Math.abs(z.y + z.vy) >= 1.0+0.2 -z.radius) z.vy = -z.vy;
	            if (Math.abs(z.x + z.vx) >= 1.0+0.2 -z.radius) z.vx = -z.vx;
	            
	            
	            // posodobimo pozicijo
	            z.y += z.vy; 
	            z.x += z.vx;
	            
	            
	            // narisemo zogico   
	            StdDraw.setPenColor(new Color(z.r,z.g,z.b)); 
	            //LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw.setPenColor(LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw.ORANGE);
	            StdDraw.filledCircle(z.x,z.y, z.radius); 
	
	            // prikazemo vse naenkrat in pocakamo za 20ms
	            
        	}
        	
        }   
    }

    
    static void constructBalls(int stZogic){
    	int max=1;
    	int min=-1;
    	
    	Random rnd = new Random();
    	for(int i=0;i<stZogic;i++){
    		//polmer
    		double rMin=0.01;
        	double rMax=0.2;
        	double rad= rMin + (rMax - rMin) * rnd.nextDouble();
        	
    		
    		//x,y
    		double x = min + (max-rad - min) * rnd.nextDouble();
        	double y= min + (max-rad - min) * rnd.nextDouble();
        	
        	//hitrost
        	double vMax=0.02;
        	double vMin=-0.02;
//        	double vMax=0.01;
//        	double vMin=-0.01;
        	double vx = vMin + (vMax - vMin) * rnd.nextDouble();
        	double vy= vMin + (vMax - vMin) * rnd.nextDouble();
        	
        	//barva
        	int r= rnd.nextInt(255);
        	int g= rnd.nextInt(255);
        	int b= rnd.nextInt(255);
        	
        	
        	Zogica z = new Zogica(x,y,rad,vx,vy,r,g,b);
        	tabela.add(z);
    	}
    	
    }
}
 
class Zogica{
	double x;
	double y;
	double radius;
    double vx = 0.023;    // hitrost
    double vy = 0.023;    // hitrost
    int r;
	int g;
	int b;
    
	Zogica(double x, double y, double rad,double vx, double vy, int r, int g, int b){
		this.x =x;
		this.y=y;
		this.radius=rad;
		this.r = r;
		this.g = g;
		this.b = b;
		this.vx=vx;
		this.vy=vy;
	}
}
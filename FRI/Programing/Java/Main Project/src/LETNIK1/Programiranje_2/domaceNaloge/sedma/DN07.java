package LETNIK1.Programiranje_2.domaceNaloge.sedma;

import java.util.Random;
import java.awt.Color;

public class DN07 {
    public static void main(String[] args){
        for(int i=0;i<70;i++){
            Random rnd = new Random();
            double x = 0 + (1) * rnd.nextDouble();
            double y = -0.3 + (0.7 + 0.3) * rnd.nextDouble();
            double h = 0.05 + (0.5 - 0.05) * rnd.nextDouble();
            Color c = new Color(rnd.nextInt(255), rnd.nextInt(200), rnd.nextInt(255));
            
            narisi(x,y,c,h);
        }
        
    }
    public static void narisi(double x, double y, Color color, double heigth){
        double r= 0.1*2*heigth;
        double lineEnd =y+heigth;
        
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(r/7);
        StdDraw.line(x, y, x, y+heigth);
        
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, lineEnd+r, r);
        StdDraw.filledCircle(x, lineEnd-r, r);
        
        StdDraw.filledCircle(x+r, lineEnd, r);
        StdDraw.filledCircle(x-r, lineEnd, r);
        
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x, lineEnd, 2*r/3);
    }
}



/**
 * Risanje matematiènih funkcij z uporabo razreda StdDraw.
 * 
 * @author tomaz
 */
public class GrafStdDraw {
  static int W = 1000;
  static int H = 1000;

  
  static void graf() {
    double x1 = -2*Math.PI;
    double x2 =  2*Math.PI;
    double y1 = -1;
    double y2 =  1;
    
    for (int i = 0; i < W; i++) {
      double x = i*(x2-x1)/W + x1;
      
      double y = Math.sin(x);
      
      int j = (int) Math.floor(H * (y-y1)/(y2-y1));
     
      if (j >= 0 && j < H)
        StdDraw.point(i, H-j-1);
    }
    
  }
  
  public static void main(String[] args) {
    StdDraw.setXscale(0, W);
    StdDraw.setYscale(0, H);
    
    graf();
  }

}

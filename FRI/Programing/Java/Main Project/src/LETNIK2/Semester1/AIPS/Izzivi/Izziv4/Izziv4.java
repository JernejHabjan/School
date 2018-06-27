package LETNIK2.Semester1.AIPS.Izzivi.Izziv4;

import LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw;

import java.awt.*;

/**
 * Created by Jernej Habjan on 28. 11. 2016.
 */

class Global1{
    static int TREESIZE = 1;
    static String[] arrayString;
    static Node1[] nodes;


    static int[] arrayY;
    static int[] arrayX;

    static String str = "";

}

public class Izziv4 {
    public static void main(String[] args){
        String argument = "";
        if(args.length == 1){
            argument = args[0];
        }else{
            argument = "5";
        }
        if (!argument.chars().allMatch( Character::isDigit )) {
            System.out.println("CANNOT CONTINUE - NOT NUMBER");
            System.exit(1);
        }

        Global1.TREESIZE = Integer.parseInt(argument);
        Screen.initScreen();

        //CREATE LOCATIONS
        Global1.arrayString = createArray(Global1.TREESIZE);
        Global1.nodes= spawnCircles(Global1.arrayString);




        //X SPREMEN
        /*
        inorder(0);
        for(int i = 0; i < LETNIK2.Semester1.AIPS.Izzivi.Izziv4.Global1.TREESIZE; i++){
            LETNIK2.Semester1.AIPS.Izzivi.Izziv4.Global1.nodes[i].x = LETNIK2.Semester1.AIPS.Izzivi.Izziv4.Global1.str.indexOf(i);
        }
        System.out.println("TREE");
        for(int i = 0; i < LETNIK2.Semester1.AIPS.Izzivi.Izziv4.Global1.TREESIZE; i++){
            System.out.print(LETNIK2.Semester1.AIPS.Izzivi.Izziv4.Global1.nodes[i].x + " ");
        }
*/

        //DRAW

        drawLines();
        for (Node1 node : Global1.nodes) {
            node.draw();
        }




        System.out.println("\nNIVOJSKI IZPIS:");
        nivojski();
        System.out.println("\nPREORDER IZPIS:");
        preorder(0);
        System.out.println("\nINORDER IZPIS:");
        inorder(0);
        System.out.println("\nPOSTORDER IZPIS:");
        postorder(0);

    }

    static void drawLines(){

        StdDraw.setPenRadius(0.01);

        for(int i = 0; i < Global1.TREESIZE; i++) {
            if(2*i+1 < Global1.TREESIZE){
                Node1 parent = Global1.nodes[i];
                Node1 child = Global1.nodes[2*i +1];
                StdDraw.line(parent.x, parent.y ,child.x, child.y);
            }
            if(2*i+2 < Global1.TREESIZE){
                Node1 parent = Global1.nodes[i];
                Node1 child = Global1.nodes[2*i +2];
                StdDraw.line(parent.x, parent.y ,child.x, child.y);
            }


        }

    }










    private static String[] createArray(int TREESIZE){

        String[] array = new String[TREESIZE];
        for (int i = 0; i < TREESIZE; i++) {
            array[i] = ((char)((int)'A'+i))+"";
        }

        return array;
    }



    private static Node1[] spawnCircles(String[] arrayString){
        Global1.arrayY = getY(arrayString);
        Global1.arrayX = getX(arrayString);


        Node1[] arrayCircles = new Node1[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {


            arrayCircles[i] = new Node1(Global1.arrayX[i], Global1.arrayY[i], arrayString[i]);
        }
        return arrayCircles;
    }

    private static int[] getX(String[] array){
        int[] arrayX = new int[array.length];
        for (int i = 0; i < array.length; i++) {



            arrayX[i] = i* Screen.SQUARE_WIDTH;
        }return arrayX;
    }

    private static int[] getY(String[] array){
        int[] arrayY = new int[array.length];
        for(int i = 0; i < array.length; i++){
            arrayY[i] = ((int)(Math.log(i+1) / Math.log(2)))*Screen.SQUARE_HEIGHT;
        }return arrayY;
    }

    private static void inorder(int i){
        if(2*i+1  < Global1.TREESIZE ) //-> če je levi sin in obstaja
        inorder(2*i+1);

        Global1.str = Global1.str + Global1.arrayString[i];

        System.out.print(Global1.arrayString[i]+ " ");

        if(2*i+2  < Global1.TREESIZE ) //-> če je desni sin in obstaja
        inorder(2*i+2);

    }


    static void nivojski() {
        for (int i = 0; i < Global1.TREESIZE; i++) {
            System.out.print(Global1.arrayString[i] + " ");
        }
    }

    private static void preorder(int i){
        System.out.print(Global1.arrayString[i] + " ");
        if(2*i+1  < Global1.TREESIZE ) //-> če je levi sin in obstaja
            preorder(2*i+1);

        if(2*i+2  < Global1.TREESIZE  ) //-> če je desni sin in obstaja
            preorder(2*i+2);
    }


    private static void postorder(int i){
        if(2*i+1  < Global1.TREESIZE) //-> če je levi sin in obstaja
            postorder(2*i+1);

        if(2*i+2  < Global1.TREESIZE) //-> če je desni sin in obstaja
            postorder(2*i+2);
        System.out.print(Global1.arrayString[i]+ " ");
    }


}


class Screen{

    static final int SCREENWIDTH = 500;
    static final int SCREENHEIGHT = 500;

    static final int SQUARE_WIDTH = SCREENWIDTH/Global1.TREESIZE;
    static final int SQUARE_HEIGHT = SCREENHEIGHT/(((int)(Math.log(Global1.TREESIZE) / Math.log(2))));
    static void initScreen(){

        StdDraw.setCanvasSize(SCREENWIDTH, SCREENHEIGHT);
        StdDraw.setXscale(0, SCREENWIDTH);
        StdDraw.setYscale(SCREENHEIGHT, 0 );

    }

}


class Node1{
    private Color cCircle = StdDraw.GRAY;
    private Color cStr = StdDraw.RED;
    private String str = "";
    int x;
    int y;
    private int r = (Math.min(Screen.SCREENWIDTH / Global1.TREESIZE, Screen.SCREENHEIGHT / Global1.TREESIZE))/2;


    Node1(int x, int y, String str){
        this.x = x;
        this.y = y;
        this.str = str;

    }

    void draw(){
        StdDraw.setPenColor(cCircle);
        StdDraw.setPenRadius(r);
        StdDraw.filledCircle(x, y, r);

        StdDraw.setPenColor(cStr);
        Font font = new Font("Arial", Font.BOLD, r);
        StdDraw.setFont(font);
        StdDraw.text(x, y, str);

    }
}

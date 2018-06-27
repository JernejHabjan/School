package LETNIK1.Programiranje_2.domaceNaloge.trinajsta;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


//TODO right click za brisat
//TODO reparent right

public class DN13 {
	static String algorithmOption = "";
	static String startOption = "";
	static String gridOption = "";
	static Boolean waitClick = true;
	static Boolean clickable=true;
	static Boolean animation=false;
	public static void main(String[] args) throws AWTException, IOException {

		Miscellaneous.initProgram();

		while(DN13.waitClick){
			Miscellaneous.sleep(500);
		}
		
		Grid g = new Grid();

		switch (DN13.gridOption){
			case "Grid8":
				Grid.SQ_NUM=8;
	    		g.initSq(); 
				break;
			
	        case "Grid16":
	        	Grid.SQ_NUM=16;
	    		g.initSq(); 
				break;
				
			case "Grid32":
				Grid.SQ_NUM=32;
	    		g.initSq(); 
				break;
				
			
			case "Grid64":
				Grid.SQ_NUM=64;
				g.initSq(); 
				break;
	
				
			default:
				Grid.readPicture(DN13.gridOption);
				break;
		}

		Grid.setHeuristic();

		while(true){
//			TODO ->  OPTIONAL SET BRUSH SIZE PA KROGEC OKROG MIŠKE K BARVAŠ
//			brush not working 
//			double x = LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw.mouseX();
//	        double y = LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw.mouseY();
//	        LETNIK1.Programiranje_2.domaceNaloge.trinajsta.StdDraw.circle(x, y, 40);
			

			
            if (clickable && StdDraw.mousePressed()){ //spremeni barvo
            	Miscellaneous.onClick();	
            }
            if (StdDraw.isKeyPressed(32) || StdDraw.isKeyPressed(10) ){ //če prtisneš space al pa enter
            	clickable = false; //disables clicks
    
            	for(Square r: Grid.squareArray){ //nardiš free array zarad hitrosti
            		if(r.isfree){
            			Grid.squareFreeArray.add(r);
            		}
            	}

            	switch (DN13.algorithmOption){

		            case "JernejOld":
		    			Algorithm a = new Algorithm();
		    			a.path();
		    			break;
		    		
		            case "A*":
		            	AStar aS = new AStar();
		        		aS.find();
		        		break;
		    	}
            }
		}
	}

}


class Grid{
	final static int WIDTH = 512;
	final static int HEIGHT = 512;
	static int SQ_NUM =8;
	static int squareSize=2*HEIGHT/SQ_NUM;
	static ArrayList<Square> squareArray = new ArrayList<Square>();
	static ArrayList<Square> squareFreeArray = new ArrayList<Square>();
	static Square endSq;
	static Square startSq;
	static Boolean startNotSet=true;
	static Boolean endNotSet=true;

	@SuppressWarnings("unused") // da umiri prvi if
	void initSq(){
		StdDraw.setCanvasSize(WIDTH, HEIGHT);
		StdDraw.setScale(-WIDTH, HEIGHT);//Screen size

		if(SQ_NUM<3 || (SQ_NUM & (SQ_NUM - 1)) != 0){ //error report  in če potenca st 2 -> (RECT_NUM & (RECT_NUM - 1)) != 0)
			Miscellaneous.popupErrorGui("Inappropriate grid size.");
		}
		StdDraw.setPenColor(StdDraw.GRAY); //NARIŠEŠ BACKROUND
        StdDraw.filledSquare(0, 0, HEIGHT+HEIGHT*0.1);
        
		for (int i = 0; i < 2*HEIGHT; i+=2*HEIGHT/SQ_NUM) { //2*height/RECT_NUM -> cez celo->2x povrsina, zdeli na kvadratke
        	for (int j = 0; j < 2*WIDTH; j+=2*HEIGHT/SQ_NUM) {
        		Square sq = new Square();
        		sq.init(-WIDTH+j,HEIGHT-i, (2*HEIGHT/SQ_NUM)/2-2, 230,230,230);
        		if(i==0 || j==0 || i==2*HEIGHT-2*HEIGHT/SQ_NUM || j==2*HEIGHT-2*HEIGHT/SQ_NUM){ //naredi rdečo obrobo okrog mreže
        			sq.isfree=false;
        			sq.setColor(250, 100, 100); //naštima se zasedena barva
        		}
        		sq.draw();
        		squareArray.add(sq);
    		}
		}
		switch (DN13.startOption){
    	case "Random":
    		initRandomStartEnd();
    		break;
    	case "Fixed":
    		initFixedStartEnd();
    		break;
    	case "Set yourself":
    		initClickStartEnd();
    		break;
		}
	}

	static void readPicture(String mazeType) throws IOException {

    	StdDraw.setCanvasSize(Grid.WIDTH, Grid.HEIGHT);
    	File maze= new File("src\\LETNIK1\\domaceNaloge\\trinajsta"+mazeType+".jpg");
    	
    	Image image = ImageIO.read(maze);
    	Raster raster = ((BufferedImage) image).getData();
    	BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
    	
    	int w = img.getWidth();
    	int h = img.getHeight();

    	StdDraw.setScale(-(w/8)*WIDTH, (w/8)*HEIGHT);//Screen size
    	
    	
    	StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(0, 0, (w/8)*(HEIGHT+HEIGHT*0.1));
        
    	for (int j = 0; j < w; j++) {
    	    for (int k = 0; k < h; k++) {
    	    	
    	    	Square sq= new Square();
    	    	sq.init((-w/2+j)*Grid.squareSize, (-h/2+k)*Grid.squareSize, Grid.squareSize/2, 178,34,34);
    	    
    	    	if(raster.getSample(j, k, 0) == 255){
    	    		
    	    		sq.setColor(200, 200, 200);
    	    	}else{
    	    		sq.isfree=false;
    	    	}
    	    	sq.draw();
    	    	squareArray.add(sq);
    	    }
    	}
    	switch (DN13.startOption){
	    	case "Random":
	    		initRandomStartEnd();
	    		break;
	    	case "Fixed":
	    		Miscellaneous.popupGui("Error","No fixed start for image");
	    		initRandomStartEnd();
	    		break;
	    	case "Set yourself":
	    		initClickStartEnd();
	    		break;
    	}
    }

	static void initClickStartEnd(){
		while(Grid.startNotSet){
    		start();	
    	}
    	while(Grid.endNotSet){
    		end();
    	}
	}

	static void start(){
		
		double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        
        if(StdDraw.mousePressed()){

	        for(Square k: Grid.squareArray){//SO  SLOWWWWWWW
	        	int squareSize=Grid.HEIGHT/Grid.SQ_NUM;
	        	if(k.isfree==true){
		        	if( ((x-squareSize)<k.x&(k.x<(x+squareSize))) & ((y-squareSize)<k.y&(k.y<(y+squareSize)))){
		        		k.setColor(10, 255, 150);
		        		k.isfree=false;  //state ma zaseden
		        		k.isStart=true;
						startSq=k;
		        		k.draw();
		        		Grid.startNotSet=false;
		        	}
	        	}
	        }
        }
	}
	static void end(){
		double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        if(StdDraw.mousePressed()){

	        for(Square k: Grid.squareArray){
	        	int squareSize=Grid.HEIGHT/Grid.SQ_NUM;
	        	if(k.isfree==true){
		        	if( ((x-squareSize)<k.x&(k.x<(x+squareSize))) & ((y-squareSize)<k.y&(k.y<(y+squareSize)))){
		        		k.setColor(255, 255, 150);
		        		k.isEnd=true;
		        		endSq=k;
		        		k.draw();
		        		Grid.endNotSet=false;
		        	}
	        	}
	        }
        }
	}

	static void initRandomStartEnd(){
		Random rnd = new Random();
		Boolean startCreated=false;
		int i=0;
		while(i<squareArray.size()){ //da se ne zacikla
			int randStart= rnd.nextInt(squareArray.size()); //rectangleArray.size() ni vključen
			Square sq =squareArray.get(randStart); //later lah zbrišeš Kvadrat kv pa daš sam rectangleArray.get(i) za hitrejš delovanje
			if(startCreated==false && sq.isfree){ //createStart
				sq.isStart=true;
				sq.isfree=false;
				sq.setColor(10, 255, 150);
				startCreated=true;
				startSq=sq;
				sq.draw();
			}if(startCreated==true && sq.isfree){ //createEnd
				sq.isEnd=true;
				//kv.isfree=false; ->konc more bit frej d ga lah poišče
				sq.setColor(255, 255, 150);
				endSq=sq;
				sq.draw();
				break;
			}i++;
		}
	}
	
	static void initFixedStartEnd(){
		int start=SQ_NUM+1;
		Square sq = squareArray.get(start);
		sq.isStart=true;
		sq.isfree=false;
		sq.setColor(10, 255, 150);
		startSq=sq;
		sq.draw();

		
		//end
		int end = SQ_NUM*SQ_NUM-SQ_NUM-2;
		Square sqEnd = squareArray.get(end);
		sqEnd.isEnd=true;
		//kv.isfree=false; ->konc more bit frej d ga lah poišče
		sqEnd.setColor(255, 255, 150);
		endSq=sqEnd;
		sqEnd.draw();


	}

	static double heuristic(Square node){ //more here: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
		double euclid=Math.sqrt(Math.pow((node.x-endSq.x),2)+Math.pow((node.y-endSq.y),2));
		
		
		
		
		
		
		int D = 1;
		double D2 = Math.sqrt(2); //this is called the octile distance.
	    double dx = Math.abs(node.x - endSq.x);
	    double dy = Math.abs(node.y - endSq.y);
	    
	    //return D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy); // vrne Manhattansko razdaljo od noda do cilja
	    
	    return euclid;
	    
	    
	    
	    
	
	}
	
	static void setHeuristic(){
		

		StdDraw.setPenColor(StdDraw.BLACK);
		for (Square sq: squareArray){
			if (sq.isfree || sq.isStart){
				sq.H=heuristic(sq); //da lepo prikaže 10, 20, 2*heig... je velikost kvadratka
				
				
				if(squareArray.size()<256){
					Miscellaneous.screenText(sq.x+squareSize/8, sq.y+squareSize/3, squareSize/8, "H:"+(int)sq.H);
				}
			}
		}
	}

}

class Algorithm{
	
	@SuppressWarnings("unused")
	void path(){
		while(true){
			for(Square sq: Grid.squareArray){
				if (sq.isStart){
					Square closest=findClosestRect(sq);
					if(closest==null){ //čej error
						Miscellaneous.popupErrorGui("Start is obstructed... Cannot continue.");
					}//če pa ni errorja
					
					if (closest.isEnd){
						drawLine(sq.x,sq.y,closest.x,closest.y);
						Miscellaneous.popupGui("Success","PATH FOUND!");
						 System.exit(0);
					}
					sq.isStart=false; //disables old start
					sq.parent=closest; /////////////////////////////////////////////////////////////////////////////////////////
					closest.isStart=true; /////////////////// ZAENKRAT !!!!!
					closest.isfree=false;
					closest.setColor(150, 255, 150); //svetlozelena
					closest.draw();
					drawLine(sq.x,sq.y,closest.x,closest.y);
				}
			}
			if(DN13.animation){ //za animacijo
				Miscellaneous.sleep(5000/Grid.squareArray.size()); //za vecji grid dela hitrejs

			}
		}
	}
	
	Square findClosestRect(Square startSq){
		
		Square end = Grid.endSq;

		Square closest=null;
		double tempDistStartNew=0;   
		double tempDistEndNew=0; 
		for(Square newSq: Grid.squareFreeArray){ //gre skozi vse kvadratke in išče najbližjega
			if(newSq.isfree){ //pregleduje samo proste

				double distStartNew=Math.sqrt(Math.pow((newSq.x-startSq.x),2)+Math.pow((newSq.y-startSq.y),2)); //izračuna distance
				double distEndNew =Math.sqrt(Math.pow((newSq.x-end.x),2)+Math.pow((newSq.y-end.y),2));
				
				//tok je velk kvadratek ->2*LETNIK1.Programiranje_2.domaceNaloge.trinajsta.Grid.HEIGHT/LETNIK1.Programiranje_2.domaceNaloge.trinajsta.Grid.RECT_NUM
				if(closest==null || (distEndNew+distStartNew <tempDistStartNew+tempDistEndNew)){ 
					if (distStartNew<= Math.sqrt(2)*2*Grid.HEIGHT/Grid.SQ_NUM){ //distance < diagonale
						tempDistStartNew=distStartNew;
						tempDistEndNew = distEndNew;
						closest=newSq; //postane to najbližji kvadratek
					}
				}
			}
		}return closest; //vrneš tist kvadrat kj closest
	}

	static void drawLine(double x1, double y1, double x2, double y2){
		StdDraw.setPenColor(new Color(0,0,0)); 
		StdDraw.line(x1, y1, x2, y2);
	}
}

class AStar{
	static Square endR = Grid.endSq;
	static Square startR = Grid.startSq;
	static ArrayList<Square> openList = new ArrayList<Square>();
	static ArrayList<Square> closedList = new ArrayList<Square>();
	
	void find(){
		
		Square cur = startR;
		openList.add(cur);
		while(!openList.isEmpty()){
			Square current=cur;
			double lowestF=99999;
			for(Square lowest: openList){ //chooeses one with least F
				if(lowest.F<lowestF){
					current=lowest;
					lowestF=lowest.F;
				}
			}
			if(!current.isStart && !current.isEnd){
				current.setColor(80, 150, 150);
				current.draw();
			}
			if(current.isEnd){

				traceBack();// -izriše pot
				Miscellaneous.popupGui("Success!","PATH FOUND!");
				System.exit(0);
			}
			for(int i=0; i<openList.size();i++){ //pops it out of array
				if(openList.get(i)==current){

					openList.remove(i);
				}
			}
			closedList.add(current);
			for(Square neigbour: neigbours(current)){
				if(closedList.contains(neigbour)){
					continue;
				}
				double tentativeG=current.G+Math.sqrt(Math.pow((neigbour.x-current.x),2)+Math.pow((neigbour.y-current.y),2));
				
				if(!openList.contains(neigbour)){
					openList.add(neigbour);
				}else{
					if (tentativeG>=neigbour.G){
						continue;
					}
				}
				neigbour.parent=current;
				neigbour.G=tentativeG;
				neigbour.F=neigbour.G+Grid.heuristic(neigbour);

				
			}
			if(DN13.animation){
				Miscellaneous.sleep(200);
			}
				
				
			
		}Miscellaneous.popupErrorGui("Start is obstructed... Cannot continue.");
			
	}
	
	static ArrayList<Square>  neigbours(Square node){  //return array of neigbours
		ArrayList<Square> neigbour= new ArrayList<Square>();
		for(Square r: Grid.squareFreeArray){
			if(r.isfree){
		
				double G=Math.sqrt(Math.pow((r.x-node.x),2)+Math.pow((r.y-node.y),2)); //calculates distance
				
				if (G<= 2*Grid.HEIGHT/Grid.SQ_NUM){ //distance < diagonal

					neigbour.add(r); //adds to neigbour array
					
					//writes F
					r.G=G;
					double tempBest_F = r.H+ G;
					if(Grid.squareArray.size()<1024){
						Miscellaneous.screenText(r.x-Grid.squareSize/8, r.y-Grid.squareSize/3, Grid.squareSize/12, "F:"+(int)tempBest_F);
					}
					 
				}
			}
		}return neigbour;
	}
	
	
	

	static void traceBack(){
		int i=0;
		Square current = endR; // da se začne nakonc
		while(i<Grid.squareArray.size()){
			if(current.isStart){ //ko pride na začetek
				break;
			}
			
			drawLine(current.x,current.y, current.parent.x,current.parent.y); //nariše crto med nodom in parentom
			current= current.parent;
			i++;
		}
	}
	
	static void drawLine(double x1, double y1, double x2, double y2){ //NAKONC
		StdDraw.setPenColor(new Color(0,0,0)); 
		StdDraw.line(x1, y1, x2, y2);
		
	}
}

class Miscellaneous{
	
	public static void initProgram(){
		OptionWindow myWindow = new OptionWindow("Init Program");
        myWindow.setSize(330, 200);
        myWindow.setLocation(600, 100);
        myWindow.setVisible(true);
	}
	
	public static void screenText(float yPosition,String text){
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(0.5, yPosition, text);
	}
	
	public static void screenText(float xPosition, float yPosition,int size,String text){
		Font font = new Font("Arial", Font.BOLD, size);
		StdDraw.setFont(font);
		StdDraw.text(xPosition, yPosition, text);
	}

	public static void popupErrorGui(String errorMessage){
		//import javax.swing.JOptionPane; to mors importat za to
		//dokumentacija -> https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
		JOptionPane.showMessageDialog(null, errorMessage, "Error Message Report",JOptionPane.ERROR_MESSAGE);
		System.exit(1); //exites program
	}
	
	public static void popupGui(String title, String message){
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE );
	}

	public static void sleep(int milliseconds){
		try {
		    Thread.sleep(milliseconds);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	static void onClick(){
        // mouse location
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        for(Square k: Grid.squareArray){//SO  SLOWWWWWWW
        	int squareSize=Grid.HEIGHT/Grid.SQ_NUM;
        	if(k.isfree==true && !k.isEnd){
	        	if( ((x-squareSize)<k.x&(k.x<(x+squareSize))) & ((y-squareSize)<k.y&(k.y<(y+squareSize)))){
	        		k.setColor(250, 100, 100);
	        		k.isfree=false;  //state ma zaseden
	        		k.draw();
	        	}
        	}
        	
        	/*else{   //ZA RIGHT BUTTON
        		if(k.isfree==false){
		        	if( ((x-squareSize)<k.x&(k.x<(x+squareSize))) & ((y-squareSize)<k.y&(k.y<(y+squareSize)))){
		        		k.setColor(100, 100, 100);
		        		k.isfree=true;  //state ma zaseden
		        		k.draw();
		        	}
	        	}
        	}
        	*/
        		
        	
        }
	}

}

class OptionWindow extends Frame implements WindowListener,ActionListener {
    final static JComboBox<String> grid = new JComboBox<String>();
    final static JComboBox<String> alg = new JComboBox<String>();
    final static JComboBox<String> start = new JComboBox<String>();
    static Boolean animation=false;
    
    public OptionWindow(String title) {
    	super(title);
    	
	    //start:
	    JLabel lbl = new JLabel("Start:");
	    setVisible(true);
	    add(lbl);
	    String[] start = { "Random","Fixed", "Set yourself"};
	    for(String s: start){
	    	OptionWindow.start.addItem(s);
	    }OptionWindow.start.setVisible(true);
	    add(OptionWindow.start);
	    
	    //algorithm:
	    JLabel lbl1 = new JLabel("LETNIK1.Programiranje_2.domaceNaloge.trinajsta.Algorithm");
	    lbl1.setVisible(true);
	    add(lbl1);
	    String[] alg = { "A*","JernejOld"};
	    for(String s: alg){
	    	OptionWindow.alg.addItem(s);
	    }OptionWindow.alg.setVisible(true);
	    add(OptionWindow.alg);

	    
	    //LETNIK1.Programiranje_2.domaceNaloge.trinajsta.Grid:
	    JLabel lbl3 = new JLabel("LETNIK1.Programiranje_2.domaceNaloge.trinajsta.Grid:");
	    lbl3.setVisible(true);
	    add(lbl3);
	    String[] grid = { "Grid8","Grid16","Grid32","Grid64","maze16","maze64","maze256"};
	    for(String s: grid){
	    	OptionWindow.grid.addItem(s);
	    }OptionWindow.grid.setVisible(true);
	    add(OptionWindow.grid);
	    
	    
	  //In initialization code:
	    JCheckBox animButton = new JCheckBox("animation");
	    animButton.setMnemonic(KeyEvent.VK_C); 
	    animButton.setSelected(false);
	    add(animButton);
	    

	    animButton.addActionListener(this);

	    JLabel lbl2 = new JLabel("<html>Draw squares with mouse,<br/>start pathfind with space or enter.</html>");
	    lbl2.setVisible(true);
	    add(lbl2);

	    
	    JButton btn = new JButton("OK");
        add(btn);
        btn.addActionListener(this);
	    
        setLayout(new FlowLayout());
        addWindowListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
    	
        if ("javax.swing.JCheckBox".equals(source.toString().substring(0,21))) {
            if(OptionWindow.animation==false){
            	OptionWindow.animation=true;
            }else{
            	OptionWindow.animation=false;
            }
        }
        else{
	    	DN13.gridOption=OptionWindow.grid.getSelectedItem().toString();
	    	DN13.startOption=OptionWindow.start.getSelectedItem().toString();
	    	DN13.algorithmOption=OptionWindow.alg.getSelectedItem().toString();
	    	DN13.waitClick=false;
	    	DN13.animation=OptionWindow.animation;
	    	dispose();
        }
            
    }
    public void mouseReleased(MouseEvent e) {} //ZA OPTIONAL FIX ZA DOUBLE CLICK PA TI OBA NARDI PONESREC
    

    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

}

class Square{
	int x;
	int y;
	int side;
	int r;
	int g;
	int b;
	Boolean isfree = true;
	Boolean isStart = false;
	Boolean isEnd = false;
	Square parent=null;
	double H;
	double G;
	double F;
	
	public void init(int x, int y, int side, int r, int g, int b){
		this.x=x;
		this.y=y;
		this.side=side;
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	public void setColor(int r, int g, int b){
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	public void draw(){
		StdDraw.setPenColor(new Color(r,g,b)); 
		StdDraw.filledSquare(x, y, side);
	}
}
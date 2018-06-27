package LETNIK1.vajeDoma.vajePriprave;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Mess {
	public static void main(String[] args) {
		Mess mess = new Mess();
		MessPomozni messpomozni = new MessPomozni();
		MessExtended messextends = new MessExtended();
		Person person = new Person("");
		
		//polymorphism_______________________
		ArrayList<Human> group = new ArrayList<Human>();
	    group.add(new Male());
	    group.add(new Female());
	    // ... add more...
	    // tell the class to take a pee break
	    for (Human x : group) x.goPee();
	    //next level for zanka right here
	    Commander soldier = new Commander();
	    for(Human x: group) soldier.command(x);
	    //________________________________
	    mess.list(); //LIST V KERGA LAH DAŠ VSE
	    //________________________________
	    mess.mnozica(); //mnozica
	    //________________________________
	    //Mess.catchException(); //exception
	    //________________________________
		mess.splitter("lol\n");
		System.out.print(mess.equal()+"\n");
		MessPomozni.whileZanka();
		mess.array();
		System.out.print("\n");
		mess.arraylist();
		mess.mnozica();
		System.out.print("\n");
		Mess.zaokrozi(2.35434f);
		System.out.print("\n");
		System.out.print('\u0000'+" <-to je char\n");
		 //________________________________RANDOM
		int[] solutionArray = { 1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11 };
	    System.out.print("Before: ");
	    for (int i = 0; i < solutionArray.length; i++){
	        System.out.print(solutionArray[i] + " ");
	      }
	    System.out.println();
	    //________________________________
	    shuffleArray(solutionArray);
	    System.out.print("After: ");
	    for (int i = 0; i < solutionArray.length; i++){
	      System.out.print(solutionArray[i] + " ");
	    }
	    System.out.println();
	    //________________________________
	    Random r = new Random();
	    String word = "Animals";
	    System.out.println("Before: " + word );
	    word = scramble( r, word );
	    System.out.println("After : " + word );
	    //________________________________
	    person.way1();//dict
  		messpomozni.extendedhashmap();//dict
  		System.out.println(messpomozni.capitalize("lol"));
  		System.out.println(messpomozni.fib(22));
	    
  	    System.out.println(messextends.whileZanka1());
		MainNew matic = new MainNew();
		matic.mainnew();
		 //________________________________
		mess.okenckaGUI();
		 //________________________________
		Function<Double, Double> square = DoubleSemicolon::square; //functional interface
		double ans = square.apply(23d);
	}
	/*
	 PREDAVANJA:
	 Predavanje1: print
	 Predavanje2: 
	 	FORMAT ->System.out.printf("   %02d %9s%5.2f\n",i,"|",i*1.25);
		 	%f - float
			%c - char
			%s - string
			%x - hex
			%5d -da na levo 4 presledke če vneseš št manjše od 10..
		RANDOM
	 Predavanje3:
	 	scanner
	 	do while
	 Predavanje4:
	 	bitwise operators &, |, ^-ekskluzivni ali, << -predznačen pomik levo
	 		>> -predznačen pomik desno, >>> -nepredznačen pomik bitov v desno
	 		~ -eniški komplement
	 		shift v desno je celoštevilsko deljenje z 2!!!!!!
			v desetiškem je shift v desno deljenje z 10
	 	? operator: rezultat= pogoj? izraz1:izraz2;
	 	NAUČI SE (a<5)?lolo:thisiselse
	 	pisanje v datoteko
	 	branje iz datoteke
	 	Poglej si Filereader,BufferedReader !!!! za izpit
	 Predavanje5: 
	 	Packeti
	 	importi
	 	Switch
	 	DOSTOP:
	 		public -dostopno povsod in vsakomur
	 		protected - dostopno v vseh razredih tega paketa ter v vseh podrazredih(tudi če niso v tem paketu)
	 		default - dostopno v vseh razredih tega paketa
	 		private - dostopno samo v tem razredu
	 	CHARAT!!! ZA INDEKSIRAT SKOZI STRING
	 Predavanje6;
		 java.lang je automatsko importan
		 import static java.MATH.* ->(odsvetovano) lahko pišemo samo PI in ne Math.PI... pišemo tudi vse static metode
		 dostopnostna loćila:
			 public ->dostopen povsod
			 private ->dostopen samo v istem classu
			 protected -> v istem packagu in če inherita
			 brez -> če v istem packagu
	 Predavanje7:
	 	int x= 012 ->zapisano je v osmiškem zaradi 0 pred št
	 	int y= 0b101001100101 ->binarno
	 	long var = 132421343l; -> nakoncu mora biti l
	 	indexOf
	 	split("[: ]+"); ->splita po : in po spacu!!!!!!! -regularni izraz
	 		+ ->enega ali več : ali spacov
	 	StringBufer niz = new StringBufer("pomlad");
	 	niz.replace(0,1,"p");
	 	niz.reverse().toString();
	 Predavanje8:
	 	StringBuilder ->podobno kot stringBuffer ampak ni sinhroniziran za več threadov, zato hitrejši
	 	continue !! nadaljuje loop od začetka loopa,ne gre ven kot break
	 	POGLEJ SI STRINGBUFFER PA STRINGBUILDER !!!!!!!!!!
	 	toCharArray() ->naredi tabelo
	 	int a[][] = new int[3][3]; ->večdimenzionalna tabela
	 Predavanje9:
	 	algoritem na začetku(merge sort)
	 	potem classi
	 	
	 	za file
	 	new jframeform... ali other -> swing ->jframeform
	 	v okenčku  potem desno maš za "mobi"
	 	text-field 
	 	za gumb ->add listener... events -> action -actionperformed ... toj desn klik
	 		te vrže na kodo -> vsaki kontroli(okenčku lah damo ime ->F2, jih preimenujemo
	 		String neki = op1TF(ime k smo poimenoval text field).getText(); ->dobimo tekst iz okenčka
	 		rezultatTf.setText(rezultat);
	 		v fieldih so stringi
	 		not morš nastavt še na absolute layout k drgač bo gumb čez celo
	 Predavanje10:
	 	konstruktorji
	 	inheritance
	 	override
	 
	 Predavanje11:
	 	GOOD TIP -> v vsakem razredu napiši še prazen konstruktor da nimš problemov!!!
	 	--DEKLARIRANJE FUNKCIJE ON THE GO!!!
		 	artikel = art(1,3, new Function(){
		 		@override
		 		double vrednost(double e){
		 			System.out.println("neki");
		 		}
		 	}
		 	);
	 	--
	 	extends- razširi
	 	implements-vse metode implementiraš!!!!
	 	ce napišeš @override -> prepreč te da se zmotiš
	 	extenda lahko enega, implementira več
	 	-- ZA SUPER PRI KONSTRUKTORJU:
		 	class MojRazred {
			}
			IN
			class MojRazred{
			 MojRazred() {    //prevedeta se v isto stvar
			 super();
			 }
			} 
		--
		če rabiš "ABSTRACT CLASS" pa če hočeš da njegove METODE NEKAJ VSEBUJEJO daš IMPLEMENTS
			else EXTENDS
	Predavanje12:
		!!!!!!!!!!!!!!!!!!!!!!!!!!
		ZA IZPIT SI POGLEJ 12. PREDAVANJE
		!!!!!!!!!!!!!!
		catch(ArithmeticException e){
		System.out.println("prislo je do izjeme: "+e.getMessage());
		e.printStackTrace();
		}
		
		ZA SCANNERJA ZAPIRAT NA IZPITU!!!!!!!!!!!!
		!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		try with resources:  ->da vedno zapre file
			try(Scanner sc = new Scanner(new File("lolo.txt"))){
				... koda
			}catch{FileNotFoundException e) {
		      System.out.println("Napaka pri odpiranju!");
		    }
		za zapiranje fila:
			PREBER SI NA UČILNC
			
		za deljenje z nič
			if(b!=0){
			deli...
			}else{
				throw new RuntimeException("deljenje z 0 ni dovoljeno");
			}
		runtimeException-nepreverljiva izjema
		exception ->lahko vrneš.. in je treba v metodi k jo kličeš spet dat thows runtimeException
		EXCEPTION THROW:
		static int deliMaloDrugace(int a, int b) throws Exception {
	    if (b != 0)
	      return a/b;
		else{
		¸	throw new Exception("Deljenje z nic!");
		}
		...
		main:
		try:...
		catch (Exception ex) {
      		System.out.println(ex.toString());
		 }
		 
		arraylist vs linkedlist
		linkedlist nima indeksa
		arraylistu ni treba dati velikosti na začetku
		slovar:
			Map<String,integer> neki = new HashMap();
		ITERATOR:!!!
			Iterator it = dnevi.iterator();
		    while (it.hasNext())
		      System.out.println(it.next());
      
	Predavanje13: ######################################## POGLEJ SI TE READERJE!!
			V BINARNI:
		datainputstream
		dataoutputstream

		BufferedInputStream - pohitritev branja in pisanja
		WHILE != -1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		BufferedOutputStream
			bfs.read()
		//____________
		BRANJE SLIKE PRIMER:
		static final int n=4096
		Byte podatki = new Byte(n);
			stPrebranih=bfs.read(podatki)
			-vrne število prebranih bajtov
			for (int i=0; i<stPrebranih;i++){
				vsota=vsota+podatki[i]!!!!!!!!!!!!!!!!!!!!!!!
			}
		//____________
		public class Oseba extends Serializable !!!!!!!!!!!!!!!!!!!! -da jih lahko pišemo v dat
			Oseba a=new Oseba("rekt");
			
		FileOutputStream neki =FileOutputStream ("neki.dat");
		ObjectOutputStream fff= new ObjectOutputStream(neki);
		
		fff.close() -dost je d sam tega zapremo k se pol vsi
		
		outputstreamWriter ma not encoding
		printWriter(new outputstreamWriter ("neki"))
		//____________
		branje z neta:
		URL neki = new URL("www.google.com") // še https zravn n shit
		Scanner sc = new Scanner(neki.OpenStream());
		while sc.hasNext....
		
		- da se webpagu predstavš kot web browser -morš dat URL CONNECTION
		
		//GUI
			swing -minimalno odvisen od OS
		JPANEL ->samo odlagalna površina k se ne vid d jo dodaš
	Predavanje14: 
		// MOUSELISTENER IN GUI BODO ŠE NA IZPITU

		
		Make Button: //čez celo
			Jframe okno = new JFrame("Naslov");
			okno.setBounds(100,100,400,400);
			
			Jbutton gumb = new JButton("OK");
			okno.getContentPane().add(gumb);
			okno.setVisible(true);
		
		Absolutno pozicioniranje- da coder sam nastavi kje bo button
			#morš met container
			
			Jframe okno = new JFrame("Naslov");
			okno.setBounds(100,100,400,400);
			
			okno.getContentPane().setLayout(null); #ONEMOGOČIŠ AVTOMATSKO RAZPOREJANJE
			gumb.setBounds(50,50,100,20);
			
			Jbutton gumb = new JButton("OK");
			okno.getContentPane().add(gumb);
			okno.setVisible(true);
		Razporejevalniki:
			Flow layout: #takrat ko hočemo več komponent razporediti v eno vrstico
				za JPanel
				Jframe okno = new JFrame("Naslov");
				okno.setBounds(100,100,400,400);
				
				Jbutton gumb1= new Jbutton("prvi");
				Jbutton gumb2= new Jbutton("drugi");
				Jbutton gumb3= new Jbutton("tretji");
				Jbutton gumb4= new Jbutton("cetrti");
				
				FLOW LAYOUT
				#################################################################
				Container vsebnik = okno.getContentPane();
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout(FlowLayout.LEFT)); !!!!!!!!!!!!!!!!!!!!!
				vsebnik.add(panel);
				#################################################################
				panel.add(gumb1);
				panel.add(gumb2);
				panel.add(gumb3);
				panel.add(gumb4);
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);#############
				okno.setVisible(true);
			BorderLayout -zgoraj spodaj levo desno ali na sredino
				border layozt je razporejevalnik za okno
				če daš nekaj na sredinsko cono se razleze čez cev še avalaible prostor
				komponente se avtomatsko dodajejo v center
				v vsako cono lahko daš samo eno komponento...
				
				Jframe okno = new JFrame("Naslov");
				okno.setBounds(100,100,400,400);
				#SPODNJA VRSTICA:
				JPanel spodaj= new JPanel();
				spodaj.setLayout(new FlowLayout(FlowLayout.RIGHT)); # da je spodnja vrstica desno podavnana
				spodaj.setPreferedSize(new Dimension(100,1);
				Jbutton gumb1= new Jbutton("prvi");
				Jbutton gumb2= new Jbutton("drugi");
				spodaj.add(gumb1);
				spodaj.add(gumb2);
				#ZDAJ PA ŠE TEXT AREA ZA CENTER!
				JTextArea textArea = new JTextArea;
				
				#DODAŠ NA OKNO
				okno.getContextPane().add(textArea,BorderLayout.CENTER);
				okno.getContentPane().add(spodaj, BorderLayout.PAGE_END);
				
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);#############
				okno.setVisible(true);
			GridLayout -poveš kašno mrežo si želiš--- po vrsti to mrežo povne--nemorš rečt d doda v celico 3-3
				Jframe okno = new JFrame("Naslov");
				okno.setBounds(100,100,400,400);
				okno.getContentPane(new GridLayout(3,3)); # tri kolone po 3 stolpce
				
				for (int i=0; i<9;i++){
					JButton gumb= new JButton(""+i);
					okno.getContentPane().add(gumb);
				}
				okno.setVisible(true);
			CardLayout - stack kart
				recimo da se menja okno.. imamo kupček kart pa vidiš tazgorno, pa menjaš jih lahko--- show 7 pa pokaže 7. panel
				za to maš na učilnc /drivu
			
			GridBackLayout -kompleksnejši razporejevalnik (podoben GridLayout ampak se zmišluješ "v celico 8 dej to... ena nej bo čez 4 celice")
				za vsako komponento ki jo dodaš na formo ji morš dodat omejitve: gridX, gridY, gridWidth, gridHeight,fill, insets(margin), anchor(poravnava v celici -N, NW, W...)
				Weight x,Weight y, (uporablajo v kombinaciji z fill
				fill=HORIZONTAL ->da se extenda čez 3 celice
					
				Jframe okno = new JFrame("Naslov");
				okno.setBounds(100,100,400,400);
				JLabel ime = New JLabel("ime");
				JLabel priimek = New JLabel("priimek");
				JTextField imeTF = new JTextField(); ######### DA NOT VPIŠEŠ
				JTextField priimekTF = new JTextField();
				
				JTextArea vsebinaTA = new JTextArea();
				#DODAMO GUMBE
				JButton b1 = new JButton("prvi");
				JPanel gumbiP = new Jpanel();
				gumbiP.add(p1);
				
				
				JPanel sredina = new JPanel(new GridBagLayout());
				
				#CONSTRAINTS:
				GridBagConstraints gbc = new GridBagConstraints():
				gbc.gridx=0;
				gbc.gridy=0;
				sredina.add(ime,gbc);
				
				GridBagConstraints gbc = new GridBagConstraints():
				gbc.gridx=0;
				gbc.gridy=1;
				gbc.fill=GridBagConstraints.HORIZONTAL;
				sredina.add(imeTF,gbc);
				
				GridBagConstraints gbc = new GridBagConstraints():
				gbc.gridx=1;
				gbc.gridy=1;
				sredina.add(priimek,gbc);
				
				GridBagConstraints gbc = new GridBagConstraints():
				gbc.gridx=0;
				gbc.gridy=2;
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridWidht=2;
				sredina.add(priimekTF,gbc);
				
				
				
				okno.getContentPane().add(sredina,BorderLayout.CENTER);
				okno.getContentPane().add(gumbiP,BorderLayout.PAGE_END);
				
				okno.setVisible(true);
				
		ActionListener:
			class MojPoslusalec implements ActionListener{# ima eno metodo action performed
	 			public void actionPerformed(ActionEvent e){
	 				System.out.println("lol"+e.getActionCommand()); #dobi še ime od gumba
	 			}
	 			
	 		}
			
			MojPoslusalec posl = new MojPoslusalec();
			gumb1.addActionListener(posl);
			
		ActionListener2:
			
			MojPoslusalec posl = new MojPoslusalec();
			gumb1.addActionListener(new ActionListener(){ ############## ANONIMNI NOTRANJI RAZRED!!!-POZNA SPREM V TEMU CLASU
				public void ActionPerformed(ActionEvent e){
					System.out.println("neki novga");
					okno.setTtitle("lolololo");
				}
				
			});
			
		MouseMotionListener:					#MOUSE MOTION ADAPTER-da ni treba overridat
			okno.getContentPane().addMouseMotionListener(new MouseMotionAdapter(){ #ABSTRACTEN CLASS KI IMA ŽE IMPLEMENTIRANE METODE
				public void MouseMoved(MouseEvent e){
					System.out.println(e.getX()+" "+e.getY());
				}
			});
			
	 	WindowResize:
	 		okno.addComponentListener(new ComponentAdapter....
	 
	 
	 

	 ceil
	 floor
	 
	 ctrl +F11
	 ctrl+1
	 ctrl+M -maximize
	 
	 alt+left/right-to previous edited location
	 alt+up/down- move line up/down
	 
	 Math.max() takes 2 integers and not whole array
	 
	 long -max value 2^64-1
	 
	 Standard input:
	 boolean isEmpty()
	 int readInt()
	 String read String() - za vse data type
	 
	 datatype[] readAlldatatype()
	 
	 
	 Random library:
	 void shuffle(double[] a) -randomly shuffle the array a[]
	 
	 Statistics library:
	 double max(double[] a)
	 double mean(double[] a) -average
	 
	 String:
	 char charAt(int i)
	 String substring(int i,int j) -string od i do (j-1)
	 contains(String substring)
	 startsWith(String pre)
	 endsWith(string post)
	 int indexOf(String pattern) index of first occurance of pattern
	 int compareTo(String t)
	 String toLowerCase() modifies this string
	 String toUpperCase()
	 String ReplaceAll(String a, String b) -replaces all a-s with b-s
	 split(String delimiter)
	 equals(neki)
	 
	 ArrayList:
	 clone
	 forEach - MAP
	 sublist() -return portion of list from to
	 
	 Scanner:
	 input.hasNextDATATYPE
	 
	 
	 COLLECTIONS
	 
	 */
	
	
	public void polymorphism(){
		// iteriranje skozi različne classe ki so dedovani iz enega skupnega in izpisavanje metode z istim imenom
		Mess[] myArray = new Mess[2];
		Mess myPoly = new MessExtended();
		
		myArray[0]=myPoly;
		for(Mess x: myArray){
			x.mnozica();
		}
	}
	
	public void lambda(){
		/*LAMBDA EXAMPLES:
		(int a, int b) -> {  return a + b; };
		() -> System.out.println("Hello World");
		(String s) -> { System.out.println(s); }
		() -> 42;
		() -> { return 3.1415 };
		*/
		Runnable r = () -> System.out.println("hello world"); //lambda funct.

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		list.forEach(n -> System.out.println(n));
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7);
		int sum = list1.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
		System.out.println(sum);
	}
	
	public void stream(){
		List<String> myList =
			    Arrays.asList("a1", "a2", "b1", "c2", "c1");

			myList
			    .stream()
			    .filter(s -> s.startsWith("c"))
			    .map(String::toUpperCase)
			    .sorted()
			    .forEach(System.out::println);
			// C1
			// C2
	}
	

		
	static void bubbleSort(int t[]){ //hopefully dela
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length-1; j++) {
				if(t[i]<t[j]){
					t[i]=t[i]+t[j];
					t[j]=t[i]-t[j];
					t[i]=t[j]-t[i];
				}
			}
		}
	}
	
	static void mergeSort(int t[], int z, int k){//od vključno začetnega do vključno končnega
		if(k-z+1<=1){
			return;
		}
		int s= (z+k)/2;
		mergeSort(t,z,s);
		mergeSort(t,s+1,k);
		merge(t,z,k,s);
	}
	static void merge(int t[], int z, int k, int s){
		
		
	}
	
	public Set<String> mnozica(){
		String[] strArray = {"eins", "zwei", "drei", "vier","vier","zwei"};

		Set<String> strSet = Arrays.stream(strArray).collect(Collectors.toSet());
		System.out.println(strSet);
		return strSet;
	}

	public void minInArray() { //min value in array
	    int a[] = {2,5,3,7,8};
	    Arrays.sort(a);
	
	     int min =a[0];
	    System.out.println(min);
	    int max= a [a.length-1];
	    System.out.println(max);
	}

	public void makeSet(String a[]){ //MAKE SET
		Set<String> strSet = Arrays.stream(a).collect(Collectors.toSet());
	}

	public void okenckaGUI(){
		//import javax.swing.JOptionPane; to mors importat za to
		
		String fn = JOptionPane.showInputDialog("enter first num ");
		String fn1 = JOptionPane.showInputDialog("enter sec num ");
		int num1= Integer.parseInt(fn);//THATS HOW U PARSE STRING TO INT
		int num2= Integer.parseInt(fn1);//to je treba nardit ker je data podan v stringu
		int sum=num1+num2;
		JOptionPane.showMessageDialog(null, "rezultat je "+sum);
	}
	
	public void izpisFile() throws FileNotFoundException{
		  // ustvarim objekt z imenom "izhod" in ga povezem z datoteko
	    PrintWriter izhod = new PrintWriter(new File("viri/izpis.txt"));

	    // v datoteko izpisem nekaj besedila
	    izhod.println("V datoteko pisem enako kot na zaslon.");
	    izhod.println("Uporabljam lahko iste metode: println() in printf()");
	    izhod.println();
	    izhod.printf("Danes je %s, do konca tedna je se %d dni.\n", "ponedeljek", 5);

	    // datoteko na koncu OBVEZNO zaprem
	    izhod.close();
	}
	
	public void tryCatch(){
		 // V "try" blok zapremo vse dele programa, pri katerih lahko pride do izjeme.
	    // Ce do izjeme ne bo prislo, se bo izvrsila celotna koda try bloka; ce pa bo
	    // prislo do izjeme (recimo: File not found exception), se izvajanje try bloka
	    // prekine, program se nadaljuje v "catch" bloku.
	    try {
	      // odprem
	      Scanner datoteka = new Scanner(new File("viri/izpoved.txt"));

	      // preberem in izpisem
	      while (datoteka.hasNextLine())
	        System.out.println(datoteka.nextLine());

	      // zaprem
	      datoteka.close();   // DATOTEKE SE OBVEZNO ZAPRE
	    } catch (FileNotFoundException e) {
	      // Catch blok se izvede samo v primeru, da je prišlo do napake.
	      // V tem bloku primerno ukrapamo (na primer, obvestilo o napaki izpisemo na zaslon)
	      System.out.println("Prislo je do napake pri delu z datoteko");
	      System.out.println("Napaka: " + e);
	    }
	}

	public void reader(){
        String fileName = "temp.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	public void writer(){

        // The name of the file to open.
        String fileName = "temp.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("Hello there,");
            bufferedWriter.write(" here is some text.");
            bufferedWriter.newLine();
            bufferedWriter.write("We are writing");
            bufferedWriter.write(" the text to the file.");

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	    	    
	public void splitter(String a) {
		for (String x:a.split("")){
			//for zanka za skozi sezname
			System.out.print(x);
		}
	}
	
	public void scannerexample() {
		Scanner input = new Scanner(System.in);
		System.out.print("vpisi naslednje st: \n");
		Integer nextint =input.nextInt();
		System.out.println(nextint);
		System.out.print("vpisi naslednje st: \n");
		Integer nextint1 =input.nextInt();
		System.out.println(nextint1);
		input.close();
		// za dobit stevila iz scannerja scanner klicemo kt fuknkcijo
	}
	
	public void array() {
		//Array for "immutable" collections, List<T> for mutable collections.
		//LIST-> List myList = new ArrayList();   OR   List<MyType> myList = new ArrayList<MyType>();
		//ARRAY->int[] data = {10,20,30,40,50,60,71,80,90,91};
		//ARRAYLIST-> ArrayList<String> arrayList = new ArrayList<String>();
		
		int xs[]={1,2,3};
		//below example how can u declare array... 3 different way
		int[] myIntArray = new int[3];
		int[] myIntArray1 = {1,2,3};
		int[] myIntArray2 = new int[]{1,2,3};
		//_________________
		for (int i=0; i<xs.length;i++){
		//for zanka z indeksi
			System.out.print(xs[i]);
		}
	}
	
	void contains(String[] myArray){
		Arrays.asList(myArray).contains("something"); //returns true/false
	}
	void subarray(String[] myArray){
		//in general:  Arrays.copyOfRange(Object[] src, int from, int to)
		Arrays.copyOfRange(myArray, 0, myArray.length-4);
	}
	public void list(){
		//slower ker mora od enega do druzga hitrejs pa od arraylista ker ne rab kopirat
		//LIST NIMA TIPA... LAHKO NOTER STRINGI,INTI....
		
		List myList = new ArrayList();
		
		myList.add("object1");
		myList.add(2);
		
		Iterator iterator= myList.iterator();
		while(iterator.hasNext()){
			Object next = iterator.next();
			System.out.println(next);
		}
	}
	public void arraylist(){
		//arraylist je slower kot ostali ker ko ga razširi mora narediti nov arraylist in kopirat vse
		
		ArrayList<String> xs = new ArrayList<String>();
		xs.add("lol");
		xs.add(0, "feget");//<- adds feget na 0. indeksu
		System.out.print(xs.size());	
	}
	
	public Boolean equal(){
		String xs[]={"lol"};
		String xs1[]={"lol"};
		if (xs[0].equals(xs1[0])){
			return true;
		}
		return false;
	}
	
	public void switchi(){
		int age=3;
		switch (age){
		case 1:
			System.out.println("you can crawl");
			break;
		case 2:
			System.out.println("you can walk");
			break;
		case 3:
			System.out.println("you can speak");
			break;
		default:
			System.out.print("dunno");
		break;
		}
	}
	static void catchException(){
		FileReader fr=null;		
	      try{
	         File file=new File("file.txt");
	         fr = new FileReader(file);  char [] a = new char[50];
	         fr.read(a); // reads the content to the array
	         for(char c : a)
	         System.out.print(c); //prints the characters one by one
	      }catch(IOException e){
	          e.printStackTrace();
	       }
	       finally{	
	          try{
	              fr.close();
	          }catch(IOException ex){		
	               ex.printStackTrace();
	           }
	       }
	}
	
	public static void zaokrozi(Float x){
		System.out.printf("%.2f",x);
	}
	
	int count2orMoreAdjacentValues(int[] nums) { //vaja clumpana stevila http://codingbat.com/prob/p193817
		int counter=0;
		int stevilka=0;
		Boolean soseda =false;
		for (int x=0; x<nums.length;x++){
			if(x>0){
				if (nums[x]==stevilka){
					soseda=true;
				}
				else{
					if (soseda){
						counter++;
						stevilka=nums[x];
					}
				}
			}
			else{
				stevilka=nums[x];
			}
		}
	return counter;
	}
	
	public boolean has77(int[] nums) {// ČE 7 ZRAVEN 7 ALI ČE JE VMES ŠE ENA ŠT... npr 7,1,7 http://codingbat.com/prob/p168357
		Boolean ena = false;
		Boolean dve = false;
		for (int i=0;i<nums.length;i++){
			if (nums[i]==7){
				if (ena==true){ //za 7,7
					return true;
				}
				if (dve==true&&ena==true){
					return true;
				}
				ena=true;
			}else{
				if (dve==true){
					dve=false;
				}else{
					dve=true;
					ena=false;
				}
			}
		}
		return false;
	}
	
	boolean canBalance(int[] nums){    //vaja tier 3 if can split array on 2 parts(equal sum on both) http://codingbat.com/prob/p158767
		Boolean myBool=null;			//all correct :D
		int i;
		for(i=0;i<nums.length;i++){
			int vsotaLeft=0;
			int vsotaRight=0;
			for(int j=0; j<i+1;j++){
				vsotaLeft+=nums[j]; //left side
			}
			
			int tempSum=0; //TO SUM WHOLE ARRAY
			for(int k=0;k<nums.length;k++){
				tempSum+=nums[k];
			}				//TO SUM WHOLE ARRAY END
			vsotaRight=tempSum-vsotaLeft;   //right side
			
			if (vsotaLeft==vsotaRight){
				return true;
			}
		}
		return false;
		
	}
	int maxMirror(int[] nums) {  //vaja tier 3 dolžina mirrorja  http://codingbat.com/prob/p196409
		int vsota=0;
		for (int i=0;i<nums.length;i++){
			if (nums[i]!=nums[nums.length-i-1]){
				vsota=0;
			}else{
				vsota+=1;
			}
		}
		return vsota;
	}
	
	/*int maxMirror(int[] nums) {  //vaja tier 3 dolžina mirrorja  http://codingbat.com/prob/p196409
	int vsota=0;
	for (int i=0;i<nums.length;i++){
		for(int j=nums.length-1; nums.length-j>=0;j--){
			
			if (nums[i]!=nums[j]){
				return vsota;
			}else{
				vsota+=1;
			}
		}
		
	}
	return vsota;
	}*/

	
	
	boolean ifInnnerinOuter(int[] outer, int[] inner) { //vaja skupna stevila http://codingbat.com/prob/p193817
		if (inner.length>0){
			int position=0;
			
			for (int x=0; x<outer.length;x++){
				if(inner[0]==outer[x]){
					position=x;
					break;
				}
			}
		
			for (int j=0;j<inner.length;j++){
				if (inner[j]!=outer[position]){
					return false;
				}
				else{
					position++;
				}
			
			}
		}
	return true;
	}
	
	int factorial(int n) { //RECURSIVE http://codingbat.com/prob/p154669
		int result;
		if (n==1){
			return 1;
		}
		result= n*factorial(n-1);
		return result;
	}
	
	int bunnyEars(int bunnies) { //RECURSIVE RABBIT EARS http://codingbat.com/prob/p183649
		if (bunnies==0){
			return 0;
		}
		int ears=2;
		ears+=bunnyEars(bunnies-1);
		return ears;
	}
	
	int fibonacci(int n) {
		if (n==1||n==0){
			return 1;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}
	
	//______________________________________________________________________ RANDOM SHUFFLE
	  // Implementing Fisher–Yates shuffle
	  static void shuffleArray(int[] ar){
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	 
		public static String scramble( Random random, String inputString ){
		    // Convert your string into a simple char array:
		    char a[] = inputString.toCharArray();

		    // Scramble the letters using the standard Fisher-Yates shuffle, 
		    for( int i=0 ; i<a.length-1 ; i++ ){
		        int j = random.nextInt(a.length-1);
		        // Swap letters
		        char temp = a[i]; 
		        a[i] = a[j];
		        a[j] = temp;
		    }       

		    return new String(a);
		}
		//______________________________________________________________________
		
		public static void randomno(){
			Random randomno = new Random();
			   System.out.println("Next int value: " +randomno.nextInt(10000));
		}
}

class MessExtended extends Mess{

	public Integer dolzinaBesede(String a){
		return a.length();
	}
	//_______________________________
	public ArrayList<Integer> whileZanka1(){
		int i=0;
		ArrayList<Integer> myArray= new ArrayList<Integer>();
		while ((i=MessExtended.myScanner())!=0){
			//THIS LINE ABOVE.. DECLERATION IN WHILE
			myArray.add(i);
		}	
		return myArray;
	}
	private static Integer myScanner(){
		System.out.print("vpisi st\n");
		Scanner input = new Scanner(System.in);
		String a= input.nextLine();
		int b=0;
		if (!a.equals("")){
			b= Integer.parseInt(a);
		}
		
		return b;
	}
	//_______________________________
	int bunnyEars2Recursive(int bunnies) {//RECURSIVE DONT WORK http://codingbat.com/prob/p107330
		return lihi(bunnies);
	}
	int lihi(int bunnies){
		if (bunnies==0){
			return 0;
		}
		return lihi(bunnies-1)+sodi(bunnies);
	}
	int sodi(int bunnies){
		if (bunnies==0){
			return 0;
		}
		return sodi(bunnies)+lihi(bunnies-1);
	}
	//_________________________
	int triangleRecursive(int rows) { //RECURSIVE http://codingbat.com/prob/p194781
		if (rows==0){
			return 0;
		}
		int vsota=rows;
		vsota+=triangleRecursive(rows-1);
		return vsota;
	}
	int sumDigitsRecursive(int n) { //DONT WORK RECURSIVE http://codingbat.com/prob/p163932
		if(n%10<0){
			return n;
		}
		
		int vsota=0;
		vsota+=sumDigitsRecursive(n/10);

		return vsota;
	}
	int powerNRecursive(int base, int n) { //DONT WORK RECURSIVE http://codingbat.com/prob/p158888
		if (n==1)
			return base;
		int vsota=1;
		int vsota2=1;
		for (int i=1;i<n;i++){
			
			vsota2*= powerNRecursive(base,n-i);
		}
		return vsota*vsota2;
	}
	
	boolean groupSumAdvancedRecursive(int start, int[] nums, int target) { //ADVANCED RECURSION COPY PASTE  http://codingbat.com/prob/p145416
		  // Base case: if there are no numbers left, then there is a
		  // solution only if target is 0.
		  if (start >= nums.length) return (target == 0);
		  
		  // Key idea: nums[start] is chosen or it is not.
		  // Deal with nums[start], letting recursion
		  // deal with all the rest of the array.
		  
		  // Recursive call trying the case that nums[start] is chosen --
		  // subtract it from target in the call.
		  if (groupSumAdvancedRecursive(start + 1, nums, target - nums[start])) return true;
		  
		  // Recursive call trying the case that nums[start] is not chosen.
		  if (groupSumAdvancedRecursive(start + 1, nums, target)) return true;
		  
		  // If neither of the above worked, it's not possible.
		  return false;
	}

	boolean nearHundred(int n) {
		  return ((Math.abs(100 - n) <= 10) ||(Math.abs(200 - n) <= 10));
	}
	
	//SUBSTRING 
	//str.substring(1,n);     NE VRNE N--- JE N-1
	
	//CHAR
	/*	String niz="lol";
	System.out.printf("%c\n",niz.charAt(0));*/
	
	//replaceAll()
	//niz.replaceAll(" ","") -zamenja vse presledke z ""
	
	
	//FORMAT , Decimal...
	//System.out.printf("   %02d %9s%5.2f\n",i,"|",i*1.25); 
	String missingChar(String str, int n) { //odstranit crko na n-tem mestu SUBSTRING!  
		  String front = str.substring(0, n);   
		  String back = str.substring(n+1, str.length());
		  //str.charAt(0) vrne char na 0tem mestu
		  //str.substring(0, 3); vzame te 3 crke iz stringa
		  return front + back;
	}
	String front22(String str) {//VZAME 2 CRKI NA ZACETKU IN JIH PRLIMA NA ZACETK PA NAKONC helloo -> hehelloohe
								//POGLEJ KAKO JE S SUBSTRINGI
		  // First figure the number of chars to take
		  int take = 2;
		  if (take > str.length()) {
		    take = str.length();
		    
		   // String two = str.substring(1, 3); - vzame crko na indeksu 1 in indeksu 2 kot string.. za checking!!!!!!
		  }
		  
		  String front = str.substring(0, take);
		  return front + str + front;
		}
	int[] combineTwoArrays(int[] a, int[] b) {  //ZDRUZI DVA ARRAYA       HARD AF
		int aLen = a.length;
	    int bLen = b.length;
	    int[] c= new int[aLen+bLen];
	    System.arraycopy(a, 0, c, 0, aLen);  //KVA JE TO
	    System.arraycopy(b, 0, c, aLen, bLen);
	    return c;
	}

	int[] selectionSortDescending (int[] nums) {                 //SELECTION SORT IN DESCENDING ORDER
	     int i, j, first, temp;  
	     for ( i = nums.length - 1; i > 0; i -- ){
	          first = 0;   //initialize to subscript of first element
	          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
	          {
	               if( nums[ j ] < nums[ first ] )         
	                 first = j;
	          }
	          temp = nums[ first ];   //swap smallest found with element in position i.
	          nums[ first ] = nums[ i ];
	          nums[ i ] = temp; 
	    }           
	     
		return nums;
	}
	
	public void selectionSort(int[] arr) { //ASCENDING SELECTION SORT

		int i, j, minIndex, tmp;
	      int n = arr.length;
	      for (i = 0; i < n - 1; i++) {
	            minIndex = i;
	            for (j = i + 1; j < n; j++)
	                  if (arr[j] < arr[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = arr[i];
	                  arr[i] = arr[minIndex];
	                  arr[minIndex] = tmp;
	            }
	      }
	}
	//OBSTAJA TUD Arrays.sort(array); !!!!!!!!
}

//POLYMORPHISM ___________________
abstract class Human{
	   //ABSTRACT MEANS THAT U CANNOT MAKE ANY OBJECT NAMED HUMAN... but can use class for inheritance, polymorphism etc
	   //non abstract classes are called concrete
	
	   public abstract void goPee(); 
	   //ABSTRACT METHOD is a method that must be OVERWRITTEN!! AND IT DOESNT HAVE A BODY..
	   //ABSTRACT METHOD IS ONLY IN ABSTRACT CLASS
}
class Male extends Human{

    @Override
    public void goPee(){
        System.out.println("Stand Up");
    }
}
class Female extends Human{

    @Override
    public void goPee(){
        System.out.println("Sit Down");
    }
}
class Commander{
	public void command(Human x){
		x.goPee();
	}
}
//_______________________________
//MATICEV CLASS ISTI KT MOJ WhileZanka1
class Str{
	Scanner scanner = new Scanner(System.in);
	public static final int strlen(String s){
		return s.length();
	}
	public ArrayList<Integer> integerList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(true){
			System.out.println("vpisi st");
			int in = getInteger();
			if(in != 0) list.add(in);
			else break;
		}return list;
	}
	private boolean isInteger(String s){
		for(char c : s.toCharArray()){
			if(!Character.isDigit(c))
				return false;
		}return strlen(s) > 0 ? true : false;
	}
	private int getInteger(){
		String string = scanner.nextLine();
		return isInteger(string) ? Integer.parseInt(string) : 0;
	}
}

class MainNew {
	public static void mainnew() {
		Str str = new Str();
		for(int a : str.integerList()){
			System.out.println(a);
		}
	}
	static int random(int min, int max){
		int range = (max - min) + 1;     
		return (int) ((Math.random() * range) + min);
	}
}
//________________________________________
class DoubleSemicolon{
	public static double square(double num){
	        return Math.pow(num , 2);
	}
}



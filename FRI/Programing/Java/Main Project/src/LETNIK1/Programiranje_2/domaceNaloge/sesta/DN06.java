package LETNIK1.Programiranje_2.domaceNaloge.sesta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class DN06 {
	
	public static void main(String[] args) throws IOException{
		switch (args[0]){
		case "1":
			nacin1(args); break;
		case "2":
			izpisNacin2(nacin2(args)); break;
		case "3":
			nacin3(args); break;
		case "4":
			nacin4(args); break;
		case "5":
			nacin5(args); break;
		case "6":
			nacin6(args); break;
		case "7":
			nacin7(args); break;
		default: System.out.println("Operation not recognised."); break;
		}
	}

	public static void nacin1(String[] args){
		File f= new File(args[1]);
		if(f.exists()&&f.canRead()&&(!f.isDirectory())){
			System.out.println("Podatki o datoteki:");
			
			System.out.println("- ime: "+f.getName());
			
			System.out.println("- pot: "+f.getParent());
			
			System.out.println("- velikost: "+f.length()+" bajtov");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			System.out.println("- spremenjena: "+sdf.format(new Date(f.lastModified())));
		}
	}
	
	public static ArrayList<String> nacin2(String[] args){
		File f1= new File(args[1]);
		File f2= new File(args[2]);
		
		String name1 = f1.getName();
		String name2 = f2.getName();
		
		long velikost1 = f1.length();
		long velikost2 = f2.length();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Date datum1 = new Date(f1.lastModified());		
		Date datum2 = new Date(f2.lastModified());
		
		ArrayList<String> strings = new ArrayList<String>();
		
		if (!name1.equals(name2)) strings.add("Datoteki se razlikujeta v imenu:\n(1) "+name1+"\n(2) "+name2);
		if (velikost1!=velikost2) strings.add("Datoteki se razlikujeta v velikosti:\n(1) "+velikost1+" bajtov\n(2) "+velikost2+" bajtov");
		if (!datum1.equals(datum2)) strings.add("Datoteki se razlikujeta v datumu zadnje spremembe:\n(1) "+sdf.format(datum1)+"\n(2) "+sdf.format(datum2));
		else strings.add("Datoteki sta enaki:\n(1) "+f1.getAbsolutePath()+"\n(2) "+f2.getAbsolutePath());
		return strings;
	}
	public static void izpisNacin2(ArrayList<String> strings){
		for(String x: strings){
			System.out.println(x);
		}
	}
	
	public static void nacin3(String[] args) throws IOException{
		File f = new File(args[1]);
		Scanner sc = new Scanner(f).useDelimiter("");
	    int sum=0;
	    while (sc.hasNext()) {
	    	String character = sc.next();
	    	sum+=(int) character.charAt(0);
	    }System.out.println("Kontrolna vsota datoteke je: "+sum%1024);
	    sc.close();
	}
	
	public static void nacin4(String[] args) throws IOException{
		int lineNumber=0;
		
        String fileName1 = args[1];
        String fileName2 = args[2];

        int line1;
        int line2;
       
        FileReader fileReader1 = new FileReader(fileName1);
        FileReader fileReader2 = new FileReader(fileName2);

        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
        
        while((line1 = bufferedReader1.read()) != -1) {
        	line2=bufferedReader2.read();
        	lineNumber++;
        	if(line1!=line2){
        		System.out.println("Datoteki se razlikujeta v vsebini pri znaku: "+lineNumber);
        		bufferedReader1.close();
                bufferedReader2.close();
        		return;
        	}
        }System.out.println("Datoteki imata enako vsebino.");

        bufferedReader1.close();
        bufferedReader2.close(); 
	}
	
	public static void nacin5(String[] args){
		File f = new File(args[1]);
		System.out.println("Vsebina direktorija "+f.getAbsolutePath()+":");
		
		File[] datoteke = f.listFiles();
		ArrayList <String> fileList= izpisiDatoteke(datoteke);
		izpis(fileList);
	}
	
	private static ArrayList <String> izpisiDatoteke(File datoteke[]){
		ArrayList <String> a= new ArrayList <String>();
		for (File f: datoteke){
			a.add(f.getPath());
			if(f.isDirectory()&& f.exists()){
				a.addAll(izpisiDatoteke(f.listFiles()));
			}
		}return a;
	}
	
	public static void nacin6(String[] args){
		File file = new File(args[1]);
		System.out.println("Vsebina direktorija "+file.getAbsolutePath()+":");
		
		ArrayList<String> izpis = new ArrayList<String>();
		Stack<File> stack = new Stack<File>();									// naredimo v obliki stacka
		
		stack.push(file);
		while(!stack.isEmpty()){
			File childFile = stack.pop();
			if (childFile.isFile()||childFile.listFiles().length==0) {				 //pazi na zadnjega
				izpis.add(childFile.getPath());
			}else{
				for(File f : childFile.listFiles())
					stack.push(f);
			}
		}izpis(izpis);
	}
	
	public static void izpis(ArrayList <String> izpis){
		Collections.sort(izpis);
		for(String filename: izpis){
			System.out.println(filename);
		}
	}
	
	public static ArrayList<File> unzipped(File file){
		ArrayList<File> izpis = new ArrayList<File>();
		Stack<File> stack = new Stack<File>();
		
		stack.push(file);
		while(!stack.isEmpty()){
			File childFile = stack.pop();
			if (childFile.isFile() || childFile.listFiles().length==0){ 
				izpis.add(childFile);
			}else{
				for(File f : childFile.listFiles()){
					stack.push(f);
				}
			}
		}
		return izpis;
	}
	
	public static void nacin7(String[] args){
		File f1 = new File(args[1]);
		File f2 = new File(args[2]);
		ArrayList<File> datoteke1=new ArrayList<File>();
		for(File x:unzipped(f1)){
			datoteke1.add(x);
		}
		ArrayList<File> datoteke2=new ArrayList<File>();
		for(File x:unzipped(f2)){
			datoteke2.add(x);
		}
		
		Collections.sort(datoteke1);
		Collections.sort(datoteke2);
		
		for(File x: datoteke1){
			for(File y: datoteke2){
				
				if(x.getName().equals(y.getName())){
					String a[] = new String[3];
					a[1]=x.getPath();
					a[2]=y.getPath();
					for(String ena :nacin2(a)){ 
						if(!"Datoteki st".equals(ena.substring(0,11))){
							System.out.println(ena);
							break;
						}
					}System.out.println("OK "+x.getName());
					break;
				}
			}
		}
	}
	
}

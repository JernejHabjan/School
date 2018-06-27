package LETNIK1.vajeDoma.vajePriprave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessPomozni {
	
	public static void whileZanka(){
		int i=0;
		double b=0;
		while (i<5){
			b =  Math.pow(i,2);
			i++;
		}
		System.out.println(b);
	}
	String capitalize(final String line) {
		// COPIED 
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	public static int fib(int n) //REKURZIJA FIB
	{
	    if(n == 0 || n == 1)
	        return n;
	    return fib(n-1) + fib(n-2);
	}
	
	public void extendedhashmap(){    // SLOVAR THATS HOW YOU BASIC SLOVAR
		Map<String, List<String>> myHashMap = new HashMap<String, List<String>>();
		List<String> people = new ArrayList<String>();
		people.add("Lol");
		people.add("lolinja");
		myHashMap.put("key", people);
		System.out.print(myHashMap.get("key")+"to je DICT\n");
	}
	
}

class Person{
	String personName;
	//THIS IS HOW YOU NAME
	public Person(String name){
		personName=name;
	}
	
	public String getName(Person a){//getter
		return personName;
	}
	
	public void way1(){ //SLOVAR WAY1 IS THE WAY -POGLEJ KAKO SE DODAJA SEZNAM V SLOVAR.... tu se dela z objecti... isto
		//Map with list as the value
		Map<String, List<Person>> peopleByForename = new HashMap<String, List<Person>>(); 
		
		List<Person> people = new ArrayList<Person>();
		
		Person bobSmith = new Person("Bob Smith");
		Person bobJones = new Person("Bob Jones");

		people.add(bobJones);
		people.add(bobSmith);
		
		peopleByForename.put("Bob", people);

		// read from it
		List<Person> bobs = peopleByForename.get("Bob");
		for (int i=0;i<bobs.size();i++){
			System.out.println(bobs.get(i).personName);	
		}
	}

}



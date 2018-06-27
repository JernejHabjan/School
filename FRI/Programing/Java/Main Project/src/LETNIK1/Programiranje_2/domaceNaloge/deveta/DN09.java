package LETNIK1.Programiranje_2.domaceNaloge.deveta;

import java.util.Arrays;

public class DN09 {
	public static void main(String[] args){
		int argumenti[]= new int[args.length];
		int i=0;
		for(String s: args){
			argumenti[i]=Integer.parseInt(s);
			i++;
		}Par.najvecjaRazlika(argumenti);
		System.out.println("Najvecja razlika: ("+Par.min +", "+ Par.max+")");
	}
}

class Par{
	public static int min;
	public static int max;
    static Par najvecjaRazlika(int stevila[]){
    	Par p = new Par();
		int a[]=stevila;
		Arrays.sort(a);
		min=a[0];
		max=a[a.length-1];
		return p;
	}
}
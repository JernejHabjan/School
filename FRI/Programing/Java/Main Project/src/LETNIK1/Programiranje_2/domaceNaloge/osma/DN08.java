package LETNIK1.Programiranje_2.domaceNaloge.osma;

public class DN08 {
	public static void main(String[] args) {
		//String num = args[0];
		String num ="0823372036854775807";
		
		if(num.length()>0 && Long.parseLong(num)>=0){
			num=Long.parseLong(num)+1+"";
			if(Long.parseLong(num)<10){
				System.out.println(num);
			}else{
				if(num.length()%2==0){
					String firstHalf= num.substring(0, num.length()/2);
					String secondHalf= num.substring((num.length()/2), num.length());
					if(Long.parseLong(new StringBuilder(firstHalf).reverse().toString())<Long.parseLong(secondHalf)){ //primer "1057"
						firstHalf= Long.parseLong(num.substring(0, num.length()/2))+1+"";
					}System.out.println(firstHalf+new StringBuilder(firstHalf).reverse().toString());
				}else{ //zdj pa liha
					String firstHalf= num.substring(0, num.length()-num.length()/2);
					String secondHalf =num.substring((num.length()/2)+1, num.length());
					if(Long.parseLong(new StringBuilder(firstHalf.substring(0,firstHalf.length()-1)).reverse().toString())<Long.parseLong(secondHalf)){
						firstHalf= Long.parseLong(firstHalf)+1+"";
						System.out.println(firstHalf+new StringBuilder(firstHalf.substring(0,firstHalf.length()-1)).reverse().toString());
					}else System.out.println(new StringBuilder(firstHalf.substring(0,firstHalf.length()-1)).toString()+new StringBuilder(firstHalf).reverse().toString());
				}
			}
		}else System.out.println("Number is not a palindrome.");
	}
}

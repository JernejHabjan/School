package LETNIK1.Programiranje_2.domaceNaloge.tretja;

public class DN03 {
	public static void main(String[] args) {
		if (args.length<4){
			String operator= args[0];
			float num1 = Float.parseFloat(args[1]);
			float num2 = Float.parseFloat(args[2]);
			DN03.calculateSimple(operator, num1, num2);
		}else{
			DN03.calculateMin(DN03.parseArray(args),args);
		}
	}
	static void calculateSimple(String operator, float num1, float num2){
		switch(operator){
		
		case "1":
			System.out.println(num1+" + "+num2+" = "+(num1+num2));
			break;
		case "2":
			System.out.println(num1+" - "+num2+" = "+(num1-num2));
			break;
		case "3":
			System.out.println(num1+" * "+num2+" = "+(num1*num2));
			break;
		case "4":
			System.out.println(num1+" / "+num2+" = "+(num1/num2));
			break;
		}
	}
	
	static float[] parseArray(String nums[]){
		float[] myFloatArray = new float[nums.length-1];
		for (int i=1;i<nums.length;i++){
			myFloatArray[i-1] =Float.parseFloat(nums[i]);
		}
		return myFloatArray;
	}
	
	static void calculateMin(float nums[],String args[]){
		float min=nums[0];
		System.out.print("Minimum stevil ");
		for(float x: nums){
			if(x<min){
				min=x;
			}
		}
		for (int i=1;i<args.length;i++){
			System.out.print(args[i]+" ");
		}
		System.out.print("je "+min);
	}
}

package LETNIK2.Semester1.AIPS.Naloge.Naloga2;

import java.util.Scanner;

/**
 * Created by Jernej Habjan on 28. 11. 2016.
 */

class Global2{
    static InsertionSort is = new InsertionSort();
    static SelectionSort ss = new SelectionSort();
    static BubbleSort bs = new BubbleSort();
    static HeapSort hs = new HeapSort();
    static MergeSort ms = new MergeSort();
    static QuickSort qs = new QuickSort();
    static RadixSort rs = new RadixSort();
    static BucketSort bus = new BucketSort();

    static String arg0 = "";
    static String arg1 = "";
    static String arg2 = "";

    static ResizableArray raNUMBERS = new ResizableArray();
    static int[] count = new int[6];
    static int indexPremikov = 0;
    static int indexPrimerjav = 1;

    static void swap(int[] a, int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;

        Global2.addPremik(3);
    }

    static void addPremik(){
        count[indexPremikov] +=1;
    }
    static void addPrimerjavo(){
        count[indexPrimerjav] +=1;
    }

    static void addPremik(int stevilo){
        count[indexPremikov] +=stevilo;
    }
    static void addPrimerjavo(int stevilo){
        count[indexPrimerjav] +=stevilo;
    }

    static String printArray(int[] array){
        String izpis = "";
        for (int i = 0; i < array.length; i++){
            if(i < array.length - 1){
                izpis += array[i] + " ";
            }else{
                izpis += array[i] + " \n";
            }
        }
        return izpis;
    }

    static int getMax(int[] a){

        int max = 0;
        for(int i = 0; i< a.length; i++){
            if(a[i]> max) max = a[i];
        }
        return max;
    }

    static int getMin(int[] a){
        int min = a[0];
        for(int i = 0; i< a.length; i++){
            if(a[i]< min) min = a[i];
        }
        return min;
    }
    static String printArrayDelimiter(int[] array, int indexDelimiter){
        String izpis = "";
        for (int i = 0; i < array.length; i++){

            izpis += array[i] + " ";
            if(i == indexDelimiter){
                izpis +="| ";
            }
        }
        izpis +="\n";

        return izpis;
    }

    static int[] subArray(int[] a, int startIndex, int endIndex){
        int[] temp = new int[endIndex +1 -startIndex];
        int i = 0;
        for(int startIndex1 = startIndex; startIndex1 <= endIndex; startIndex1++){
            temp[i] = a[startIndex1];
            i++;
        }return temp;
    }

}

class ResizableArray{

    int[] array = new int[20];
    int count = 0;

    void resetArray(){
        count = 0;
        array = new int[20];
    }

    void trim(){
        array = Global2.subArray(array, 0, count - 1);
    }

    ResizableArray(){}

    ResizableArray(int elements){
        array = new int[elements];
    }

    void add(int element){
        if(array.length == count){
            resize();
        }
        array[count] = element;
        count ++;
    }

    void resize(){
        int[] temp = array;
        array = new int[array.length * 2];
        for(int i = 0; i < temp.length; i++){
            array[i] = temp[i];
        }
    }

    int[] getArray(){
        return Global2.subArray(array,0,count-1);
    }

    void setArray(int[] numbers){
        for(int i = 0; i< numbers.length; i++){
            array[i] = numbers[i];
        }
    }


}


public class Naloga2 {
    public static void main(String[] args) {

        String[] strArray;
        Scanner sc = null;
        int timesRun = 0;
        try {
            sc = new Scanner(System.in);
            while(sc.hasNextLine()) {
                Global2.raNUMBERS.resetArray();
                strArray = sc.nextLine().split(" ");

                //obdelej argumente
                if (args.length == 3) { //obicajen zagon s testi
                    Global2.arg0 = args[0];
                    Global2.arg1 = args[1];
                    Global2.arg2 = args[2];

                    run(strArray);


                } else {
                    if(timesRun %2 == 0){ //prvi zagon -> pocaka na vpis argumentov
                        if(strArray.length == 3){ //ce podam vse 3 argumente
                            Global2.arg0 = strArray[0];
                            Global2.arg1 = strArray[1];
                            Global2.arg2 = strArray[2];
                        }else if(strArray.length == 1){ //ce podam samo algoritem
                            Global2.arg0 = "trace";
                            Global2.arg1 = strArray[0];
                            Global2.arg2 = "down";
                        }else{  //privzeto
                            Global2.arg0 = "trace";
                            Global2.arg1 = "insert";
                            Global2.arg2 = "up";
                        }
                    }else{ //drugi zagon -> pocaka na vpis stevil

                        run(strArray);

                    }
                }timesRun++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    static void run(String[] strArray){

        int[] numbers = convertToInt(strArray);
        fillResizableArray(numbers); //dinamicni array
        int[]array = Global2.raNUMBERS.getArray();
        Global2.raNUMBERS.setArray(array);
        Global2.raNUMBERS.trim();


        //############# 1. call ###################
        algorithmCalls(Global2.raNUMBERS.array);

        if(Global2.arg0.equals("count")) {
            //############# 2. call ###################
            Global2.indexPremikov += 2;
            Global2.indexPrimerjav += 2;
            algorithmCalls(Global2.raNUMBERS.array); //second call


            //############# 3. call ###################
            if (Global2.arg2.equals("up")) { //negiramo smer
                Global2.arg2 = "down";
            } else {
                Global2.arg2 = "up";
            }
            Global2.indexPremikov += 2;
            Global2.indexPrimerjav += 2;
            algorithmCalls(Global2.raNUMBERS.array); //se 3. call z obrnenim redom

            System.out.print(Global2.printArray(Global2.count));
        }
    }

    static void fillResizableArray(int[] numbers){
        for(int i = 0; i < numbers.length; i++){
            Global2.raNUMBERS.add(numbers[i]);
        }
    }

    private static int[] convertToInt(String[] array){
        int[] numbers = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }return numbers;
    }

    private static void algorithmCalls(int[] a){
        int m = 0;
        switch(Global2.arg1){
            case "insert":
                Global2.is.insertionSort(a);
                break;
            case "select":
                Global2.ss.selectionSort(a);
                break;
            case "bubble":
                Global2.bs.bubbleSort(a);
                break;
            case "heap":
                Global2.hs.heapSort(a);
                break;
            case "merge":
                if(Global2.arg0.equals("trace")) System.out.print(Global2.printArray(a));
                Global2.raNUMBERS.array = Global2.ms.mergeSort(a);
                break;
            case "quick":
                Global2.qs.izpis = Global2.printArray(a);
                Global2.qs.quicksort(a, 0, a.length -1 );
                if(Global2.arg0.equals("trace")) System.out.print(Global2.qs.izpis + Global2.printArray(a));
                Global2.qs.izpis = "";
                break;
            case "radix":
                Global2.rs.radixSort(a);
                break;
            case "bucket":

                m = a.length/2; //n/2 kosev

                m = a.length; ////////////////MAX STEVILKA
                Global2.bus.bucketSort(a,m);
                break;
            default:
                System.out.println("ALGORITH NOT SUPPORTED: EXITING...");
                System.exit(1);
        }
    }
}



class BubbleSort{
    void bubbleSort(int[] a){ //todo -nasteje prevec comparov - mogoce zato ke ne skippa, ostalo cool
        String izpis = Global2.printArray(a);
        boolean swapped;




        for(int i = 1; i < a.length; i++){
            int neki= 1;
            swapped = false;
            for(int j = a.length-1; j >=i; j--){
                boolean condition = Global2.arg2.equals("up")? a[j-1]>a[j]: a[j-1]<a[j]; //COMPARE
                Global2.addPrimerjavo();
                if(condition){
                    Global2.swap(a,j-1,j);
                    swapped = true;
                    neki = j-1;

                }
            }if(!swapped){
                break;
            }

            neki++;
            if(Global2.arg0.equals("trace")) izpis += Global2.printArrayDelimiter(a, neki-1);

            neki++;
            if(Global2.arg0.equals("count")) neki++;
        }
        if(Global2.arg0.equals("trace")) {
            izpis += Global2.printArrayDelimiter(a, a.length - 2);
            System.out.print(izpis);
        }

    }


    /*


    String izpis = Global2.printArray(a);
        boolean swapped;



        int neki= 1;
        for(int i = 1; i < a.length; i++){

            swapped = false;
            for(int j = a.length-1; j >=i; j--){
                boolean condition = Global2.arg2.equals("up")? a[j-1]>a[j]: a[j-1]<a[j]; //COMPARE
                Global2.addPrimerjavo();
                if(condition){
                    Global2.swap(a,j-1,j);
                    swapped = true;
                    neki = j-1;

                }
            }if(!swapped){
                break;
            }

            neki++;
            if(Global2.arg0.equals("trace")) izpis += Global2.printArrayDelimiter(a, neki-1);

            neki++;
            if(Global2.arg0.equals("count")) neki++;
        }
        if(Global2.arg0.equals("trace")) {
            izpis += Global2.printArrayDelimiter(a, a.length - 2);
            System.out.print(izpis);
        }
     */
}

class QuickSort{
    String izpis = "";
                                                                    //TODO - ne dela trace
    static String printArrayPivot(int[] array, int indexDelimiter){
        String izpis = "";
        for (int i = 0; i < array.length; i++){
            izpis += array[i] + " ";
            if(i == indexDelimiter || i == indexDelimiter + 1){
                izpis +="| ";
            }
        }
        //if(indexDelimiter == array.length -1){
        //    izpis +=" |\n";
        //}else{
            izpis +="\n";
       // }
        return izpis;
    }


    private int partition(int[] a, int left, int right){

        boolean conditionLeft;
        boolean conditionRight;

        int p = a[left];
        int l = left;
        int r = right + 1;
        Global2.addPremik();

        while (true) {

            do{
                l++;
                Global2.addPrimerjavo();
                conditionLeft = Global2.arg2.equals("up")? a[l] < p && l <right: a[l] > p  && l <right;
            } while (conditionLeft);
            do{
                r--;
                Global2.addPrimerjavo();
                conditionRight = Global2.arg2.equals("up")? a[r] > p: a[r] < p;
            } while (conditionRight);


            if (l >= r) break;
            Global2.swap (a, l, r);

        }

        Global2.swap(a, left, r);


        return r;
    }
    void quicksort(int[] a, int left, int right){

        if (left >= right) return;
        int r = partition(a, left, right);

        int[] subarray = Global2.subArray(a,left,right);
        if(Global2.arg0.equals("trace")) izpis += printArrayPivot(subarray, r-1); //partition prov izpise sam delimiter ne

        quicksort(a, left, r - 1);
        quicksort(a, r + 1, right);
    }
}



/*
############################################################################
#################### ZA NARDIT SE ##########################################
############################################################################
 */

class HeapSort{ //todo BUILD HEAP NAROBE DELA!!!!!!! TAKO MIN KOT MAX
    private static int[] a;
    private static int n;


    private static void buildheap(int[] a){
        n = a.length - 1;
        for(int i=n/2;i>=0;i--){
            if(Global2.arg2.equals("up")) maxHeap(a,i);
            else minHeap(a,i);

        }
    }

    private static void minHeap(int[] a, int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int smallest = a[0];
        if(left <= n && a[left] < a[i]){
            smallest = left;
        }else{
            smallest =i;
        }

        if(right <= n && a[right] < a[smallest]) smallest = right;

        if(smallest !=i){
            Global2.swap(a, i ,smallest);

            minHeap(a, smallest);
        }
    }


    private static void maxHeap(int[] a, int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;
        if(left <= n && a[left] > a[i]){
            largest = left;
        }else{
            largest =i;
        }

        if(right <= n && a[right] > a[largest]) largest = right;

        if(largest !=i){
            Global2.swap(a, i ,largest);
            maxHeap(a, largest);
        }
    }

    void heapSort(int []a0) {
        String izpis = Global2.printArray(a0);

        a = a0;
        buildheap(a);

        for (int i = n; i > 0; i--) {
            Global2.swap(a, 0 ,i);
            n = n - 1;

            if(Global2.arg2.equals("up")) maxHeap(a,0);
            else minHeap(a,0);


            izpis += Global2.printArrayDelimiter(a, i -1);
        }
        if(Global2.arg0.equals("trace")) {

            System.out.print(izpis);
        }
    }
}

class BucketSort{

    void bucketSortMoj(int[] a){
        int k = a.length/2;
        int[] kosi = new int[k];

        int min = Global2.getMin(a);
        int max = Global2.getMax(a);
        int v = (max - min + 1)/ k;

    }



    void bucketSort(int[] array, int N){
        if( N <= 0 )
            return;                                   // Case of empty array

        int min = Global2.getMin(array);
        int max = Global2.getMax(array);

        int bucket[] = new int[max - min + 1];          // Create buckets

        for( int i = 0; i < N; i++ )                // "Fill" buckets
            bucket[array[i] - min]++;                   // by counting each datum

        int i = 0;
        for( int b = 0; b < bucket.length; b++ ) {   // "Empty" buckets
            for (int j = 0; j < bucket[b]; j++) {      // back into array
                Global2.addPremik();
                array[i++] = b + min;                     // by creating one per count
            }
        }
        System.out.print(Global2.printArray(array));
    }
}

/*
############################################################################
#################### DELA ##################################################
############################################################################
 */

class InsertionSort{
    void insertionSort(int[] a){
        String izpis = Global2.printArray(a);

        for (int i = 1; i < a.length; i++){
            int k = a[i];
            int j = i;
            Global2.addPremik();

            if(Global2.arg2.equals("up")){

                while (j > 0 && a[j-1] > k){
                    Global2.addPrimerjavo();
                    a[j] = a[j-1];
                    Global2.addPremik();
                    j--;
                }
                if(j>0) Global2.addPrimerjavo();

            }else{

                while (j > 0 && a[j-1] < k){
                    Global2.addPrimerjavo();

                    a[j] = a[j-1];
                    Global2.addPremik();
                    j--;
                }
                if(j>0) Global2.addPrimerjavo();

            }
            a[j]=k;
            Global2.addPremik();

            if(Global2.arg0.equals("trace")) izpis += Global2.printArrayDelimiter(a, i);
        }
        if(Global2.arg0.equals("trace"))  System.out.print(izpis);
    }
}

class SelectionSort{ //DELA
    void selectionSort(int[] a){
        int min;
        String izpis = Global2.printArray(a);


        boolean condition;

        for (int i = 0; i < a.length-1; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++) {

                condition = Global2.arg2.equals("up")? a[j] < a[min]: a[j] > a[min];

                Global2.addPrimerjavo();
                if (condition) {
                    min = j;

                }
            }

            //if (min != i) {
            Global2.swap(a, i, min);
            //}
            if(Global2.arg0.equals("trace"))
                izpis += Global2.printArrayDelimiter(a, i);
        }
        if(Global2.arg0.equals("trace"))
            System.out.print(izpis);

    }
}

class RadixSort{
    int[] countingSort(int[] input, int place){
        int[] out = new int[input.length];

        int[] count = new int[10];

        for(int i=0; i < input.length; i++){

            Global2.addPremik();
            Global2.addPrimerjavo();
            int digit = Global2.arg2.equals("up")? getDigit(input[i], place): 9-getDigit(input[i], place);
            count[digit] += 1;
        }

        for(int i=1; i < count.length; i++){
            count[i] += count[i-1];
        }


        for(int i = input.length-1; i >= 0; i--){

            Global2.addPremik();
            Global2.addPrimerjavo();
            int digit = Global2.arg2.equals("up")? getDigit(input[i], place): 9-getDigit(input[i], place);

            out[count[digit]-1] = input[i];
            count[digit]--;
        }

        return out;

    }

    private static int getDigit(int value, int digitPlace){
        return ((value/digitPlace ) % 10);
    }


    private static int getLongestString(int[] array) {
        String[] str = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            str[i] = array[i]+"";
        }
        int maxLength = 0;

        for (String s : str) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        String number = "1";
        for(int i = 0; i < maxLength; i++){
            number += "0";
        }
        return Integer.parseInt(number);
    }

    void radixSort(int[] a){
        String izpis = "";

        if(Global2.arg0.equals("trace")) izpis += Global2.printArray(a);

        int d = getLongestString(a);
        for(int place=1; place < d; place *= 10){
            // Use counting sort at each digit's place
            a = countingSort(a, place);
            if(Global2.arg0.equals("trace")) izpis += Global2.printArray(a);
        }

        if(Global2.arg0.equals("trace")) System.out.print(izpis);
    }
}

class MergeSort{
    static String printMerge(int[] a, int[] b){
        String izpis = "";
        for (int i = 0; i < a.length; i++){
            izpis += a[i] + " ";

        }
        izpis += "| ";
        for (int i = 0; i < b.length; i++){
            izpis += b[i] + " ";

        }
        izpis += "\n";
        return izpis;
    }

    int[] mergeSort(int[] a){


        if(a.length <= 1) return a;
        int sredina = (a.length  - 1)/2;
        int[] leftSubArray = Global2.subArray(a, 0, sredina);
        int[] rightSubArray = Global2.subArray(a, sredina + 1, a.length - 1);

        if(Global2.arg0.equals("trace")) System.out.print(printMerge(leftSubArray, rightSubArray));

        int[] left = mergeSort(leftSubArray);
        int[] right = mergeSort(rightSubArray);




        return merge(left,right);
    }

    static int[] merge(int[] left, int[] right){
        int[] c = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int t = 0;

        int k = left.length;
        int l = right.length;


        while(i < k && j < l){

            Global2.addPrimerjavo(); //todo PRIMERJAVA ###########################################################################
            boolean condition = Global2.arg2.equals("up")? left[i] <= right[j]: left[i] >= right[j];
            if(condition){
                c[t] = left[i];
                Global2.addPremik(2);
                i++;
            }else{
                c[t] = right[j];
                Global2.addPremik(2);
                j++;

            }t++;
        }
        while (i < k){

            c[t++] = left[i++];
            Global2.addPremik(2);
        }


        while (j < l) {

            c[t++] = right[j++];
            Global2.addPremik(2);
        }

        if(Global2.arg0.equals("trace")) System.out.print(Global2.printArray(c));


        return c;
    }
}






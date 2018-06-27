package LETNIK2.Semester1.AIPS.Izzivi.Izziv6;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Jernej Habjan on 19. 12. 2016.
 */




class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

class SequenceException extends CollectionException {
    SequenceException(String msg) {
        super(msg);
    }
}



interface Sequence<T extends Comparable> extends Collection {
    T get(int i) throws SequenceException;
    T set (int i, T x) throws SequenceException;
    void insert(int i, T x) throws SequenceException;
    T delete(int i) throws SequenceException;
    void reverse();

}


class LinkedSequence<T extends Comparable> implements Sequence{

    private static final String ERR_MSG_INDEX="Wrong index in sequence";
    private static final String ERR_MSG_EMPTY= "EMPTY LIST";

    private Node first;
    private int count;


    LinkedSequence(){
        first = null;
        count = 0;
    }


    class Node{
        T value;
        Node next = null;
        Node prev = null;

		Node(T value){
			this.value = value;
		}
    }


    public int size() {return count;}
    public boolean contains(Object o) {return false;}
    public Iterator iterator() {return null;}
    public Object[] toArray() {return new Object[0];}
    public boolean add(Object o) {return false;}
    public boolean remove(Object o) {return false;}
    public boolean addAll(Collection c) {return false;}
    public void clear() {}
    public boolean retainAll(Collection c) {return false;}
    public boolean removeAll(Collection c) {return false;}
    public boolean containsAll(Collection c) {return false;}
    public Object[] toArray(Object[] a) {return new Object[0];}

    public boolean isEmpty(){return first ==null;}
    public boolean isFull(){ return false;}
    public int count(){ return count;}

    public String toString(){
        String output = "";
        if (first != null) {
            Node current = first.next;
            while (current != null) {
                output += "[" + current.value.toString() + "]";
                current = current.next;
            }

        }
        return output;
    }


    public Comparable get(int index) throws SequenceException{
        if (index < 0) throw new  SequenceException(ERR_MSG_INDEX);
        Node current = null;
        if (first != null) {
            current = first.next;
            for (int i = 0; i < index; i++) {
                if (current.next == null)
                    return null;

                current = current.next;
            }
            return current.value;
        }
        return null;

    }

    @Override
    public Comparable set(int i, Comparable x) throws SequenceException {
        if(count == 0)throw new  SequenceException(ERR_MSG_EMPTY);
        if(count < i) throw new  SequenceException(ERR_MSG_INDEX);
        Node node = first;
        int counter = 0;
        while(node.next != null){
            if (counter == i){
                node.value = (T) x;
                break;
            }
            counter++;
            node = node.next;
        }


         return null;
    }

    public void insert(int index, Comparable data) throws SequenceException {
        if(index+2 > count || index-2 < 0) throw new  SequenceException(ERR_MSG_INDEX);

        Node temp = new Node((T) data);
        Node current = first;

        if (current != null) {
            for (int i = 0; i < index && current.next != null; i++) {
                current = current.next;
            }
        }
        temp.next = current.next;
        current.next = temp;
        count++;

    }


    public Comparable delete(int index) throws SequenceException{
        if (index < 1 || index > size())  throw new  SequenceException(ERR_MSG_INDEX);
        Node current = first;
        if (first != null) {
            for (int i = 0; i < index; i++) {
                if (current.next == null)
                    return false;
                current = current.next;
            }
            current.next = current.next.next;
            count--;
            return true;

        }
        return false;
    }

    public void reverse(){
        Node p = null;
        Node q = first;
        Node r = first.next;

        while(q!= null){
            q.prev = r;
            q.next = p;

            p = q;
            q = r;
            r = r.next;

        }first = p;

    }
}

class QuickSort{


    private static void swap(Sequence<Comparable> s, int i, int j) throws SequenceException {
        Comparable t = s.get(i);
        s.set(i, s.get(j));
        s.set(j,t);
        Global.addPremik(3);

    }


    static String printArrayPivot(Sequence <Comparable> array, int indexDelimiter) throws SequenceException {
        String izpis = "";
        for (int i = 0; i < array.size(); i++){
            izpis += array.get(i) + " ";
            if(i == indexDelimiter || i == indexDelimiter + 1){
                izpis +="| ";
            }
        }
        izpis +="\n";
        return izpis;
    }


    private static Sequence <Comparable> subArray(Sequence<Comparable> a, int startIndex, int endIndex) throws SequenceException {
        Sequence <Comparable> temp = null;
        
        int i = 0;
        for(int startIndex1 = startIndex; startIndex1 <= endIndex; startIndex1++){
            temp.add(a.get(startIndex1));

            i++;
        }return temp;
    }


    private int partition(Sequence <Comparable> a, int left, int right) throws SequenceException {

        boolean conditionLeft;
        boolean conditionRight;

        int p = (int)a.get(left);
        int l = left;
        int r = right + 1;
        Global.addPremik(1);

        while (true) {

            do{
                l++;
                Global.addPrimerjavo();

            } while (a.get(l).compareTo(p)<0 && l <right);
            do{
                r--;
                Global.addPrimerjavo();

            } while (a.get(r).compareTo(p)>0);


            if (l >= r) break;
            swap (a, l, r);

        }

        swap(a, left, r);


        return r;
    }
    void quicksort(Sequence <Comparable> a, int left, int right) throws SequenceException {

        if (left >= right) return;
        int r = partition(a, left, right);

        Sequence <Comparable> subarray = subArray(a,left,right);
        Global.izpis += printArrayPivot(subarray, r-1); //partition prov izpise sam delimiter ne

        if(subarray.size()< Global.velikost) straightInsertion(a, left, right); //PREKLOPIMO NA INSERTION SORT
        else{
            quicksort(a, left, r - 1);
            quicksort(a, r + 1, right);
        }

    }


    private static void straightInsertion(Sequence<Comparable> s, int l, int r) throws SequenceException {
        Comparable x;
        for(int i = l+1; i < r; i++){
            x = s.get(i);

            Global.addPremik(1);
            for(int j = i -1; j >=l; j--){ //do l????
                Global.addPrimerjavo();
                if(x.compareTo(s.get(j))<0){ //COMPARI SE STEJEJO SAMO ZA IFE
                    s.set(j+1, s.get(j));
                    Global.addPremik(1);
                }else{
                    s.set(j+1,x);
                    Global.addPremik(1);
                    break;
                }
            }
        }
    }
}





class Global{
    static int atr = 0;
    static int smer = 1;
    static int velikost = 1;


    static String izpis = "";
    private static int count[] = new int[6];

    static void addPremik(int n) {
        int indexPremik = 0;
        count[indexPremik]+=n;}
    static void addPrimerjavo() {
        int indexPrimerjava = 1;
        count[indexPrimerjava]+= 1;}
}

public class Izziv6 {
    public static void main(String[] args){
        QuickSort qs = new QuickSort();

        int STEVILOOSEB = 0;

        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            System.out.println("koliko oseb naj ima tabela?");

            LinkedSequence <Comparable> tt = new LinkedSequence<>();

            if(sc.hasNext()) {
                STEVILOOSEB = sc.nextInt();

                for(int i = 0; i < STEVILOOSEB; i++){ //generated osebe
                    tt.add(new Oseba());

                }
                System.out.println("Zacetni seznam: ");
                System.out.print(tt.toString()); //PRINTS ARRAY

            }

            boolean running = true;
            boolean execute = true;
            while(running){
                if(execute){



                    System.out.println("vpisi atribut (0-2) in smer(-1 ali 1) in velikost za preklop na InsertionSort (1 naprej)");

                    if(sc.hasNextInt()) {
                        Global.atr = sc.nextInt();
                    }
                    if(sc.hasNextInt()) {
                        Global.smer = sc.nextInt();
                    }
                    if(sc.hasNextInt()) {
                        Global.velikost = sc.nextInt();
                    }
                    qs.quicksort(tt, 0, tt.size());

                    System.out.print(tt.toString());


                }
                System.out.println("vpisi 'ponovi' ali karkoli drugega za exit");
                if(sc.hasNext()){
                    String str = sc.next();
                    if(!str.equals("ponovi")){
                        running = false;
                    }else{
                        execute = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sc != null) {
                sc.close();
            }
        }


    }

    static void printArray(Oseba[] a){
        for(Oseba ele: a){
            System.out.println(ele.toString());
        }
    }

    static void objectInsertionSort(Comparable[] a){
        //pri padajočem je vrednost pomnožena z -1 -je že done
        for (int i = 0; i < a.length; i++) {
            Comparable k = a[i];
            int j = i;
            while (j > 0 && a[j - 1].compareTo(k) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = k;
        }
    }


}

class Oseba implements Comparable{
    private String ime;
    private String priimek;
    private int letoR;

    Oseba(){
        letoR = (int)(Math.random() * 107 + 1910);

        String[] IMENA = {"ana", "neza", "anja", "dora"};
        int rndIme = new Random().nextInt(IMENA.length);
        ime = IMENA[rndIme];

        String[] PRIIMKI = {"kondic", "gogala", "novak", "maloku"};
        int rndPriimek = new Random().nextInt(PRIIMKI.length);
        priimek = PRIIMKI[rndPriimek];
    }

    public String toString(){
        return ime + " " + priimek + ", " + letoR;
    }

    public int compareTo(Object o){
        if(! (o instanceof Oseba)) return -1;
        Oseba os = (Oseba) o;
        switch (Global.atr){
            case 0:
                return this.ime.compareTo(os.ime) * Global.smer;
            case 1:
                return this.priimek.compareTo(os.priimek) * Global.smer;
            case 2:
                return (this.letoR - os.letoR) * Global.smer;
            default:
                System.out.println("unknown");
                return -1;
        }
    }

}









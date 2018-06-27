package LETNIK2.Semester1.AIPS.Naloge.Naloga1;

import java.util.Scanner;

class Global{
    static ArrayStack<String> generalStack;
    static ArrayStack<String> functionStack;

    static int toMove;
    static int countFunctions;

    static String STRING = "";
    static void initStacks(){

        generalStack = new ArrayStack<>();
        functionStack = new ArrayStack<>();
        CONDITION = false;

        STRING = "";
    }

    static boolean CONDITION = false;

}
//
public class Naloga1{
    public static void main(String[] args){
        String line;
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            while(sc.hasNextLine()) {

                Global.initStacks(); //resets them every line

                line = sc.nextLine();
                String[] array = line.split(" ");
                for (String data : array) {

                    //handle dodajanje FUN
                    if(Global.countFunctions < Global.toMove){
                        Global.functionStack.push(data);
                        Global.countFunctions++;
                        continue;
                    }

                    Runner.runStack(data);
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
}



interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int count();
    String toString();
}


interface Stack<T> extends Collection {
    T top();
    void push(T x);
    T pop();
}


class ArrayStack<T> implements Stack<T> {
    private static final int DEFAULT_CAPACITY = 64;
    public T[] items;
    public int count = 0;

    //FUNCTIONS
    ArrayStack(){ items = (T[]) new Object[DEFAULT_CAPACITY]; }

    public boolean isEmpty() { return count == 0; }

    public boolean isFull() { return count == DEFAULT_CAPACITY; }

    public int count() {return count;}

    private static boolean checkNumeric(String str){
        try{
            int d = Integer.parseInt(str);
        }catch(NumberFormatException nfe){

            return false;
        }
        return true;


    }

    private String toString(T array[], int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(array[i]);
            if (i < count - 1)
                sb.append(" ");
        }
        return sb.toString();
    }

    public T top(){

        if (!isEmpty())
            return items[count - 1];
        return (T) "";
    }

    private T second(){
        if (count >= 2)
            return items[count -2];
        return (T) "";
    }

    public void push(T x){
        if (!isFull()) {
            items[count] = x;
            count++;
        }
    }

    public T pop(){
        if (!isEmpty()) {
            T temp = items[count-1];
            items[count-1] = null;
            count--;
            return temp;
        }
        return (T) "";

    }

    void echo(){ System.out.println(top()); pop(); }

    void stack(){ System.out.println(toString(items, count)); } //DUNNO

    void clear(ArrayStack stack){ //CHECK

        for (int i = 0; i < DEFAULT_CAPACITY; i++){stack.items[i] = null;}
        stack.count = 0;
    }

    void space(){ push((T) " ");}

    void dup(){ push(top());}

    void dup2(){

        T top = top();
        push(second());
        push(top); }

    void swap() {
        T top = top();
        pop();
        T second = top();
        pop();
        push(top);
        push(second);
    }

    void reverse(){ //CHECK
        for (int i = 0; i < count/2; i++){
            T temp = items[i];
            items[i] = items[count-1 - i];
            items[count-1 - i] = temp;
        }
    }

    void even(){
        T top = top();
        if(checkNumeric((String)top)){
            pop();
            if(Integer.parseInt((String) top) %2 == 0){push((T) "1");}
            else{push((T) "0");}
        }

    }

    void odd(){
        T top = top();
        if(checkNumeric((String)top)){

            pop();
            if(Integer.parseInt((String) top) %2 != 0){push((T) "1");}
            else{push((T) "0");}
        }
    }

    void factorial(){
        T top = top();
        if(checkNumeric((String)top)) {
            int number = Integer.parseInt((String) top);

            pop();

            int factorial = 1;
            int i = 1;
            while (i <= number) {
                factorial *= i;
                i++;
            }
            push((T) (factorial + ""));
        }
    }

    void len(){
        T top = top();
        pop();

        push((T) (((String)top).length()+""));

    }

    void nEq(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) != Integer.parseInt((String) top) ? (T) "1" : (T) "0");
            }
        }
    }

    void lt(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) < Integer.parseInt((String) top) ? (T) "1" : (T) "0");
            }
        }
    }

    void le(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) <= Integer.parseInt((String) top) ? (T) "1" : (T) "0");
            }
        }
    }

    void eq(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) == Integer.parseInt((String) top) ? (T) "1" : (T) "0");
            }
        }
    }

    void gt(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) > Integer.parseInt((String) top) ? (T) "1" : (T) "0");

            }
        }
    }

    void ge(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push(Integer.parseInt((String) second) >= Integer.parseInt((String) top) ? (T) "1" : (T) "0");
            }
        }
    }

    void plus(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push((T) (Integer.parseInt((String) second) + Integer.parseInt((String) top) + ""));
            }
        }
    }

    void minus(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push((T) (Integer.parseInt((String) second) - Integer.parseInt((String) top) + ""));
            }
        }
    }

    void multiply(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                push((T) (Integer.parseInt((String) second) * Integer.parseInt((String) top) + ""));
            }
        }
    }

    void divide(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                if (Integer.parseInt((String) top) != 0) {
                    push((T) (Integer.parseInt((String) second) / Integer.parseInt((String) top) + ""));
                }

            }
        }
    }

    void mod(){
        T top = top();
        T second = second();
        if(checkNumeric((String)top)) {
            if (checkNumeric((String) second)) {
                pop();
                pop();
                if (Integer.parseInt((String) top) != 0) {
                    push((T) (Integer.parseInt((String) second) % Integer.parseInt((String) top) + ""));
                }

            }
        }
    }

    void dot(){
        T top = top();
        T second = second();
        pop();
        pop();
        push((T) ((String)second + (String)top));
    }

    void then(){
        T top = top();

        if(checkNumeric((String)top)) {
            pop();
            if (Integer.parseInt((String) top) != 0)
                Global.CONDITION = true;
            else
                Global.CONDITION = false;
        }
    }

    void elseOperation() {
        Global.CONDITION= !Global.CONDITION;

    }

    void fun(){
        T top = top();
        if(checkNumeric((String)top)) {

            pop();
            Global.toMove = Integer.parseInt((String) top);
            Global.countFunctions = 0;
        }
    }
    //
    void seefun(){
        System.out.println(toString((T[]) Global.functionStack.items, Global.functionStack.count));
    }

    void clearfun(){clear(Global.functionStack);}

    void run(){
        T top = top();
        if(checkNumeric((String)top)) {
            pop();
            int timesRun = Integer.parseInt((String) top);

            //System.out.println("NEW RUN_ Main stack: ");
            //stack();

            for (int i = 0; i < timesRun; i++) {
                for (int j = 0; j < Global.functionStack.count; j++) {
                    Object row[] = (Object[]) Global.functionStack.items;

                    String current = (String) row[j];
                    Runner.runStack(current);
                    //System.out.println("vzame: "+ (String) current + " condition: " + Global.CONDITION );
                    //System.out.println("Main stack: ");
                    //stack();
                }
            }
        }
    }

    public T[] concat(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;
        T[] c = (T[])new Object[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

 /*   void preorder(){
        T top = top();
        checkNumeric((String)top);
        pop();
        int stopnjaDrevesa = Integer.parseInt((String)top);

        T[] drevo = pre(stopnjaDrevesa);

        System.out.println(toString(drevo, drevo.length));



    }
*/
    void preorder(){

        pre(0,Integer.parseInt(Global.generalStack.pop()));
        String str = Global.STRING.substring(0,Global.STRING.length()-1);

        System.out.println(str);
    }

    void pre(int parent, int stopnja){
        if(stopnja < 1){System.out.println("ERROR BUILDING TREE-SMALLEST NODE NUM. IS 1"); System.exit(1);}


        Global.STRING = Global.STRING + items[parent] + " ";


        for(int i = 1; i <= stopnja; i++){

            if(stopnja*parent+i  < count)
                pre(stopnja*parent+i, stopnja);
        }

    }

    void postorder(){

        post(0,Integer.parseInt(Global.generalStack.pop()));

        String str = Global.STRING.substring(0,Global.STRING.length()-1);

        System.out.println(str);

    }
    void post(int parent, int stopnja){
        if(stopnja < 1){System.out.println("ERROR BUILDING TREE-SMALLEST NODE NUM. IS 1"); System.exit(1);}


        for(int i = 1; i <= stopnja; i++){

            if(stopnja*parent+i  < count)
                post(stopnja*parent+i, stopnja);
        }

        Global.STRING = Global.STRING + items[parent] + " ";

    }


    /*
    T[] pre(int stopnjaDrevesa){


        if(stopnjaDrevesa > items.length){
            T[] arr = (T[])new Object[1]; //initialize array za skupno rabo
            arr[0] = items[stopnjaDrevesa];
            return arr;
        }


        T[] arr = (T[])new Object[1]; //initialize array za skupno rabo
        arr[0] = items[stopnjaDrevesa];

        for(int i = 1; i <= stopnjaDrevesa; i++){
            int koren = stopnjaDrevesa * i + i;
            System.out.println("koren poddrevesa: "+koren);
            arr = concat(arr, pre(koren)); //pridruzitev vseh kidov
        }
        return null;
    }
*/

}

class Runner{
    private static boolean hasQuestionMark(String data){
        return data.length() > 0 && (data.charAt(0) == '?');
    }
    static void runStack(String data){
        if(hasQuestionMark(data)) {
            if(!Global.CONDITION){return;}
            else{data = data.substring(1,data.length());}
        }
        switch(data){

            case "echo":
                Global.generalStack.echo();
                break;
            case "stack":
                Global.generalStack.stack();
                break;
            case "clear":
                Global.generalStack.clear(Global.generalStack);
                break;
            case "space":
                Global.generalStack.space();
                break;
            case "pop":
                Global.generalStack.pop();
                break;
            case "dup":
                Global.generalStack.dup();
                break;
            case "dup2":
                Global.generalStack.dup2();
                break;
            case "swap":
                Global.generalStack.swap();
                break;
            case "reverse":
                Global.generalStack.reverse();
                break;

            case "even":
                Global.generalStack.even();
                break;
            case "odd":
                Global.generalStack.odd();
                break;
            case "!":
                Global.generalStack.factorial();
                break;
            case "len":
                Global.generalStack.len();
                break;

            case "<>":
                Global.generalStack.nEq();
                break;
            case "<":
                Global.generalStack.lt();
                break;
            case "<=":
                Global.generalStack.le();
                break;
            case "==":
                Global.generalStack.eq();
                break;
            case ">":
                Global.generalStack.gt();
                break;
            case ">=":
                Global.generalStack.ge();
                break;

            case "+":
                Global.generalStack.plus();
                break;
            case "-":
                Global.generalStack.minus();
                break;
            case "*":
                Global.generalStack.multiply();
                break;
            case "/":
                Global.generalStack.divide();
                break;
            case "%":
                Global.generalStack.mod();
                break;
            case ".":
                Global.generalStack.dot();
                break;

            case "then":
                Global.generalStack.then();
                break;
            case "else":
                Global.generalStack.elseOperation();
                break;
            case "fun":
                Global.generalStack.fun();
                break;
            case "seefun":
                Global.functionStack.seefun();
                break;
            case "clearfun":
                Global.generalStack.clearfun();
                break;
            case "run":
                Global.generalStack.run();
                break;

            case "preorder":
                Global.generalStack.preorder();
                break;
            case "postorder":
                Global.generalStack.postorder();
                break;

            default:
                Global.generalStack.push(data);
                break;
        }
    }
}



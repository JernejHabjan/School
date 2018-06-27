package LETNIK2.Semester1.AIPS.Izzivi.Izziv3;

/**
 * Created by Jernej Habjan on 21. 11. 2016.
 */
public class Izziv3 {



    public static void main(String[] args) throws CollectionException1 {

        Stack1<String> s = new ArrayDeque1<>();
        Deque<String> d = new ArrayDeque1<>();

        //TEST STACKA
        for(int i =0; i<64;i++){

            s.push("lol"+i);
        }
        System.out.println(s.top());

        s.pop();
        System.out.println("s is empty?: " + s.isEmpty());
        System.out.println(s.top());

        //TEST DEQUA
        for(int i =0; i<64;i++){

            d.enqueueFront("lol");
        }
        d.dequeue();
        d.enqueue("ll");
        d.dequeueBack();
        d.enqueue("lol1");
        System.out.println(d.back());
        System.out.println(d.front());

    }
}


class CollectionException1 extends Exception {
    public CollectionException1(String msg) {
        super(msg);
    }
}


interface Collection1 {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int count();
    String toString();
}


interface Stack1<T> extends Collection1 {
    T top() throws CollectionException1;
    void push(T x) throws CollectionException1;
    T pop() throws CollectionException1;
}


interface Deque<T> extends Collection1 {
    T front() throws CollectionException1;
    T back() throws CollectionException1;
    void enqueue(T x) throws CollectionException1;
    void enqueueFront(T x) throws CollectionException1;
    T dequeue() throws CollectionException1;
    T dequeueBack() throws CollectionException1;
}

class ArrayDeque1<T> implements Deque<T>, Stack1<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] items;
    private int front = 0;
    private int back = 0;
    private int count =0;

    public ArrayDeque1() throws CollectionException1{
        items =(T[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty(){return count==0;}
    public boolean isFull(){return count == DEFAULT_CAPACITY;}
    public int count(){return count;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++){
            int index = ((((front += 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);
            sb.append(items[index]);
            if(i < count-1)
                sb.append(" ");
        }return sb.toString();
    }

    //stack############

    public T top() throws CollectionException1{
        if(isEmpty()){
            throw new CollectionException1(ERR_MSG_EMPTY);
        }
        int index = ((((back -= 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);
        return items[index];
    }

    public void push(T x) throws CollectionException1{
        if(isFull()){
            throw new CollectionException1(ERR_MSG_FULL);
        }
        items[back] = x;
        count ++;
        back = ((((back += 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);
    }

    public T pop() throws CollectionException1{
        if(isEmpty()){
            throw new CollectionException1(ERR_MSG_EMPTY);
        }

        int index = ((((back -= 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);
        System.out.println(index+"index");
        T temp = items[index];
        items[index] = null;
        back = index;
        count--;
        return temp;
    }

    //DEQUE################

    public T front() throws CollectionException1{
        if(isEmpty()){
            throw new CollectionException1(ERR_MSG_EMPTY);
        }return items[front];
    }

    public T back() throws CollectionException1{
        if(isEmpty()){
            throw new CollectionException1(ERR_MSG_EMPTY);
        }return top();
    }

    public void enqueue(T x) throws CollectionException1{
        push(x);
    }

    public void enqueueFront(T x) throws CollectionException1{
        if(isFull()){
            throw new CollectionException1(ERR_MSG_FULL);
        }
        count ++;
        front = ((((front -= 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);

        items[front] = x;
    }

    public T dequeue() throws CollectionException1{
        if(isEmpty()){
            throw new CollectionException1(ERR_MSG_EMPTY);
        }
        T temp = items[front];
        items[front] = null;
        front = ((((front += 1) % DEFAULT_CAPACITY) + DEFAULT_CAPACITY) % DEFAULT_CAPACITY);
        count--;

        return temp;
    }

    public T dequeueBack() throws CollectionException1{
        return pop();
    }
}
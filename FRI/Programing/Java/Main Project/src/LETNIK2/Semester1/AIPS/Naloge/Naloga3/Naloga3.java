//Created by Jernej Habjan on 22. 12. 2016.
import java.util.Scanner;

public class Naloga3 {
    public static void main(String[] args){
        new IOController(args);
    }
}

class IOController{
    DataType dt;
    String vrstaGrafa = "undirected";
    String akcija = "info";
    String parameterAkcije = "";

    int stVozlisc = 0;

    IOController(String[] args){
        getArguments(args);
        getInput(args);
    }

    void getArguments(String[] args) {
        if(args.length != 0){
            vrstaGrafa = args[0];
            akcija = args[1];
            if(args.length==3)parameterAkcije=args[2];
        }
    }

    void getInput(String[] args){
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);

            boolean noNumberRecieved = true;
            if(args.length == 0){  //handle my input
                if(sc.hasNextLine()){
                    String[] argumenti;
                    argumenti = sc.nextLine().split(" ");

                    switch (argumenti.length){
                        case 1:
                            if(argumenti[0].matches("^-?\\d+$")){ //pol ni user nc unesu stringa

                                stVozlisc = Integer.parseInt(argumenti[0]);
                                noNumberRecieved = false; //dobu sm cifro v argumentih
                            }else{
                                akcija = argumenti[0];
                            }


                            break;
                        case 2:
                            vrstaGrafa = argumenti[0];
                            akcija = argumenti[1];
                            break;
                        case 3:
                            vrstaGrafa = argumenti[0];
                            akcija = argumenti[1];
                            parameterAkcije = argumenti[2];
                            break;
                    }
                }
            }

            if(noNumberRecieved){

                if(sc.hasNextInt()) stVozlisc = sc.nextInt(); //get steviloVozlisc

            }

            ResizableArray ogliscaPovezave = new ResizableArray(); // input parov vozlisc

            while(sc.hasNextInt()) {
                int vozlisce = sc.nextInt();

                ogliscaPovezave.add(vozlisce);
            }


            ogliscaPovezave.trim();

            //System.out.println("ARGS");
            //System.out.println(vrstaGrafa+ " " +akcija+" "+ parameterAkcije);

            //System.out.println("DATA");
            //System.out.print(Tools.printArray(ogliscaPovezave.array));

            dt = new DataType(vrstaGrafa, ogliscaPovezave.array);

            run(vrstaGrafa, akcija, parameterAkcije, stVozlisc, ogliscaPovezave.array, dt);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    void run(String vrstaGrafa, String akcija, String parameterAkcije, int stVozlisc, int[] povezaveOglisc, DataType dt){
        Algorithms a = new Algorithms();
        switch(akcija){
            case "info":
                a.info.info(vrstaGrafa, stVozlisc, povezaveOglisc, dt);
                break;
            case "walks":
                a.walks.walks(vrstaGrafa, parameterAkcije, stVozlisc, povezaveOglisc);
                break;
            case "dfs":
                a.dfs.dfs(vrstaGrafa, stVozlisc, povezaveOglisc);
                break;
            case "bfs":
                a.bfs.bfs(vrstaGrafa, stVozlisc, povezaveOglisc);
                break;
            case "sp":
                a.sp.sp(vrstaGrafa, parameterAkcije, stVozlisc, povezaveOglisc);
                break;
            case "comp":
                a.comp.comp(vrstaGrafa, stVozlisc, povezaveOglisc);
                break;
        }
    }

}

interface Info{void info(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc, DataType dt);}
interface Walks{void walks(String vrstaGrafa, String parameterAkcije, int stVozlisc, int[] povezaveOglisc);}
interface Dfs{void dfs(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc);}
interface Bfs{void bfs(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc);}
interface Sp{void sp(String vrstaGrafa, String parameterAkcije, int stVozlisc, int[] povezaveOglisc);}
interface Comp{void comp(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc);}


class Algorithms{ //-todo -BEWARE - ce time++ premaknemo na konc funkcije dobimo izstopni vr. red

    Info info = new Info(){
        public void info(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc, DataType dt){

            System.out.println(stVozlisc + " " + povezaveOglisc.length/2 + " "); //-todo in stevilo manjkajocih povezav do klike
            dt.printMatrix();

        }

        void neki(){
            System.out.println("neki");
        }
    };

    Walks walks = new Walks() {
        public void walks(String vrstaGrafa, String parameterAkcije, int stVozlisc, int[] povezaveOglisc) {

        }
    };

    Dfs dfs = new Dfs() {

        int[] mark; //not sure

        public void dfs(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc) {


        }

        void dfsFull(){
            /*dfsInit();
            for(Vozlisce v: vozlisca){
                if(mark[v] == 0) dfsRun(v);
                else System.out.println("cikelj");
            }*/
        }

        void dfsInit(){
            /*
            for(Vozlisce v: vozlisca){
                mark[v] = 0;
                v.time = 0;

            }
            */
        }

        void dfsRun(Vozlisce v){
            /*time++;
            mark[v] = time;
            for(Vozlisce u: v.neigbours()){
                if(mark[u] == 0)
                    dfsRun(u);
            }*/
        }

        boolean reachable(Vozlisce v, Vozlisce u){
            /*dfsInit();
            dfsRun(u);
            return mark[v] > 0;*/
            return true;
        }
    };

    Bfs bfs = new Bfs() {
        public void bfs(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc) {

        }

        void bfsFull(){
            /*bfsInit();
            for(Vozlisce v: vozlisca){
                if(mark[v] == 0) bfsRun(v);
            }*/
        }

        void bfsInit(){
            /*
            for(Vozlisce v: vozlisca){
                mark[v] = 0;
                v.time = 0;
            }
            */
        }

        void bfsRun(Vozlisce v){ //PAZI - na predavanjue je napisov drgac
            /*q = queue();
            time++;
            mark[v] = time;
            q.enqueue(v);
            while(!q.empty()){
                v = q.dequeue();
                for(Vozlisce u: v.neighbours()){
                    if(mark[u] == 0){
                        time++;
                        mark[v] = time;
                        q.enqueue(u);
                    }
                }
            }*/
        }
    };

    Sp sp = new Sp() {
        public void sp(String vrstaGrafa, String parameterAkcije, int stVozlisc, int[] povezaveOglisc) {

        }
    };

    Comp comp = new Comp() {
        public void comp(String vrstaGrafa, int stVozlisc, int[] povezaveOglisc) {

        }
    };

}

class Vozlisce{
    int[] nekSeznam;
    Vozlisce[] seznamSosedov; //not sure

    Vozlisce[] neighbours(){
        return seznamSosedov;
    }

}

class ResizableArray{

    int[] array = new int[20];
    int count = 0;

    void trim(){
        array = Tools.subArray(array, 0, count - 1);
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
        return Tools.subArray(array,0,count-1);
    }

}

class Tools{
    static String printArray(int[] a){
        String izpis = "";
        for (int i = 0; i < a.length; i++){
            if(i < a.length - 1){
                izpis += a[i] + " ";
            }else{
                izpis += a[i] + " \n";
            }
        }
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


class DataType{
    int[][] matrix;



    DataType(String vrstaPovezave, int[] ogliscaPovezave){
        convertToMatrix(vrstaPovezave, ogliscaPovezave);
        convertToNeigbours();
    }
    void convertToMatrix(String vrstaPovezave, int[] ogliscaPovezave){

        matrix = new int[ogliscaPovezave.length][ogliscaPovezave.length];


        //init matrix
        for(int i = 0; i < ogliscaPovezave.length; i++)
            for(int j = 0; j < ogliscaPovezave.length; j++)
                matrix[j][i] = 0;

        //fill matrix
        if(vrstaPovezave.equals("undirected")){ //nevem če nism lih zamenu
            for(int i = 0; i < ogliscaPovezave.length; i+=2){//po 2 preskakuje
                matrix[ogliscaPovezave[i]][ogliscaPovezave[i+1]] +=1;
            }
        }else{
            for(int i = 0; i < ogliscaPovezave.length; i+=2){//po 2 preskakuje
                matrix[ogliscaPovezave[i]][ogliscaPovezave[i+1]] +=1;
            }
        }

    }

    void convertToNeigbours(){

    }


    void printMatrix(){

        for(int i = 0; i <  matrix[0].length; i++)
            for(int j = 0; j <  matrix[0].length; j++)
                System.out.println(matrix[i] + " " + matrix[j]);
    }

}
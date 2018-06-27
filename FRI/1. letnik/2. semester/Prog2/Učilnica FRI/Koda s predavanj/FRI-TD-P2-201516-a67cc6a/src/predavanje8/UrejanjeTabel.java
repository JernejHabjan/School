package predavanje8;

import java.util.Random;

/**
 *
 * @author tomaz
 */
public class UrejanjeTabel {
  
  static int N = 10;
  
  static void bubbleSort(int t[]) {
    for (int i = 0; i < t.length; i++) {
      for (int j = 0; j < t.length-1; j++) {
        if (t[i] < t[j]) {
          t[i] = t[i] + t[j];
          t[j] = t[i] - t[j];
          t[i] = t[i] - t[j];
        }
      }
    }
  }
  
  // manjka se funkcija za zlivanje dveh urejenih 
  // tabel -- izziv za domaco nalogo
  static void merge(int t[], int z, int k, int s) {
    
  }
  
  static void mergeSort(int t[], int z, int k) {
    if (k - z + 1 <= 1) return;
    
    int s = (z + k) / 2;
    mergeSort(t, z, s);
    mergeSort(t, s+1, k);
    
    merge(t, z, k , s);
  }
  
  public static void main(String[] args) {
    int tabela[] = new int[N];
    Random rnd = new Random();
    for (int i = 0; i < N; i++) {
      tabela[i] = rnd.nextInt(10);
    }
    
    // Arrays.sort(tabela);
    //bubbleSort(tabela);
    
    mergeSort(tabela, 0, tabela.length-1);
    
    for (int i = 0; i < N; i++) {
      System.out.print(tabela[i] + " ");
    }
    System.out.println("");
    
  }

}

package predavanje9;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author tomaz
 */
public class UrejanjeTabel {
  
  
  //********************* Urejanje z mehurcki *********************//
  
  // Najpreprostejsa (in zato tudi najpocasnejsa) metoda za urejanje
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

  
  //********************* Urejanje z zlivanjem *********************//  
  /**
   * Metoda zlije dve poddabeli: t(z ... s) in t(s+1 ... k). Rezultat
   * zlivanja je v isti tabeli (na mestih od z ... k so zapisana stevila, 
   * ki so bila pred klicem metode zapisana v obeh podtabelah)
   */
  static void merge(int t[], int z, int k, int s) {
    int novaT[] = new int[k-z+1];
    int n = 0; // koliko elementov je ze v tabeli novaT
    
    int i=z, j=s+1;
    while (i<=s && j<=k) {
      if (t[i] < t[j]) {
        novaT[n++] = t[i++];
      } else {
        novaT[n++] = t[j++];
      }        
    }
    while (i <= s)
      novaT[n++] = t[i++];
    
    for (int l = 0; l < n; l++) {
      t[l+z] = novaT[l];
    }

  }
  
  /**
   * Urejanje tabele t od vkljucno indeksa z do vkljucno indeksa k
   */
  static void mergeSort(int t[], int z, int k) {
    // ustavitveni pogoj: tabela velikosti 1 (ali manj) je ze 
    // urejena, zato z urejanjem koncamo
    if (k - z + 1 <= 1) return;
    
    // poiscemo sredino tabele ...
    int s = (z + k) / 2;
    // ... in uredim podtabelo od z do s (vkljucno) ...
    mergeSort(t, z, s);
    // ... in podtabelo od s+1 do k (vkljucno)
    mergeSort(t, s+1, k);
    
    // urejeno podtabeli le se zlijem v urejeno tabelo
    merge(t, z, k , s);
  }

  //********************* Pomozne metode ********************//
  static int [] ustvariStevila(int n) {
    int tabela[] = new int[n];
    Random rnd = new Random();
    for (int i = 0; i < n; i++) {
      tabela[i] = rnd.nextInt(10);
    }
    return tabela;
  }
  static void izpisi(int [] tabela) {
    for (int i = 0; i < tabela.length; i++) {
      if (i%100 == 0) System.out.println("");
      System.out.print(tabela[i] + " ");      
    }
    System.out.println("");
  }
  
  //********************* Glavni program ********************//
  public static void main(String[] args) {
    int tabela[];

    int n=400000;
            
    tabela = ustvariStevila(n);
    long startB = System.currentTimeMillis();
    bubbleSort(tabela);
    long endB   = System.currentTimeMillis();
    System.out.printf("Cas (Bubblesort): %d\n", endB - startB);
    // izpisi(tabela);

    
    tabela = ustvariStevila(n);
    long startM = System.currentTimeMillis();
      mergeSort(tabela, 0, tabela.length-1);
    long endM   = System.currentTimeMillis();
    System.out.printf("Cas (Mergesort): %d\n", endM - startM);

    //izpisi(tabela);
    
    
  }

}

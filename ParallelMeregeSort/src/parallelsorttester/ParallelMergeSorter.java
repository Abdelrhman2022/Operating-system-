package parallelsorttester;


import java.util.*;



    public class ParallelMergeSorter extends Thread {
   
    public static <E> void sort(E[] a, Comparator<? super E> comp, int threads) {
        parallelMergeSort(a, 0, a.length-1, comp, threads);
    }

  
    private static <E> void mergeSort(E[] a, int from, int to,
            Comparator<? super E> comp) {
        if (from == to) {
            return;
        }
        if (to - from >0) {
            int mid = (from + to) / 2;
            
            // Sort the first and the second half
            mergeSort(a, from, mid, comp);
            mergeSort(a, mid + 1, to, comp);
            merge(a, from, mid, to, comp);
        }
    }
   
    private static <E> void parallelMergeSort(E[] a, int from, int to, Comparator<? super E> comp, int availableThreads){
        if (to - from > 0){
            if (availableThreads <=1) {
                mergeSort(a, from, to, comp);
            }
            else {
                int middle = to/2;
            
                Thread firstHalf = new Thread(){
                    public void run(){
                        parallelMergeSort(a, from, middle, comp, availableThreads - 1);
                    }
                };
                Thread secondHalf = new Thread(){
                    public void run(){
                        parallelMergeSort(a, middle + 1, to, comp, availableThreads - 1);
                    }
                };
            
                firstHalf.start();
                secondHalf.start();
			
                try {
                    firstHalf.join();
                    secondHalf.join();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            
                merge(a, from, middle, to, comp);
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    private static <E> void merge(E[] a,
            int from, int mid, int to, Comparator<? super E> comp) {
        int n = to - from + 1;
        

        // Merge both halves into a temporary array b
        Object[] b = new Object[n];

        int i1 = from;
        // Next element to consider in the first range
        int i2 = mid + 1;
        // Next element to consider in the second range
        int j = 0;
         // Next open position in b

        // As long as neither i1 nor i2 past the end, move
        // the smaller element into b
        while (i1 <= mid && i2 <= to) {
            if (comp.compare(a[i1], a[i2]) < 0) {
                b[j] = a[i1];
                i1++;
            } else {
                b[j] = a[i2];
                i2++;
            }
            j++;
        }

        
        while (i1 <= mid) {
            b[j] = a[i1];
            i1++;
            j++;
        }

        // Copy any remaining entries of the second half
        while (i2 <= to) {
            b[j] = a[i2];
            i2++;
            j++;
        }

        // Copy back from the temporary array
        for (j = 0; j < n; j++) {
            a[from + j] = (E) b[j];
        }
    }
}


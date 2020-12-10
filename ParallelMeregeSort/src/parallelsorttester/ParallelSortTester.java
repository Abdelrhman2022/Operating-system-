package parallelsorttester;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class ParallelSortTester {

    public static void main(String[] args) {
        runSortTester();
    }

    
    public static void runSortTester() {
        int SIZE = 1000,   // initial length of array to sort
            ROUNDS = 15,
            availableThreads = 4; // assume the available Threads will be 4 but can change thih value 

        Integer[] a;

        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer d1, Integer d2) {
                return d1.compareTo(d2);
            }
        };

        System.out.printf("\nMax number of threads == %d\n\n", availableThreads);
        for (int i = 1; i <= availableThreads; i*=2) {
            if (i == 1) {
                System.out.printf("%d Thread:\n", i);
            }
            else {
                System.out.printf("%d Threads:\n", i);
            }
            for (int j = 0, k = SIZE; j < ROUNDS; ++j, k*=2) {
                a = createRandomArray(k);
                // run the algorithm and time how long it takes to sort the elements
                long startTime = System.currentTimeMillis();
                ParallelMergeSorter.sort(a, comp, availableThreads);
                long endTime = System.currentTimeMillis();

                if (!isSorted(a, comp)) {
                    throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
                }

                System.out.printf("%10d elements\n", k);
            }
            System.out.print("\n*******************************************\n");
        }
    }

    
    public static <E> boolean isSorted(E[] a, Comparator<? super E> comp) {
        for (int i = 0; i < a.length - 1; i++) {
            if (comp.compare(a[i], a[i + 1]) > 0) {
                System.out.println(a[i] + " > " + a[i + 1]);
                return false;
            }
        }
        return true;
    }

    // Randomly rearranges the elements of the given array.
    public static <E> void shuffle(E[] a) {
        for (int i = 0; i < a.length; i++) {
            // move element i to a random index in [i .. length-1]
            int randomIndex = (int) (Math.random() * a.length - i);
            swap(a, i, i + randomIndex);
        }
    }

    // Swaps the values at the two given indexes in the given array.
    public static final <E> void swap(E[] a, int i, int j) {
        if (i != j) {
            E temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    // Creates an array of the given length, fills it with random
    // non-negative integers, and returns it.
    public static Integer[] createRandomArray(int length) {
        Integer[] a = new Integer[length];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(1000000);
        }
        return a;
    }
}
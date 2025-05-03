import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {



    /**
     * Performance test for Fixed Stack-Dynamic category.
     * Implementation: Local array declaration with fixed size known at compile time (int[] arr = new int[CONSTANT];)
     * but allocation happens at runtime when the method/declaration is reached.
     * Uses a local int[] for the benchmark.
     * Result: This took around 10 milliseconds
     * SS: https://paste.pics/T8BRM
     */
    public static void checkPerformanceForFixedStackDynamic() {
        int size = 1000000; // 1M
        System.out.println("\n--- Performance Test: Fixed Stack-Dynamic ---");
        System.out.println("Category Implementation: Local int[" + size + "] (fixed size declared locally). Size: " + size);
        long startTime, endTime, duration;
        long sum;

        startTime = System.nanoTime();
        int[] array = new int[size]; // Fixed size at compile time inside the local method
        sum = 0L;
        for (int i = 0; i < size; i++) {
            array[i] = i;
            sum += array[i];
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("  Result: Sum = " + sum + ", Time = " + duration + " ms");
        System.gc(); // Suggest GC
    }

    /**
     * Performance test for Stack-Dynamic category.
     * Implementation: Local array declaration where user input determines the size at runtime
     * Deallocated after execution
     * Result: it's around takes 6 milliseconds
     * SS : https://paste.pics/T8BRP
     */
    public static void checkPerformanceForStackDynamic(int size) {
        System.out.println("\n--- Performance Test: Stack-Dynamic ---");
        System.out.println("Category Implementation: Local int[variableSize] (size determined at runtime via user input). Size: " + size);
        long startTime, endTime, duration;
        long sum;

        startTime = System.nanoTime();
        // Size 'size' is dynamic from the caller's perspective
        int[] array = new int[size];
        sum = 0L;
        for (int i = 0; i < size; i++) {
            array[i] = i;
            sum += array[i];
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("  Result: Sum = " + sum + ", Time = " + duration + " ms");
        System.gc(); // Suggest GC
    }



    /**
     * Performance test for Heap-Dynamic category.
     * Implementation: Java ArrayList, which grows dynamically on the heap based on the number of elements it contains.
     * Testing population using add() which may trigger resizing.
     * Result without an initial capacity set: it took around 60 milliseconds
     * Result with an initial capacity set: it took around 27 milliseconds
     * SS : https://paste.pics/T8BRT
     */
    public static void checkPerformanceForHeapDynamic(int size) {
        System.out.println("\n--- Performance Test: Heap-Dynamic ---");
        System.out.println("Category Implementation: ArrayList<Integer> using add(). Size: " + size);
        long startTime, endTime, duration;
        long sum;

        // Test 1: Without initial capacity (emphasizes resizing)
        System.out.println("  Variant: No initial capacity");
        startTime = System.nanoTime();
        ArrayList<Integer> arrayList = new ArrayList<>();
        sum = 0L;
        for (int i = 0; i < size; i++) {
            arrayList.add(i); // Potential resizing, boxing
        }
        // Separate summation after population
        for(Integer val : arrayList) { sum += val; }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("    Result: Sum = " + sum + ", Time = " + duration + " ms");
        arrayList = null; // Help GC

        // Test 2: With initial capacity (minimizes resizing)
        System.out.println("  Variant: With initial capacity");
        startTime = System.nanoTime();
        ArrayList<Integer> arrayListWithCap = new ArrayList<>(size);
        sum = 0L;
        for (int i = 0; i < size; i++) {
            arrayListWithCap.add(i); // Still adding, less resizing
        }
        // Separate summation
        for(Integer val : arrayListWithCap) { sum += val; }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("    Result: Sum = " + sum + ", Time = " + duration + " ms");
        arrayListWithCap = null; // Help GC

        System.gc(); // Suggest GC
    }

    public static void main(String[] args) {

        /**
        * Here we are implementing the staic type array with 1M data
        * to manipulate and travers, we will measure that how much time
        * it will take for the static type array
        * Result: after running it takes 6 milliseconds
        * SS: https://paste.pics/T8BRD
        * */

        /**
        Static Category: Storage allocated when the class is loaded
        (before main runs, effectively). Size must be known at compile
        time (or class loading time).
        */

        System.out.println("===========================================================");
       System.out.println("===== Starting Performance Comparisons for Static =====");
        int size = 1000000; // 1M

        System.out.println("\n--- Performance Test: Static (Operational Speed) ---");
        System.out.println("Category Implementation: Simulating operations on pre-allocated fixed storage (using local int[]). Size: " + size);
        long startTime, endTime, duration;
        long sum;

        startTime = System.nanoTime();
        int[] staticArray = new int[size]; // Size 1000000 is fixed before compile time
        sum = 0L;
        for (int i = 0; i < size; i++) {
            staticArray[i] = i; //traverse and insert
            sum += staticArray[i]; //manipulate
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("  Result: Sum = " + sum + ", Time = " + duration + " ms");
        System.gc(); // Suggest GC
        System.out.println("===== Ending Performance Comparisons for Static ========");
        System.out.println("===========================================================");



        System.out.println("===========================================================");

        int testSize1 = 1000000; // 1M

        System.out.println("\n*** Running tests, size: " + testSize1 + " ***");
        checkPerformanceForFixedStackDynamic();

       System.out.print("Enter size for the stack-dynamic array: 1000000 - " );
        Scanner input = new Scanner(System.in);

        int stackDynamicArraySize = input.nextInt();
        checkPerformanceForStackDynamic(stackDynamicArraySize); // set the size during run time
        System.out.println("===========================================================");


        System.out.println("===========================================================");

        /**
         * Performance test for Fixed Heap-Dynamic category.
         * Implementation: Standard Java array allocated explicitly on the heap with 'new' keyword in the run time
         * the size will be defined via user input. and Size is fixed after allocation.
         * Result: it took similar 6 milliseconds
         * SS : https://prnt.sc/c7BltIImtTMR
         */

        System.out.print("Enter size for the heap-dynamic array: 1000000 -");
        int heapDynamicArraySize = input.nextInt();
        System.out.println("\n--- Performance Test: Fixed Heap-Dynamic ---");
        System.out.println("Category Implementation: Standard 'new int[" + heapDynamicArraySize + "]' on heap. Size: " + heapDynamicArraySize);
        long startTimes, endTimes, durations;
        long sums;

        startTimes = System.nanoTime();
        int[] array = new int[heapDynamicArraySize]; // Explicit heap allocation request
        sums = 0L;
        for (int i = 0; i < heapDynamicArraySize; i++) {
            array[i] = i;
            sums += array[i];
        }
        endTimes = System.nanoTime();
        durations = TimeUnit.NANOSECONDS.toMillis(endTimes - startTimes);
        System.out.println("  Result: Sum = " + sums + ", Time = " + durations + " ms");
        System.gc(); // Suggest GC
        System.out.println("===========================================================");

        System.out.println("===========================================================");

        //test for Heap Dynamic
        checkPerformanceForHeapDynamic(testSize1); // Contains two variants inside

        System.out.println("===== Performance Comparisons Complete =====");


        System.out.println("===========================================================");

    }
}
package com.spartaglobal.sortproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSorterTest {
    @Test
    public void testQuickSort(){
        // test generic bubblesort
        int size = 100;
        boolean displayOutput = true;
        TestUtils<Integer> testUtils = new TestUtils<>();

        BubbleSorter<Integer> integerSorter = new BubbleSorter<>();
        Integer[] input = ListGenerator.generateRandomIntegerList(size);

        // displaying generated list
        if(displayOutput){
            System.out.println("Input: ");
            testUtils.outputArray(input);
        }

        // test run + timing
        long start = System.nanoTime();
        QuickSorter<Integer> quickSorter = new QuickSorter<>();
        Integer[] actual = quickSorter.sort(input);
        long end = System.nanoTime();

        // displaying list + time taken
        if(displayOutput){
            System.out.println("\nDuration: " + (end-start)+ "ns") ;
            System.out.println("Output: ");
            testUtils.outputArray(actual);
        }

        // validity check
        assertEquals(true, testUtils.isSorted(actual));
    }
}

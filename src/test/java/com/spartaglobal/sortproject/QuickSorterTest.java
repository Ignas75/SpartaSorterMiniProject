package com.spartaglobal.sortproject;

import com.spartaglobal.sortproject.sorters.BubbleSorter;
import com.spartaglobal.sortproject.sorters.QuickSorter;
import com.spartaglobal.sortproject.utilities.ArrayUtils;
import com.spartaglobal.sortproject.utilities.ListGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSorterTest {
    @Test
    public void testQuickSort(){
        // test generic bubblesort
        int size = 100;
        boolean displayOutput = true;
        ArrayUtils<Integer> testUtils = new ArrayUtils<>();

        BubbleSorter<Integer> integerSorter = new BubbleSorter<>();
        List<Integer> input = ListGenerator.generateRandomIntegerList(size);

        // displaying generated list
        if(displayOutput){
            System.out.println("Input: ");
            System.out.println(input);
        }

        // test run + timing
        long start = System.nanoTime();
        QuickSorter<Integer> quickSorter = new QuickSorter<>();
        List<Integer> actual = quickSorter.sort(input);
        long end = System.nanoTime();

        // displaying list + time taken
        if(displayOutput){
            System.out.println("\nDuration: " + (end-start)+ "ns") ;
            System.out.println("Output: ");
            System.out.println(actual);
        }

        // validity check
        assertEquals(true, testUtils.isSorted(actual));
    }
}

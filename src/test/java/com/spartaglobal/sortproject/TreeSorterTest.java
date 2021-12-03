package com.spartaglobal.sortproject;

import com.spartaglobal.sortproject.sorters.TreeSorter;
import com.spartaglobal.sortproject.utilities.ArrayUtils;
import com.spartaglobal.sortproject.utilities.ListGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeSorterTest {

    @Test
    public void testTreeSorter(){
        // test generic bubblesort
        int size = 100;
        boolean displayOutput = true;
        ArrayUtils<Integer> testUtils = new ArrayUtils<>();

        List<Integer> input = ListGenerator.generateRandomIntegerList(size);

        // displaying generated list
        if(displayOutput){
            System.out.println("Input: ");
            System.out.println(input);
        }

        // test run + timing
        long start = System.nanoTime();
        TreeSorter<Integer> treeSorter = new TreeSorter<>();
        List<Integer> actual = treeSorter.sort(input);
        long end = System.nanoTime();

        // displaying list + time taken
        if(displayOutput){
            System.out.println("\nDuration: " + (end-start)+ "ns") ;
            System.out.println("Output: ");
            System.out.println(actual);
        }

        // validity check
        assertEquals(true, testUtils.isSorted(actual));

        /*
         TODO: add test for array implementation
         TODO: reduce code repetition by having a setup method?
         TODO: add tests for strings
        */
    }
}

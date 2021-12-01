package com.spartaglobal.sortproject;

import com.spartaglobal.sortproject.sorters.BubbleSorter;
import com.spartaglobal.sortproject.utilities.ArrayUtils;
import com.spartaglobal.sortproject.utilities.ListGenerator;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSorterTest {
    public static boolean compareArrays(int[] arr1, int arr2[]) {
        if(arr1.length != arr2.length){
            return false;
        }
        for(int i = 0; i<arr1.length; i++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean isIntListAscending(int[] arr){
        if(arr.length <= 1){
            return true;
        }
        for(int i = 0; i< arr.length-1; i++){
            if(arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }

    @Test
    public void testValidIntSort(){
        int[] input = {5,6,3,7,9,10,8,2,1,4};
        int[] actual = BubbleSorter.bubbleSort(input);
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        assertEquals(true, compareArrays(actual, expected));
    }

    @Test
    public void testRandomList(){
        // test parameters
        boolean displayOutput = true;
        int size = 100;

        int[] input = ListGenerator.generateRandomIntArray(size);
        // displaying generated list
        if(displayOutput){
            System.out.println("Input: ");
        }
        for(int i = 0; i<input.length; i++){
            input[i] = (int) (Math.random() * size);
            if(displayOutput){
                System.out.print(input[i] + ", ");
            }
        }

        // test run + timing
        long start = System.nanoTime();
        int[] actual = BubbleSorter.bubbleSort(input);
        long end = System.nanoTime();

        // displaying list + time taken
        if(displayOutput){
            System.out.println("\nDuration: " + (end-start)+ "ns") ;
            System.out.println("Output: ");
            for(int i = 0; i<actual.length; i++){
                System.out.print(actual[i] + ", ");
            }
        }

        // validity check
        assertEquals(true, isIntListAscending(actual));
    }

    @Test
    public void testGenericBubbleSort(){
        // test generic parameters
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
        BubbleSorter<Integer> bubbleSorter = new BubbleSorter<>();
        List<Integer> actual = bubbleSorter.sort(input);
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

    /*
     TODO: add test for array implementation
     TODO: reduce code repetition by having a setup method?
     TODO: add tests for strings
    */

}

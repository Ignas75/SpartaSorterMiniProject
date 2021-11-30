package com.spartaglobal.sortproject.utilities;

import java.util.ArrayList;

public class ListGenerator{
    public static int[] generateRandomIntArray(int size){
        int[] arr = new int[size];
        for(int i = 0; i<size; i++){
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    public static Integer[] generateRandomIntegerArray(int size){
        int[] intArr = generateRandomIntArray(size);
        Integer[] arr = new Integer[size];
        for(int i = 0; i<size; i++){
            arr[i] = intArr[i];
        }
        return arr;
    }

    public static ArrayList<Integer> generateRandomIntegerList(int size){
        ArrayList<Integer> list = new ArrayList<>(size);
        Integer[] array = generateRandomIntegerArray(size);
        for(int i = 0; i<size; i++){
            list.add(array[i]);
        }
        return list;
    }
}

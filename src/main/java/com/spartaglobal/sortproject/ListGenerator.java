package com.spartaglobal.sortproject;

public class ListGenerator{
    public static int[] generateRandomIntList(int size){
        int[] arr = new int[size];
        for(int i = 0; i<size; i++){
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    public static Integer[] generateRandomIntegerList(int size){
        int[] intArr = generateRandomIntList(size);
        Integer[] arr = new Integer[size];
        for(int i = 0; i<size; i++){
            arr[i] = intArr[i];
        }
        return arr;
    }
}

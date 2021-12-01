package com.spartaglobal.sortproject.utilities;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils<E extends Comparable> {
    public boolean isSorted(E[] list){
        if(list.length <= 1){
            return true;
        }
        for(int i = 0; i<list.length-1; i++){
            if(list[i].compareTo(list[i+1]) > 0){
                return false;
            }
        }
        return true;
    }

    public boolean isSorted(List<E> list){
        if(list.size() <= 1){
            return true;
        }
        for(int i = 0; i<list.size()-1; i++){
            if(list.get(i).compareTo(list.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }

    public String arrayToString(E[] list){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i< list.length; i++){
            output = output.append(list[i].toString() + ", ");
        }
        return output.toString();
    }

    public ArrayList<Integer> copyIntArrayToIntegerArrayList(int[] list){
        ArrayList<Integer> output = new ArrayList<>(list.length);
        for(int i: list){
            output.add(i);
        }
        return output;
    }

    public Integer[] copyIntArrayToIntegerArray(int[] list){
        Integer[] output = new Integer[list.length];
        for(int i = 0; i < list.length; i++){
           output[i] = list[i];
        }
        return output;
    }
}

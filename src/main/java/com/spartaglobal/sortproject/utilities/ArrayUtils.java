package com.spartaglobal.sortproject.utilities;

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
}

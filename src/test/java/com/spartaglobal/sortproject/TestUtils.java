package com.spartaglobal.sortproject;

public class TestUtils<E extends Comparable> {
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

    public void outputArray(E[] list){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i< list.length; i++){
            output = output.append(list[i].toString() + ", ");
        }
        System.out.printf(output.toString());
    }
}

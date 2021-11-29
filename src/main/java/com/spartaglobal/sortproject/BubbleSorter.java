package com.spartaglobal.sortproject;

public class BubbleSorter<E extends  Comparable> extends GenericSorter<E>{

    @Override
    public E[] sort(E[] list){
        int current = 0;
        int end = list.length;
        while(end>0){
            while(current<end-1){
                if(list[current].compareTo(list[current+1]) > 0){
                    E temp = list[current];
                    list[current] = list[current+1];
                    list[current+1] = temp;
                }
                current++;
            }
            current = 0;
            end --;
        }
        return list;
    }

    public static int[] bubbleSort(int[] list){
        int current = 0;
        int end = list.length;
        while(end>0){
            while(current<end-1){
                if(list[current] > list[current+1]){
                    int temp = list[current];
                    list[current] = list[current+1];
                    list[current+1] = temp;
                }
                current++;
            }
            current = 0;
            end --;
        }
        return list;
    }
}

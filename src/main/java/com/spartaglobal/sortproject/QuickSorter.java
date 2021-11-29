package com.spartaglobal.sortproject;

import java.util.ArrayList;

public class QuickSorter<E extends  Comparable> extends  GenericSorter<E>{
    @Override
    public E[] sort(E[] list){
        int size = list.length;
        return quickSort(list, 0, size-1);
    }

    private E[] quickSort(E[] list, int left, int right){
        // setup
        int range = right-left;
        int pivot = (int) (Math.random() * range) + left;
        E midVal = list[pivot];
        ArrayList<E> smaller = new ArrayList<>();
        ArrayList<E> larger = new ArrayList<>();

        // finding smaller and larger lists of values compared to pivot
        for(int i = left; i<=right; i++){
            if(i != pivot){
                E val = list[i];
                if(midVal.compareTo(val) > 0){
                    smaller.add(val);
                }
                else{
                    larger.add(val);
                }
            }
        }
        // putting smaller(and larger) to the left (and right) of the pivot
        int i = left;
        for(E val: smaller){
            list[i] = val;
            i++;
        }
        list[i] = midVal;
        i++;
        for(E val:larger){
            list[i] = val;
            i++;
        }
        // sorting the left and right of the midval if there is sorting to be performed (>=2 elements in sublists)
        if(smaller.size() >= 2){
            list = quickSort(list, left, left+smaller.size()-1);
        }
        if(larger.size() >= 2){
            list = quickSort(list, right-larger.size()+1, right);
        }
        return list;
    }
}
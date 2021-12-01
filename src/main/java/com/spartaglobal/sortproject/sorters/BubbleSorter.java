package com.spartaglobal.sortproject.sorters;

import java.util.List;

public class BubbleSorter<E extends  Comparable> extends GenericSorter<E> {

    @Override
    public List<E> sort(List<E> list){
        int current = 0;
        int end = list.size();
        while(end>0){
            while(current<end-1){
                E currentVal = list.get(current);
                E nextVal = list.get(current+1);
                if(currentVal.compareTo(nextVal) > 0){
                    E temp = currentVal;
                    list.set(current, nextVal);
                    list.set(current+1, currentVal);
                }
                current++;
            }
            current = 0;
            end --;
        }
        return list;
    }

    public E[] sort(E[] list){
        int current = 0;
        int end = list.length;
        while(end>0){
            while(current<end-1){
                E currentVal = list[current];
                E nextVal = list[current+1];
                if(currentVal.compareTo(nextVal) > 0){
                    E temp = currentVal;
                    list[current] = nextVal;
                    list[current+1] =  currentVal;
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

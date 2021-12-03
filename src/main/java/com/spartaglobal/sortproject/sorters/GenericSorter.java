package com.spartaglobal.sortproject.sorters;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSorter<E extends Comparable> {
    public abstract List<E> sort(List<E> list);
    public E[] arraySort(E[] list){
        List<E> arrayList = new ArrayList<>();
        for(E item: list){
            arrayList.add(item);
        }
        arrayList = sort(arrayList);
        for(int i = 0; i<arrayList.size(); i++){
            list[i] = arrayList.get(i);
        }
        return list;
    };
}

package com.spartaglobal.sortproject;

public abstract class GenericSorter<E extends Comparable> {
    public abstract E[] sort(E[] list);
}

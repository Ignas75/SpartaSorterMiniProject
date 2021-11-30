package com.spartaglobal.sortproject.sorters;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSorter<E extends Comparable> {
    public abstract List<E> sort(List<E> list);
}

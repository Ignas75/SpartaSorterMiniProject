package com.spartaglobal.sortproject.utilities;
import com.spartaglobal.sortproject.SortDriver.SortingAlgorithms;
import com.spartaglobal.sortproject.SortDriver.SortableType;
import com.spartaglobal.sortproject.sorters.BubbleSorter;
import com.spartaglobal.sortproject.sorters.GenericSorter;
import com.spartaglobal.sortproject.sorters.QuickSorter;
import com.spartaglobal.sortproject.sorters.TreeSorter;

public class SorterFactory {
    public static GenericSorter makeSorter(SortingAlgorithms algorithm , SortableType dataType){
        return switch (algorithm) {
            case BUBBLESORT -> switch (dataType) {
                case INTEGER -> new BubbleSorter<Integer>();
                case DOUBLE -> new BubbleSorter<Double>();
                case STRING -> new BubbleSorter<String>();
            };
            case QUICKSORT -> switch (dataType) {
                case INTEGER  -> new QuickSorter<Integer>();
                case DOUBLE-> new QuickSorter<Double>();
                case STRING  -> new QuickSorter<String>();
            };
            case TREESORT -> switch (dataType) {
                case INTEGER  -> new TreeSorter<Integer>();
                case DOUBLE-> new TreeSorter<Double>();
                case STRING  -> new TreeSorter<String>();
            };
        };
    }
}

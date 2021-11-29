package com.spartaglobal.sortproject;
import com.spartaglobal.sortproject.SortDriver.SortingAlgorithms;
import com.spartaglobal.sortproject.SortDriver.SortableType;

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
        };
    }
}

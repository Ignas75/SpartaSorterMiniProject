package com.spartaglobal.sortproject;

public class SorterFactory {
    public static GenericSorter makeSorter(String algorithm , String dataType){
        return switch (algorithm) {
            case ("BubbleSort") -> switch (dataType) {
                case ("Integer") -> new BubbleSorter<Integer>();
                case ("Double") -> new BubbleSorter<Double>();
                case ("String") -> new BubbleSorter<String>();
                default -> null;
            };
            case ("QuickSort") -> switch (dataType) {
                case ("Integer") -> new QuickSorter<Integer>();
                case ("Double") -> new QuickSorter<Double>();
                case ("String") -> new QuickSorter<String>();
                default -> null;
            };
            default -> null;
        };
    }
}

package com.spartaglobal.sortproject;

import com.spartaglobal.sortproject.sorters.GenericSorter;
import com.spartaglobal.sortproject.utilities.ArrayUtils;
import com.spartaglobal.sortproject.utilities.ListGenerator;
import com.spartaglobal.sortproject.utilities.SorterFactory;
import com.spartaglobal.sortproject.utilities.TypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortDriver {
    public enum SortableType{STRING("Word", "[+-]?[a-zA-Z]+"),
        DOUBLE("Real", "[+-]?\\d+(?:\\.\\d+)?"),
        INTEGER("Integer", "[+-]?[0-9]+");

        private final String descriptor;
        private final String format;

        SortableType(String descriptor, String format){
            this.descriptor = descriptor;
            this.format = format;
        }

        public static SortableType getType(String descriptor){
            for(SortableType type: SortableType.values()){
                if(type.descriptor.equals(descriptor)){
                    return type;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.descriptor;
        }

        public String getFormat(){
            return this.format;
        }
    }

    public enum SortingAlgorithms{BUBBLESORT, QUICKSORT;
        public static SortingAlgorithms getType(String name){
            for(SortingAlgorithms algorithm: SortingAlgorithms.values()){
                if(algorithm.toString().equals(name)){
                    return algorithm;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){
        boolean finished = false;
        while(!finished){
            ArrayList<String> choices = new ArrayList<>();
            choices.add("Sort a list");
            choices.add("Compare sorting algorithms");
            choices.add("Compare array and list implementations of an algorithm");
            choices.add("Exit the program");
            String header = "Main Menu";
            int choice = menuChoice(choices, header);

            if(choice == 1){
                sortingProcess();
            }
            else if(choice == 2){
                if(SortingAlgorithms.values().length < 2){
                    System.out.println("Somehow there is only one algorithm so we can't compare it against another");
                    System.out.println("Please choose another menu option");
                }
                else{
                    algorithmComparison();
                }
            }
            else if(choice == 3){
                arraysComparedWithArrayLists();
            }
            else{
                finished = true;
            }
        }
    }

    // could be made more generic by looking in a package/folder for sort classes count and their names
    public static void sortingProcess(){
        // finding out user algorithm choice
        SortingAlgorithms algorithm = chooseSortingAlgorithm();
        SortableType dataType = chooseDataType();
        GenericSorter sorter = SorterFactory.makeSorter(algorithm, dataType);
        ArrayList data = getUserData(dataType);
        System.out.println(data);
        try{
            switch(dataType){
                case INTEGER:
                    data = TypeConverter.stringToInteger(data);
                    break;
                case DOUBLE:
                    data = TypeConverter.stringToDouble(data);
                    break;
                case STRING:
                default:
                    break;
            }
            sorter.sort(data);
            System.out.println(data);
        } catch(Exception e){
            System.out.println("Expected data type mismatch with received data");
            e.printStackTrace();
        }
    }


    public static void algorithmComparison(){
        // choosing the algorithms to compare
        SortingAlgorithms algorithm1;
        SortingAlgorithms algorithm2;
        if(SortingAlgorithms.values().length == 2){
            System.out.println("\nThere are only two algorithms so we will compare them, they are:");
            algorithm1 = SortingAlgorithms.values()[0];
            algorithm2 = SortingAlgorithms.values()[1];
            System.out.println(algorithm1);
            System.out.println(algorithm2);
        }
        else{
            algorithm1 = chooseSortingAlgorithm();
            algorithm2 = chooseSortingAlgorithm(true, algorithm1);
            System.out.println("\n We will be comparing" + algorithm1 + " and " + algorithm2);
        }
        // comparing sorting algorithms
        int arraySize = getUserComparisonAmount();
        int[] input = ListGenerator.generateRandomIntArray(arraySize);
        ArrayUtils<Integer> arrayUtils = new ArrayUtils<>();
        List<Integer> input1 = arrayUtils.copyIntArrayToIntegerArrayList(input);
        List<Integer> input2 = arrayUtils.copyIntArrayToIntegerArrayList(input);
        System.out.println("Sorting: ");
        System.out.println(input1);
        long algorithmOneDuration = timeSort(algorithm1, input1, true);

        long algorithmTwoDuration = timeSort(algorithm2, input2, true);
        System.out.println(algorithm2 + " finished in " + algorithmTwoDuration + "ns");
    }

    public static void arraysComparedWithArrayLists(){
        SortingAlgorithms algorithm = chooseSortingAlgorithm();
        SorterFactory.makeSorter(algorithm, SortableType.INTEGER);
        int quantity = getUserComparisonAmount();
        int[] input = ListGenerator.generateRandomIntArray(quantity);
        ArrayUtils<Integer> arrayUtils = new ArrayUtils<>();
        Integer[] arrayInput = arrayUtils.copyIntArrayToIntegerArray(input);
        ArrayList<Integer> arrayListInput = arrayUtils.copyIntArrayToIntegerArrayList(input);
        long arrayDuration = timeSort(algorithm, arrayInput, true);
        long arrayListDuration = timeSort(algorithm, arrayListInput, true);
    }

    public static long timeSort(SortingAlgorithms algorithm, List<Integer> input, boolean displaySortResult){
        GenericSorter<Integer> sorter = SorterFactory.makeSorter(algorithm, SortableType.INTEGER);
        long start = System.nanoTime();
        List<Integer> output = sorter.sort(input);
        long end = System.nanoTime();
        long duration = end-start;
        if(displaySortResult) {
            System.out.println(output);
            System.out.println(algorithm + " finished in " + duration + "ns");
        }
        return duration;
    }

    // main reason for this code repetition is to conduct array and arraylist implementation comparisons
    public static long timeSort(SortingAlgorithms algorithm, Integer[] input, boolean displaySortResult){
        GenericSorter<Integer> sorter = SorterFactory.makeSorter(algorithm, SortableType.INTEGER);
        long start = System.nanoTime();
        Integer[]  output = sorter.arraySort(input);
        long end = System.nanoTime();
        long duration = end-start;
        if(displaySortResult) {
            System.out.println(output);
            System.out.println(algorithm + " finished in " + duration + "ns");
        }
        return duration;
    }

    // Exception handling showcase
    public static int getUserComparisonAmount(){
        Scanner scanner = new Scanner(System.in);
        // default value, removing this makes Java Complain about lack of initialisation
        int quantity = 1000;
        boolean succeeded = false;
        while(!succeeded){
            try{
                System.out.println("\nEnter how many elements would you like in your array:");
                if(scanner.hasNextInt()){
                    quantity = scanner.nextInt();
                    succeeded = true;
                }
            }catch(Exception e){
                System.out.println("Please enter nothing but a whole number");
            }
        }
        return quantity;

    }

    public static SortingAlgorithms chooseSortingAlgorithm(boolean excludeChoice, SortingAlgorithms excludedAlgorithm){
        ArrayList<String> choices = new ArrayList<>();
        for(SortingAlgorithms algorithm: SortingAlgorithms.values()){
            if(!(excludeChoice && algorithm == excludedAlgorithm)){
                choices.add(algorithm.toString());
            }
        }
        String header = "Choose a sorting algorithm:";
        int choice = menuChoice(choices, header);
        // finding out user data type choice
        String algorithmStr = choices.get(choice-1);
        return SortingAlgorithms.getType(algorithmStr);
    }

    public static SortingAlgorithms chooseSortingAlgorithm(){
        // doesn't matter what is excluded because nothing is excluded
        return chooseSortingAlgorithm(false, SortingAlgorithms.values()[0]);
    }

    public static SortableType chooseDataType(){
        ArrayList<String> choices = new ArrayList<>();
        for(SortableType type: SortableType.values()){
            choices.add(type.toString());
        }
        String header = "Choose what you want to sort";
        int choice = menuChoice(choices, header);
        String strChoice = choices.get(choice-1);
        return SortableType.getType(strChoice);
    }

    // receives data in the form of the provided type, returns a String arraylist
    public static ArrayList getUserData(SortableType dataType){
        // setting the expected data format
        String format = dataType.getFormat();

        // loop setup
        boolean finished = false;
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nTo exit, only press enter at any point" +
                "\nPlease enter your data one by one or separated by commas:\n");
        String input;
        // getting data input loop
        while(!finished){
            input = scanner.nextLine();
            // exit condition
            if(input.length() == 0){
                finished = true;
            }
            // handling lists that were input
            else if(input.contains(",")){
                String[] multiEntry = input.split(",");
                for(String entry: multiEntry){
                    entry = entry.trim();
                    if(entry.matches(format)){
                        list.add(entry);
                    }
                    else{
                        System.out.println("Your entry '" + entry + "' does not match the " + dataType +
                                " format, it has been rejected");
                    }
                }
            }
            // handling individual input
            else{
                input = input.trim();
                if(input.matches(format)){
                    list.add(input);
                }
                else{
                    System.out.println("Your entry '" + input + "' does not match the " + dataType +
                            " format, it has been rejected");
                }
            }
        }
        return list;
    }

    // TODO: refactor to take in K, V so that it's clearer what menu choice is being made

    // utility method for displaying a header, followed by a list of choices
    // returns a value 1-n where n is the number of choices
    public static int menuChoice(ArrayList<String> choices, String header){
        int choice_amount = choices.size();
        while(true){
            // displaying menu
            System.out.println("\n\n" + header);
            System.out.println("--------------------------------");
            for(int i = 0; i<choice_amount; i++){
                System.out.println(i+1 + ": " + choices.get(i));
            }
            System.out.println("Please enter your choice: ");
            // getting user input
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
            userChoice = userChoice.trim();
            // invalid input
            if(!userChoice.matches("[0-9]*")){
                System.out.println("Please enter a whole number");
            }
            // valid input
            else{
                int choice = Integer.parseInt(userChoice);
                if(choice <= choice_amount && choice >= 1){
                    return choice;
                }
                else{
                    System.out.println("Please enter a number between 1 and " + choice_amount);
                }
            }
        }
    }


}

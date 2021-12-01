package com.spartaglobal.sortproject;

import com.spartaglobal.sortproject.sorters.GenericSorter;
import com.spartaglobal.sortproject.utilities.SorterFactory;
import com.spartaglobal.sortproject.utilities.TypeConverter;

import java.util.ArrayList;
import java.util.Scanner;

public class SorterStarter {
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
            choices.add("Compare array and list implementations");
            choices.add("Exit the program");
            String header = "Main Menu";
            int choice = menuChoice(choices, header);

            if(choice == 1){
                sortingProcess();
            }
            else if(choice == 2){
                /* TODO: implement compare sorting algorithms */
            }
            else if(choice == 3){
                // TODO: implement comparing array and list implementations
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

    public static SortingAlgorithms chooseSortingAlgorithm(){
        ArrayList<String> choices = new ArrayList<>();
        for(SortingAlgorithms algorithm: SortingAlgorithms.values()){
            choices.add(algorithm.toString());
        }
        String header = "Choose a sorting algorithm:";
        int choice = menuChoice(choices, header);
        // finding out user data type choice
        String algorithmStr = choices.get(choice-1);
        return SortingAlgorithms.getType(algorithmStr);
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

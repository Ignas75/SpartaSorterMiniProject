package com.spartaglobal.sortproject;

import java.util.ArrayList;
import java.util.Scanner;

public class SortDriver {
    // TODO: replace dataType with SortableType within this class
    public enum SortableType{STRING("Word"), DOUBLE("Real"), INTEGER("Integer");
        private final String descriptor;

        SortableType(String descriptor){
            this.descriptor = descriptor;
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
            ArrayList<String> choices = new ArrayList<String>();
            choices.add("Sort a list");
            choices.add("Compare sorting algorithms");
            choices.add("Exit the program");
            String header = "Main Menu";
            int choice = menuChoice(choices, header);

            if(choice == 1){
                sortingProcess();
            }
            else if(choice == 2){
                /* TODO: implement compare sorting algorithms */
            }
            else{
                finished = true;
            }
        }
    }

    // could be made more generic by looking in a package/folder for sort classes count and their names
    public static void sortingProcess(){
        // finding out user algorithm choice
        ArrayList<String> choices = new ArrayList<String>();
        for(SortingAlgorithms algorithm: SortingAlgorithms.values()){
            choices.add(algorithm.toString());
        }
        String header = "Choose a sorting algorithm:";
        int choice = menuChoice(choices, header);
        // finding out user data type choice
        String algorithmStr = choices.get(choice);
        SortingAlgorithms algorithm = SortingAlgorithms.getType(algorithmStr);
        SortableType dataType = chooseDataType();
        GenericSorter sorter = SorterFactory.makeSorter(algorithm, dataType);
        ArrayList<String> data = getUserData(dataType);
        String[] strData = (String[]) data.toArray();
        int size = strData.length;
        switch (dataType){
            case INTEGER:

        }

    }

    public static SortableType chooseDataType(){
        ArrayList<String> choices = new ArrayList<String>();
        for(SortableType type: SortableType.values()){
            choices.add(type.toString());
        }
        String header = "Choose what you want to sort";
        int choice = menuChoice(choices, header);
        String strChoice = choices.get(choice);
        return SortableType.getType(strChoice);
    }

    // converts String arraylist to one of the target type
    // if the target type is STRING (or uncovered newly added type) then it returns the list itself without change
    public static ArrayList convertArrayListType(ArrayList<String> strList, SortableType type){
        ArrayList list;
        switch (type){
            case INTEGER:
                list = new ArrayList<Integer>();
                break;
            case DOUBLE:
                list = new ArrayList<Double>();
                break;
            default:
                return strList;
        }

        for(String str: strList){
            switch (type){
                case INTEGER:
                    list.add(Integer.valueOf(str));
                    break;
                case DOUBLE:
                    list.add(Double.valueOf(str));
                    break;
            }
        }

        return list;
    }

    // receives data in the form of the provided type, returns a String arraylist
    public static ArrayList getUserData(SortableType dataType){
        // setting the expected data format
        String format;
        switch(dataType){
            case INTEGER:
                format = "[0-9]+";
            case DOUBLE:
                format = "[[0-9]+] | [[0-9]+.[0-9]]";
            case STRING:
                format = "[a-zA-Z]+";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dataType);
        }
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
                                "format, it has been rejected");
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
                            "format, it has been rejected");
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

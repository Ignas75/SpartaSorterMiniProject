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
        ArrayList<String> choices = new ArrayList<String>();
        for(SortingAlgorithms algorithm: SortingAlgorithms.values()){
            choices.add(algorithm.toString());
        }
        String header = "Choose a sorting algorithm:";
        int choice = menuChoice(choices, header);
        String algorithmStr = choices.get(choice);
        SortingAlgorithms algorithm = SortingAlgorithms.getType(algorithmStr);
        SortableType dataType = chooseDataType();
        GenericSorter sorter = SorterFactory.makeSorter(algorithm, dataType);

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

    public ArrayList getUserData(SortableType dataType){
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

    public static int menuChoice(ArrayList<String> choices, String header){
        int choice_amount = choices.size();
        while(true){
            System.out.println("\n\n" + header);
            System.out.println("--------------------------------");
            for(int i = 0; i<choice_amount; i++){
                System.out.println(i+1 + ": " + choices.get(i));
            }
            System.out.println("Please enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
            userChoice = userChoice.trim();
            if(!userChoice.matches("[0-9]*")){
                System.out.println("Please enter a whole number");
            }
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

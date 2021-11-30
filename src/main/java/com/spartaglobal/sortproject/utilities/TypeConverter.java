package com.spartaglobal.sortproject.utilities;

import java.util.ArrayList;
import java.util.List;

public class TypeConverter {
    public static ArrayList<Integer> stringToInteger(List<String> strList){
        ArrayList<Integer> list = new ArrayList();
        for(String str: strList){
            list.add(Integer.valueOf(str));
        }
        return list;
    }

    public static ArrayList<Double> stringToDouble(List<String> strList){
        ArrayList<Double> list = new ArrayList();
        for(String str: strList){
            list.add(Double.valueOf(str));
        }
        return list;
    }
}

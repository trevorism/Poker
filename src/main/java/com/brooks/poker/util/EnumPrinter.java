package com.brooks.poker.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trevor
 * 
 */
public class EnumPrinter{

    private static List<String> doNotCapitalize;

    static{
        doNotCapitalize = new ArrayList<String>();
        doNotCapitalize.add("a");
        doNotCapitalize.add("of");

    }

    public static String convertCase(Enum<?> enumeration){
        return convertCase(enumeration.toString());
    }

    public static String convertCase(String enumString){
        String[] strList = enumString.split("[_ ]");
        return joinWithCapitals(strList);
    }

    private static String joinWithCapitals(String[] strList){
        StringBuilder builder = new StringBuilder();
        for (String word : strList){
            String printableWord = printWord(word);
            builder.append(printableWord);
            builder.append(" ");
        }
        return builder.toString().trim();

    }

    private static String printWord(String word){
        word = word.toLowerCase().trim();
        if (shouldCapitalize(word)){
            word = capitalize(word);
        }
        return word;
    }

    private static String capitalize(String lowerCaseWord){
        String firstChar = lowerCaseWord.substring(0, 1).toUpperCase();
        String restOfString = lowerCaseWord.substring(1);
        return firstChar + restOfString;
    }

    private static boolean shouldCapitalize(String word){
        if (doNotCapitalize.contains(word))
            return false;
        return true;
    }

}

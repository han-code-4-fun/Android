package com.udacity.sandwichclub.utils;

public final class StringUtilis {

    //remove begining and ending square bracket
    public static String sanitize(String input)
    {
        if(input.length() >1)
        {
            if(input.charAt(0)=='[')
            {
                input = input.substring(1);
            }
            if(input.charAt(input.length()-1)==']')
            {
                input = input.substring(0,input.length()-1);
            }
        }
        return input;
    }
}

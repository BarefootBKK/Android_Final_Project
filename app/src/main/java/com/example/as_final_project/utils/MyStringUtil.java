package com.example.as_final_project.utils;

public class MyStringUtil {
    public static String getSubString(String str, int length) {
        if (str.length() > length) {
            String temp = str.substring(0, length);
            return temp + "...";
        } else {
            return str;
        }
    }
}

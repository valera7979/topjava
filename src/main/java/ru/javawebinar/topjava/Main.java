package ru.javawebinar.topjava;

import java.util.*;
import java.util.function.Consumer;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n-- Lambda Expressions: Sort List --");
        List<String> st = Arrays.asList("Steve", "Alex", "Jim", "Bob");
        System.out.println("Before sort: " + st);
        System.out.println("After sort: " + Main.sort(st));

           }

    public static List<String> sort(List<String> st){
        Collections.sort(st, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        //Collections.sort(st, (a, b) -> a.compareTo(b));

        return st;
    }


}

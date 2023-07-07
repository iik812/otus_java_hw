package ru.otus;

import com.google.common.collect.ImmutableList;

public class HelloOtus {
    public static void main(String[] args) {
        ImmutableList<String> list = ImmutableList.of("one", "two", "three");
        System.out.println(list);
    }
}

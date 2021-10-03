package com.example.dictionaryapp;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Dictionary{
    protected static ArrayList<World> array = new ArrayList<>(); // mang dung luu tu
    protected static List<String> listWordTarget = new ArrayList<>(); // mang luu nghia
    public static void modify(int i, World newWord)
    {
        array.set(i, newWord);
    }

}

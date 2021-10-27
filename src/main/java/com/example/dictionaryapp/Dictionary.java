package com.example.dictionaryapp;

import java.util.ArrayList;
import java.util.List;

public class Dictionary{
    protected static ArrayList<Word> words = new ArrayList<>(); // mang dung luu tu
    protected static List<String> listWordTarget = new ArrayList<>(); // mang luu nghia
    public static void modify(int i, Word newWord)
    {
        words.set(i, newWord);
    }
}

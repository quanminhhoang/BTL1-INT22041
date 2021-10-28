package com.example.dictionaryapp;

import java.util.ArrayList;
import java.util.List;

public class Dictionary{
    protected static ArrayList<Word> words = new ArrayList<>(); // mang dung luu tu
    protected static List<String> listWordTarget = new ArrayList<>(); // mang luu nghia
    public static void AddWorld(Word world, String target) {
        words.add(world);
        listWordTarget.add(target);
    }
    public static void modify(int i, Word newWord)
    {
        words.set(i, newWord);
    }
}
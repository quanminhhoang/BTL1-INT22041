package com.example.dictionaryapp;

import static com.example.dictionaryapp.DictionaryManagement.InsertFromFile;
import static com.example.dictionaryapp.DictionaryManagement.dictionaryLookup;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords() {

    }

    public static void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() {
        InsertFromFile();
        showAllWords();
        dictionaryLookup();
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();
        ArrayList<Word> words = Dictionary.searcher(word_target);

        for (Word word : words) {
            System.out.print(word.getWord_target());
            System.out.println(" " + word.getWord_explain());
        }
    }
}




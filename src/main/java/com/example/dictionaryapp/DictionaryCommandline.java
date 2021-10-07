package com.example.dictionaryapp;

import java.io.FileNotFoundException;

public class DictionaryCommandline {
    public static void showAllWords(DictionaryManagement a) {
        System.out.printf("%-22s%-22s%-22s\n","No","Word","Explain");
        int i = 1;
        for(World word : a.array){
            System.out.printf("%-22d%-22s%-22s\n",i,word.getWorld_target(),word.getWorld_explain());
            i++;
        }
    }

    public static void main(String[] args) {
        DictionaryManagement a = new DictionaryManagement();
        try {
            a.InsertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        showAllWords(a);
    }

}




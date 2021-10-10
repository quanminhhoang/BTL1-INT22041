package com.example.dictionaryapp;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement{
    public static void showAllWords(DictionaryManagement a) {
        System.out.printf("%-22s%-22s%-22s\n","No","Word","Explain");
        int i = 1;
        for(World word : a.array){
            System.out.printf("%-22d%-22s%-22s\n",i,word.getWorld_target(),word.getWorld_explain());
            i++;
        }
    }
    public static void DictionarySearcher()
    {
        Scanner x = new Scanner(System.in);
        String e = x.next();
        x.close();
        for (World world : array) {
            if (world.getWorld_target().toLowerCase().startsWith(e.toLowerCase())) {
                add_up.add(world.getWorld_target());
            }
        }
        Collections.sort(add_up);
        List<String> list = add_up;
        Collections.sort(list);
        System.out.println(list);

    }

    public static void main(String[] args) {
        DictionaryManagement a = new DictionaryManagement();
        try {
            a.InsertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DictionarySearcher();


    }


}




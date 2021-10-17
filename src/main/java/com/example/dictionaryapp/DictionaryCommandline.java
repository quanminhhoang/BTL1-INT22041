package com.example.dictionaryapp;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords() {
        System.out.printf("%-11s%-22s%-22s\n", "No", "Word", "Explain");
        int num = 1;
        for (World word : array) {
            System.out.printf("%-11d%-22s%-22s\n", num, word.getWorld_target(), word.getWorld_explain());
            num++;
        }
    }

    public static void Basic() {
        Scanner scanner = new Scanner(System.in);
        String Word;
        while (true) {
            System.out.println("Nhập ShowAllWord() để hiện thị tất cả từ.");
            System.out.println("Nhập InsertFromCmd() để nhập liệu.");
            System.out.println("Nhập Back() để quay lại.");
            Word = scanner.next();
            if (Objects.equals(Word, "ShowAllWord()")) {
                System.out.println("Bạn đã chọn hiện thị tất cả từ.");
                showAllWords();
            } else if (Objects.equals(Word, "InsertFromCmd()")) {
                System.out.println("Bạn đã chọn nhập dữ liệu.");
                insertFromCMD();
            } else if (Objects.equals(Word, "Back()")) {
                System.out.println("Bạn đã chọn quay lại.");
                break;
            } else {
                System.out.println("Hãy nhập lại ~~.");
            }
        }
    }

    public static void Advanced() {
        Scanner scanner = new Scanner(System.in);
        String Word;
        while (true) {
            System.out.println("Nhập ShowAllWord() để hiện thị tất cả từ.");
            System.out.println("Nhập InsertFromFile() để nhận dữ liệu từ file.");
            System.out.println("Nhập LookUp() để tra cứu từ.");
            System.out.println("Nhập Back() để quay lại.");
            Word = scanner.next();
            if (Objects.equals(Word, "ShowAllWord()")) {
                System.out.println("Bạn đã chọn hiện thị tất cả từ.");
                showAllWords();
            } else if (Objects.equals(Word, "InsertFromFile()")) {
                System.out.println("Bạm đã nhận dữ liệu từ file.");
                try {
                    InsertFromFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(Word, "LookUp()")) {
                System.out.println("Bạm đã chọn LookUp().");
                System.out.println("Nhập từ để tra cứu:");
                String Wordtolook = scanner.next();
                System.out.println("Nghĩa của " + Wordtolook + " là: " + dictionaryLookup(Wordtolook) +'.');
            } else if (Objects.equals(Word, "Back()")) {
                System.out.println("Bạn đã chọn quay lại.");
                break;
            } else {
                System.out.println("Hãy nhập lại ~~");
            }
        }
    }

    public static void DictionarySearcher() {
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
}




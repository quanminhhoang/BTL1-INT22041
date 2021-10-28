package com.example.dictionaryapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords() {
        System.out.printf("%-11s%-22s%-22s\n", "No", "Word", "Explain");
        int num = 1;
        for (Word word : words) {
            System.out.printf("%-11d%-22s%-22s\n", num, word.getWorld_target(), word.getWorld_explain());
            num++;
        }
    }

    public static void Basic() {
        Scanner scanner = new Scanner(System.in);
        int next;
        while (true) {
            System.out.println("Nhập 1 để hiện thị tất cả từ ShowAllWord().");
            System.out.println("Nhập 2 để nhập liệu InsertFromCmd().");
            System.out.println("Nhập 3 để quay lại.");
            next = scanner.nextInt();
            if (next == 1) {
                System.out.println("Bạn đã chọn hiện thị tất cả từ.");
                showAllWords();
            } else if (next == 2) {
                System.out.println("Bạn đã chọn nhập dữ liệu.");
                InsertFromCMD();
            } else if (next == 3) {
                System.out.println("Bạn đã chọn quay lại.");
                break;
            } else {
                System.out.println("Hãy nhập lại ~~.");
            }
        }
    }

    public static void Advanced() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int next;
        while (true) {
            System.out.println("Nhập 1 để hiện thị tất cả từ ShowAllWord().");
            System.out.println("Nhập 2 để nhận dữ liệu từ file InsertFromFile().");
            System.out.println("Nhập 3 để tra cứu từ.");
            System.out.println("Nhập 4 để quay lại.");
            next = scanner.nextInt();
            if (next == 1) {
                System.out.println("Bạn đã chọn hiện thị tất cả từ.");
                showAllWords();
            } else if (next == 2) {
                System.out.println("Bạm đã nhận dữ liệu từ file.");
                try {
                    InsertFromFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (next == 3) {
                System.out.println("Bạm đã chọn LookUp().");
                System.out.println("Nhập từ để tra cứu:");
                String Word = scanner.next();
                System.out.println("Nghĩa của " + Word + " là: " + DictionaryLookup(Word) +'.');
            } else if (next == 4) {
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
        for (Word word : words) {
            if (word.getWorld_target().toLowerCase().startsWith(e.toLowerCase())) {
                add_up.add(word.getWorld_target());
            }
        }
        Collections.sort(add_up);
        List<String> list = add_up;
        Collections.sort(list);
        System.out.println(list);

    }
}




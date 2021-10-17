package com.example.dictionaryapp;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            DictionaryManagement.InsertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String Code;
        while (true) {
            System.out.println("Nhập Basic() để chọn DictionaryBacic()");
            System.out.println("Nhập Advanced() để chọn DictionaryAdvanced()");
            System.out.println("Nhập End() để kết thúc chương trình");
            Code = scanner.next();

            if (Objects.equals(Code, "Basic()")) {
                System.out.println("DictionaryBasic Mode");
                DictionaryCommandline.Basic();
            } else if (Objects.equals(Code, "Advanced()")) {
                System.out.println("DictionaryAdvanced Mode");
                DictionaryCommandline.Advanced();
            } else if (Objects.equals(Code, "End()")) {
                System.out.println("Kết thúc chương trình.");
                scanner.close();
                break;
            }
        }
    }
}

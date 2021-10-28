package com.example.dictionaryapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            DictionaryManagement.InsertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        int Code;
        while (true) {
            System.out.println("Nhập 1 để chọn DictionaryBacic()");
            System.out.println("Nhập 2 để chọn DictionaryAdvanced()");
            System.out.println("Nhập 3 để kết thúc chương trình");
            Code = scanner.nextInt();

            if (Code == 1) {
                System.out.println("DictionaryBasic Mode");
                DictionaryCommandline.Basic();
            } else if (Code ==2) {
                System.out.println("DictionaryAdvanced Mode");
                DictionaryCommandline.Advanced();
            } else if (Code == 3) {
                System.out.println("Kết thúc chương trình.");
                scanner.close();
                break;
            }
        }
    }
}

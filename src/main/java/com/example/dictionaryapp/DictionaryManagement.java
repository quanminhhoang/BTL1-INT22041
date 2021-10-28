package com.example.dictionaryapp;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {

    public static List<String> add_up = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi them
    public static List<String> remove_out = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi xoa

    public static List<String> AddWord(String word_target, String word_explain) // them tu va tra ve danh sach tu moi
    {

        Word newWord = new Word(word_target, word_explain);
        words.add(newWord);
        for (Word word : words) {
            add_up.add(word.getWorld_target());

        }
        return add_up;
    }


    public static List<String> RemoveWord(String re) {

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWorld_target().equalsIgnoreCase(re)) {
                words.remove(i);
            } else {
                remove_out.add(words.get(i).getWorld_target());
            }
        }
        return remove_out;
    }

    public static void InsertFromFile() throws IOException {  // doc file
        File fileDir = new File("dictionaries.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
        while (br.ready()) {
            String lineWord = br.readLine();
            String[] parts = lineWord.split("\\t");
            if (parts.length == 2) {
                Word word = new Word(parts[0], parts[1]);
                words.add(word);
                listWordTarget.add(parts[0]);
            }
        }
        br.close();
    }


    // ham tra cuu tu va tra ve nghia
    public static String DictionaryLookup(String wordToLookUp) {
        for (Word word : words) {
            if (word.getWorld_target().equalsIgnoreCase(wordToLookUp)) {
                return word.getWorld_explain();
            }
        }
        return "Dont exist";
    }

    // viet lai file khi sua tu
    public static void ExportToFile() throws IOException {

        File file = new File("dataos.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        int i = 1;
        for (Word newWord : words) {
            if (i != 1) {
                outputStreamWriter.write("\n");
            }
            outputStreamWriter.write(newWord.getWorld_target());
            outputStreamWriter.write("\n");
            String t = newWord.getWorld_explain().replace("\n", "");
            outputStreamWriter.write(t);
            i++;
        }
        outputStreamWriter.flush();
    }

    //hàm chỉnh sửa nghĩa từ//
    public static String Modify(String change_target, String change_explain) throws IOException {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWorld_target().equalsIgnoreCase(change_target)) {
                Dictionary.modify(i, new Word(change_target, change_explain));
            }
        }
        ExportToFile();
        return change_explain;
    }

    public static void InsertFromCMD() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào từ.");
        String world = scanner.nextLine();
        System.out.println("Nhập vào nghĩa của từ.");
        String explain = scanner.nextLine();
        System.out.println("Từ " + world + " với nghĩa là " + explain + " đã được nhập.");
        Word word = new Word(world, explain);
        AddWorld(word, explain);
        try {
            ExportToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

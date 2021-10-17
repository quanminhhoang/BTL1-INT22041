package com.example.dictionaryapp;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {

    public static List<String> add_up = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi them
    public static List<String> remove_out = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi xoa

    public static List<String> addmoreword(String word_target, String word_explain) // them tu va tra ve danh sach tu moi
    {

        World newWord = new World(word_target, word_explain);
        array.add(newWord);
        for (World world : array) {
            add_up.add(world.getWorld_target());

        }
        return add_up;
    }


    public static List<String> removeWordFromDictionary(String re) {

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getWorld_target().equalsIgnoreCase(re)) {
                array.remove(i);
            } else {
                remove_out.add(array.get(i).getWorld_target());
            }
        }
        return remove_out;
    }

    public static void InsertFromFile() throws FileNotFoundException {  // doc file
        Scanner sc = new Scanner(new File("dataos.txt"));
        try {
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                String word_mean = sc.nextLine();
                array.add(new World(word, word_mean));
            }
            for (World world : array) {
                listWordTarget.add(world.getWorld_target());
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ham tra cuu tu va tra ve nghia
    public static String dictionaryLookup(String wordToLookUp) {
        for (World world : array) {
            if (world.getWorld_target().equalsIgnoreCase(wordToLookUp)) {
                return world.getWorld_explain();
            }
        }
        return "";
    }

    // viet lai file khi sua tu
    public static void EditFile() throws IOException {

        File file = new File("dataos.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        int i = 1;
        for (World newWord : array) {
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
    public static String modified(String change_target, String change_explain) throws IOException {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getWorld_target().equalsIgnoreCase(change_target)) {
                Dictionary.modify(i, new World(change_target, change_explain));
            }
        }
        EditFile();
        return change_explain;
    }

    public static void insertFromCMD() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào từ.");
        String world = scanner.nextLine();
        System.out.println("Nhập vào nghĩa của từ.");
        String explain = scanner.nextLine();
        System.out.println("Từ " + world + " với nghĩa là " + explain + " đã được nhập.");
        World word = new World(world, explain);
        AddWorld(word, explain);
        try {
            EditFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

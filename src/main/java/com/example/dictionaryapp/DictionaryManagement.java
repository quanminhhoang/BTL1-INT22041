package com.example.dictionaryapp;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.*;
import java.net.*;
import java.util.*;


public class DictionaryManagement extends Dictionary{

    public static List<String> add_up = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi them
    public static List<String> remove_out = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi xoa

    public static List<String> Addmoreword(String word_target, String word_explain) // them tu va tra ve danh sach tu moi
    {

        World newWord = new World(word_target, word_explain);
        array.add(newWord);
        for (World world : array) {
            add_up.add(world.getWorld_target());

        }
        return  add_up;
    }

    public static List<String> RemoveWordFromDictionary(String re)
    {

        for (int i=0; i< array.size(); i++)
        {
            if (array.get(i).getWorld_target().equalsIgnoreCase(re))
            {
                array.remove(i);
            }
            else
            {
                remove_out.add(array.get(i).getWorld_target());
            }
        }
        return remove_out;
    }

    public static void InsertFromFile() throws FileNotFoundException {  // doc file
        Scanner sc = new Scanner(new File("dataos.txt"));
        try {
            while(sc.hasNextLine())
            {
                String word = sc.nextLine();
                String word_mean = sc.nextLine();
                array.add(new World(word, word_mean));
            }
            for (World world : array) {
                listWordTarget.add(world.getWorld_target());
            }
            sc.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // ham tra cuu tu va tra ve nghia
    public static String DictionaryLookup(String wordToLookUp)
    {

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
        int i =1;
        for (World newWord : array)
        {
            if (i!=1)
            {
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
    public static String Modified(String change_target,String change_explain) throws IOException {
        for(int i=0;i<array.size();i++)
        {
            if(array.get(i).getWorld_target().equalsIgnoreCase(change_target))
            {
                Dictionary.modify(i,new World(change_target, change_explain));
            }
        }
        EditFile();
        return change_explain;
    }

    public static List<String> DictionarySearch(String wordSearch)
    {

        for (World world : array) {
            if (world.getWorld_target().toLowerCase().startsWith(wordSearch.toLowerCase())) {
                add_up.add(world.getWorld_target());
            }
        }
        Collections.sort(add_up);
        return add_up;
    }

    public static void TTS(String Speech)
    {

        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    Speech, null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 5000 tu 1 ngay 1 link
    public static String TranslateEnVi(String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbyXC0J_8nBkbpnAT96Oq_ptutFJHlHvZS_HR5Hy4qSsQvHr5Cw/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=vi&source=en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
    public static void main(String[] args) {
    }

}

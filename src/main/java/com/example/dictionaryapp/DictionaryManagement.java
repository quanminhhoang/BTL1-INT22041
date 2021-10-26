package com.example.dictionaryapp;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
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

    public static List<String> RemoveWordFromDictionary(String word)
    {
        int i;
        for(i = 0; i < array.size() ; i++) {
            if(array.get(i).getWorld_target().equals(word)) {
                array.remove(array.get(i));
            } else {
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
        return "//404//";
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

    public static Boolean WordExist(String Word) {
        for(World word : array) {
            if(Objects.equals(word.getWorld_target(), Word)) {
                return false;
            }
        }
        return true;
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLen = longer.length();
        if (longerLen == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLen - editDistance(longer, shorter)) / (double) longerLen;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] a = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    a[j] = j;
                else {
                    if (j > 0) {
                        int Value = a[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            Value = Math.min(Math.min(Value, lastValue),
                                    a[j]) + 1;
                        a[j - 1] = lastValue;
                        lastValue = Value;
                    }
                }
            }
            if (i > 0)
                a[s2.length()] = lastValue;
        }
        return a[s2.length()];
    }

    public static List<String> DictionarySearch(String wordSearch)
    {

        for (World world : array) {
            if (world.getWorld_target().toLowerCase().startsWith(wordSearch.toLowerCase()) || similarity(world.getWorld_target(), wordSearch) > 0.7) {
                add_up.add(world.getWorld_target());
            }
        }
        Collections.sort(add_up);
        return add_up;
    }

    public static void TTS(String Speech)
    {
        try {
            System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);

            Synthesizer synth = Central.createSynthesizer(desc);
            synth.allocate();
            desc = (SynthesizerModeDesc) synth.getEngineModeDesc();
            Voice voice = new Voice();
            // "business", "casual", "robotic", "breathy"
            voice.setAge(Voice.AGE_TEENAGER);
            voice.setGender(Voice.GENDER_FEMALE);
            voice.setStyle("breathy");
            synth.getSynthesizerProperties().setVoice(voice);
            synth.resume();
            synth.speakPlainText(Speech, null);
            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception ex) {
            ex.printStackTrace();
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


    public static boolean netIsAvailable() {
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

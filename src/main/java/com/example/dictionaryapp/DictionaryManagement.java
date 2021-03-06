package com.example.dictionaryapp;

import javax.speech.Central;
import java.io.*;
import java.util.*;
import java.util.Locale;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.net.*;



public class DictionaryManagement extends Dictionary{

    public static List<String> add_up = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi them
    public static List<String> remove_out = new ArrayList<>(); // list luu cac tu tieng anh truoc va sau khi xoa
    public static String lan1 = "en";
    public static String lan2 = "vi";

    public static List<String> AddWord(String word_target, String word_explain) // them tu va tra ve danh sach tu moi
    {

        Word newWord = new Word(word_target, word_explain);
        words.add(newWord);
        for (Word word : words) {
            add_up.add(word.getWordTarget());

        }
        return  add_up;
    }

    public static List<String> RemoveWordFromDictionary(String word)
    {
        int i;
        for(i = 0; i < words.size() ; i++) {
            if(words.get(i).getWordTarget().equals(word)) {
                words.remove(words.get(i));
            } else {
                remove_out.add(words.get(i).getWordTarget());
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
    public static String DictionaryLookup(String wordToLookUp)
    {
            for (Word word : words) {
                if (word.getWordTarget().equalsIgnoreCase(wordToLookUp)) {
                    return word.getWordExplain();
                }
            }

        return "";
    }

    // viet lai file khi sua tu
    public static void ExportoFile() throws IOException {

        File file = new File("dataos.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        int i =1;
        for (Word newWord : words)
        {
            if (i!=1)
            {
                outputStreamWriter.write("\n");
            }
            outputStreamWriter.write(newWord.getWordTarget());
            outputStreamWriter.write("\n");
            String t = newWord.getWordExplain().replace("\n", "");
            outputStreamWriter.write(t);
            i++;
        }
        outputStreamWriter.flush();
    }

    //h??m ch???nh s???a ngh??a t???//
    public static String Modify(String change_target, String change_explain) throws IOException {
        for(int i = 0; i< words.size(); i++)
        {
            if(words.get(i).getWordTarget().equalsIgnoreCase(change_target))
            {
                Dictionary.modify(i,new Word(change_target, change_explain));
            }
        }
        return change_explain;
    }

    public static Boolean WordExist(String Word) {
        for(Word word : words) {
            if(word.getWordTarget().equalsIgnoreCase(Word)) {
                return true;
            }
        }
        return false;
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
        for (Word word : words) {
            if (word.getWordTarget().toLowerCase().startsWith(wordSearch.toLowerCase())|| similarity(word.getWordTarget(), wordSearch) > 0.7) {
                add_up.add(word.getWordTarget());
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

    public void Change() {
        String temp;
        temp = lan1;
        lan1 = lan2;
        lan2 = temp;
    }
    // 5000 tu 1 ngay 1 link
    public static String Translate(String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbyXC0J_8nBkbpnAT96Oq_ptutFJHlHvZS_HR5Hy4qSsQvHr5Cw/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + lan2 + "&source=" + lan1;
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

    
}

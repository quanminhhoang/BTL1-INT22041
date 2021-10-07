package com.example.dictionaryapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//5000word/day voi url

public class Translator {

    private static String translateEnVi(String text) throws IOException {
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

}
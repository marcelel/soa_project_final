package com.agh.soa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test {
    public static void main(String[] args) throws IOException {
        try {
            doGet();
            doPost();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void doGet() throws Exception {
        URL url = new URL("http://localhost:8080/rest-1.0-SNAPSHOT/soa/category");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        String encoded = Base64.getEncoder().encodeToString(("marcel" + ":" + "marcel").getBytes(StandardCharsets.UTF_8));
        httpURLConnection.setRequestProperty("Authorization", " Basic " + encoded);

        if (httpURLConnection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + httpURLConnection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
        String output;
        System.out.println("All categories:");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        httpURLConnection.disconnect();
    }

    private static void doPost() throws Exception {
        URL url = new URL("http://localhost:8080/rest-1.0-SNAPSHOT/soa/category");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        String encoded = Base64.getEncoder().encodeToString(("marcel" + ":" + "marcel").getBytes(StandardCharsets.UTF_8));
        httpURLConnection.setRequestProperty("Authorization", "Basic " + encoded);

        String input = "{\n" +
                "\t\"name\" : \"restName\",\n" +
                "\t\"categoryId\" : 67,\t\n" +
                "\t\"attributeAmount\" : 300,\n" +
                "\t\"attributeName\" : \"restAttributeName\", \n" +
                "\t\"power\" : 400\n" +
                "}\t\t";
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + httpURLConnection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (httpURLConnection.getInputStream())));

        System.out.println("Element created");

        httpURLConnection.disconnect();
    }

}


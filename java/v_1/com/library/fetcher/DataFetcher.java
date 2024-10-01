
package com.library.fetcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class DataFetcher {

    // Method to fetch data asynchronously using CompletableFuture
    public CompletableFuture<String> fetchData(String url) {
        return CompletableFuture.supplyAsync(() -> {
            validateUrl(url);  // Input validation
            try {
                URL urlObj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");

                int status = conn.getResponseCode();
                if (status != 200) {
                    throw new RuntimeException("Error: " + status + " " + conn.getResponseMessage());
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return content.toString();
            } catch (Exception e) {
                throw new RuntimeException("Error fetching data: " + e.getMessage(), e);
            }
        });
    }

    // Validate URL
    private void validateUrl(String url) {
        if (url == null || !url.startsWith("http")) {
            throw new IllegalArgumentException("Invalid URL provided");
        }
    }
}

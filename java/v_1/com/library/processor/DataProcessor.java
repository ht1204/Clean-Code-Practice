package com.library.processor;

import com.library.parser.DataParser;
import com.library.parser.JsonParser;
import com.library.parser.XmlParser;
import com.library.fetcher.DataFetcher;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DataProcessor {

    private final Map<String, DataParser> parserMap = new HashMap<>();

    // Constructor to initialize supported parsers (OCP)
    public DataProcessor() {
        parserMap.put("json", new JsonParser());
        parserMap.put("xml", new XmlParser());
    }

    // Process the data fetching, parsing, and processing
    public void process(String url, String type) {
        DataFetcher fetcher = new DataFetcher();
        CompletableFuture<String> dataFuture = fetcher.fetchData(url);

        try {
            String response = dataFuture.get();  // Block until data is fetched
            Object parsedData = parseData(response, type);
            processData(parsedData);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    // Parse data using the strategy pattern (OCP)
    private Object parseData(String response, String type) {
        DataParser parser = parserMap.get(type.toLowerCase());
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported data type: " + type);
        }
        return parser.parse(response);
    }

    // Processing the parsed data
    private void processData(Object data) {
        System.out.println("Processing data: " + data.toString());
    }
}

package com.library.main;

import com.library.processor.DataProcessor;

public class Main {
    public static void main(String[] args) {

        /*
			Tested with real API endpoint instead of:
			https://example.com/api
 		*/

        DataProcessor processor = new DataProcessor();
        processor.process("https://jsonplaceholder.typicode.com/posts", "json");
    }
}

package com.library.parser;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser implements DataParser {
    @Override
    public Object parse(String response) {
        // Simulate XML parsing (using DocumentBuilderFactory for XML parsing)
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new java.io.ByteArrayInputStream(response.getBytes()));
            return doc;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse XML: " + e.getMessage(), e);
        }
    }
}

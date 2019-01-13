package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPars extends DefaultHandler {
    private long sum = 0;
    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("entry")) {
            String id = attributes.getValue("href");
            sum += Integer.parseInt(id);
        }
    }

    public void endElement(String uri, String localName, String qName) {
    }

    public long getSum() {
        return sum;
    }
}

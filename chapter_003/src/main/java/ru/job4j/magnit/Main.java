package ru.job4j.magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());
    /**
     * Connect to a database
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        if (new File("C:/sqlite/db/magnit.db").delete()) {
            LOG.info("Old base was deleted.");
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                LOG.info("A new database has been created with driver name " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void logic() throws TransformerException {
        StoreSQL sql = new StoreSQL();
        try {
            sql.generate(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<XmlUsage.Field> store = new ArrayList<>();
        try {
            store = sql.load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StoreXML storeXML = new StoreXML(new File("C:\\sqlite\\input.xml"));
        storeXML.save(store);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(new File("C:\\sqlite\\input.xml"), new File("C:\\sqlite\\output.xml"));

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXPars handler = new SAXPars();
            saxParser.parse(new File("C:\\sqlite\\output.xml"), handler);
            LOG.info("The sum is " + handler.getSum());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws TransformerException {
        createNewDatabase("magnit.db");
        Config config = new Config();
        config.init();
        logic();
    }
}
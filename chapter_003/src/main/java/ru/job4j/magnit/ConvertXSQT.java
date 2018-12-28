package ru.job4j.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {
    /**
     * Метод конвертации XML-файла в другой XML-файл посредством XSLT.
     * @param source какой XML-файл считываем.
     * @param dest в какой XML-файл записываем.
     * @throws TransformerException ля эксэпсьён манифик.
     */
    public void convert(File source, File dest) throws TransformerException {
        String xsl = "<?xml version=\"1.0\"?>\n" +
                "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                "<xsl:template match=\"/\">\n" +
                "<entries>" +
                "   <xsl:for-each select=\"user/values\">\n" +
                "       <entry>" +
                "           <xsl:attribute name=\"href\">" +
                "               <xsl:value-of select=\"value\"/>" +
                "           </xsl:attribute>" +
                "       </entry>\n" +
                "   </xsl:for-each>\n" +
                " </entries>\n" +
                "</xsl:template>\n" +
                "</xsl:stylesheet>\n";
        StringBuilder xml = new StringBuilder();
        try{
            FileInputStream fileInputStream = new FileInputStream(source);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null){
                xml.append(strLine);
                xml.append("\n");
            }
        }catch (IOException e){
            System.out.println("IOError");
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new ByteArrayInputStream(xsl.getBytes())));
        transformer.transform(new StreamSource(new ByteArrayInputStream(xml.toString().getBytes())), new StreamResult(dest));
    }
}

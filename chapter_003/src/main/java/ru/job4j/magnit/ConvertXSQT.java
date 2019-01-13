package ru.job4j.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ConvertXSQT {
    private static final Logger LOG = LogManager.getLogger(ConvertXSQT.class.getName());
    /**
     * Метод конвертации XML-файла в другой XML-файл посредством XSLT.
     * @param source какой XML-файл считываем.
     * @param dest в какой XML-файл записываем.
     * @throws TransformerException ля эксэпсьён манифик.
     */
    public void convert(File source, File dest) throws TransformerException {
        StringBuilder xsl = new StringBuilder();
        try (FileInputStream fis = new FileInputStream("C:\\sqlite\\xsl.xml"); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                xsl.append(str);
                xsl.append("\n");
            }
        } catch (IOException e) {
            LOG.info("IO Error");
        }
        StringBuilder xml = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(source)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                xml.append(strLine);
                xml.append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            LOG.info("IOError");
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new ByteArrayInputStream(xsl.toString().getBytes())));
        transformer.transform(new StreamSource(new ByteArrayInputStream(xml.toString().getBytes())), new StreamResult(dest));
        LOG.info("File " + dest + " was saved.");
    }
}

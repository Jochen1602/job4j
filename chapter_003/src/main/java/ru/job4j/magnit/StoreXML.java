package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class StoreXML {
    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());
    File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<XmlUsage.Field> list) {
        XmlUsage.User user = new XmlUsage.User();
        user.setValues(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.User.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(user, target);
            LOG.info("File " + target + " was saved.");
        } catch (JAXBException e) {
            LOG.error(e.getMessage());
        }
    }
}

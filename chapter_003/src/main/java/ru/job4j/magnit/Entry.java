package ru.job4j.magnit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

public class Entry implements Serializable {
    private Integer field;

    public Integer getField() {
        return field;
    }

    public Entry(Integer field) {
        this.field = field;
    }

    @XmlElement(name = "field")
    public void setField(Integer field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "field=" + field;
    }

    @Override
    public int hashCode() {
        return getField() != null ? getField().hashCode() : 0;
    }
}

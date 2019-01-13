package ru.job4j.sqlru;

import java.util.Objects;

public class Vacancy {
    private String date;
    private String name;
    private String text;
    private String link;

    public Vacancy(String name, String date, String text, String link) {
        this.date = date;
        this.name = name;
        this.text = text;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return  "date='" + date + '\''
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vacancy)) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(getDate(), vacancy.getDate())
                && Objects.equals(getName(), vacancy.getName())
                && Objects.equals(getText(), vacancy.getText())
                && Objects.equals(getLink(), vacancy.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getName(), getText(), getLink());
    }
}

package ru.job4j.trackersql;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void firstTest() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        Item item = new Item("test1", "testDescription", 123L);
        sql.add(item);
        assertThat(sql.findAll().get(0), Is.is(item));
    }
    @Test
    public void secondTest() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test2", "testDescription2", 1234L);
        Item third = new Item("test3", "testDescription3", 12345L);
        sql.add(first);
        sql.add(second);
        sql.add(third);
        String id = sql.findByName("test2").get(0).getId();
        sql.delete(id);
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(third);
        assertThat(sql.findAll(), Is.is(expected));
    }
}

package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name 1", "desc", "y"});
        new StartUI(input, tracker).init();
        tracker.add(new Item("1", "2"));
        assertThat(tracker.findAll().get(0).getName(), is("test name 1"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserAddItemThenDeleteThisItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        tracker.add(new Item("tt nam 2", "11desc11"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test name").size(), is(0));
    }

    @Test
    public void whenUserAddItemThenDeleteAnotherItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        tracker.add(new Item("tt nam 2", "11desc11"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("tt nam 2").size(), is(1));
    }

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }
    @Test
    public void whenUserAdd3ItemsThenShowAllOfThem() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 22", "desc 22"));
        Item item3 = tracker.add(new Item("test name 333", "desc 333"));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        tracker.findAll();
        assertThat(new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add new item. Добавить новую заявку.").append(System.lineSeparator())
                                .append("1. Show all items. Отобразить все заявки.").append(System.lineSeparator())
                                .append("2. Edit the item. Редактировать заявку по ID.").append(System.lineSeparator())
                                .append("3. Delete the item. Удалить заявку по ID.").append(System.lineSeparator())
                                .append("4. Find the item by ID. Найти заявку по ID.").append(System.lineSeparator())
                                .append("5. Find the item by name. Найти заявки по имени.").append(System.lineSeparator())
                                .append("------------ All items --------------").append(System.lineSeparator())
                                .append("ID: ")
                                .append(item1.getId())
                                .append(", Name: ")
                                .append("test name 1")
                                .append(System.lineSeparator())
                                .append("ID: ")
                                .append(item2.getId())
                                .append(", Name: ")
                                .append("test name 22")
                                .append(System.lineSeparator())
                                .append("ID: ")
                                .append(item3.getId())
                                .append(", Name: ")
                                .append("test name 333")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
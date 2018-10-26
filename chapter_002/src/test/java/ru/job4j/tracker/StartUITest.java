package ru.job4j.tracker;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name 1", "desc", "y"});
        new StartUI(input, tracker).init();
        tracker.add(new Item("1", "2"));
        assertThat(tracker.findAll()[0].getName(), is("test name 1"));
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
        assertThat(tracker.findByName("test name").length, is(0));
    }

    @Test
    public void whenUserAddItemThenDeleteAnotherItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        tracker.add(new Item("tt nam 2", "11desc11"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("tt nam 2").length, is(1));
    }

    @Test
    public void whenUserAdd3ItemsThenShowAllOfThem() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 22", "desc 22"));
        Item item3 = tracker.add(new Item("test name 333", "desc 333"));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(new String[] {new StringBuilder().
                append("id=").append(item1.getId()).
                append(", name=").append("test name 1").
                append(", desc=").append("desc 1").
                append(", created=").append(item1.getCreated()).
                append(", comments=").append(item1.getComments()).toString(),
                new StringBuilder().
                append("id=").append(item2.getId()).
                append(", name=").append("test name 22").
                append(", desc=").append("desc 22").
                append(", created=").append(item2.getCreated()).
                append(", comments=").append(item2.getComments()).toString(),
                new StringBuilder().
                append("id=").append(item3.getId()).
                append(", name=").append("test name 333").
                append(", desc=").append("desc 333").
                append(", created=").append(item3.getCreated()).
                append(", comments=").append(item3.getComments()).toString()}
        ));
    }
}
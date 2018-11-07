package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class TrackerTest Тестирование задачи 2. Реализовать класс Tracker
 *@author antontokarev
 *@since 17.10.2018
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test
    public void whenDeleteItemThenTrackerHasNotThisItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test2", "testDescription2", 1234L);
        Item third = new Item("test3", "testDescription3", 12345L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        String id = tracker.findByName("test2").get(0).getId();
        tracker.delete(id);
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(third);
        assertThat(tracker.findAll(), is(expected));
    }
    @Test
    public void findAllOf2Items() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        assertThat(tracker.findAll(), is(expected));
    }
    @Test
    public void findByNameOf3Items() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 12345L);
        tracker.add(third);
        List<Item> expected = new ArrayList<>();
        expected.add(second);
        assertThat(tracker.findByName("test2"), is(expected));
    }
    @Test
    public void findByIdOf2Items() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        String id = tracker.findByName("test2").get(0).getId();
        assertThat(tracker.findById(id), is(second));
    }
}

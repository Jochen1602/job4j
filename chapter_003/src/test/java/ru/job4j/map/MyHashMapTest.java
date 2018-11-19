package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class MyHashMapTest Тестирование задачи 8. Реализовать собственную структуру данных - HashMap[#84128]
 *@author antontokarev
 *@since 19.11.2018
 */
public class MyHashMapTest {
    private MyHashMap<String, String> map = new MyHashMap<>();

    @Before
    public void beforeTest() {
        map.insert("first key", "first");
        map.insert("second key", "second");
        map.insert("third key", "third");
        map.insert("fourth key", "fourth");
        map.insert("fifth key", "fifth");
    }

    @Test
    public void iteratorTest() {
        MyHashMap<String, String>.KeyIterator it = map.new KeyIterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionTest() {
        MyHashMap<String, String>.KeyIterator it = map.new KeyIterator();
        it.next();
        map.insert("sixth key", "sixth");
        it.next();
    }

    @Test
    public void getValues() {
        assertThat(map.get("second key"), is("second"));
        map.insert("2", "changed second");
        assertThat(map.get("2"), is("changed second"));
        assertThat(map.get("first key"), is("first"));
        map.insert("qwerty", "first");
        map.insert("qwertyuiop", "second");
        map.insert("asdfgh", "third");
        map.insert("asdfghjkl", "first");
        map.insert("zxcvbn", "second");
        map.insert("zxcvbnm", "third");
        map.insert("dsfsdfsdf", "first");
        map.insert("fbhfdbdfbdf", "second");
        map.insert("fdgfgdfgdfvd", "third");
        assertThat(map.getLength(), is(16));
        map.insert("dfvdfvfsdasdad", "first first");
        assertThat(map.getLength(), is(32));
        map.insert("fhfhfghfghfghfgh", "second");
        map.insert("hhhhhhhhhhhfgfffffffff", "third");
        map.insert("jjjjjjjjjjjjjjj", "second");
        map.insert("hjkhjgjghjghjghjghjgghhh", "third");
        map.insert("hjkhjgjghjghjjgghhh", "third");
        assertThat(map.get("dfvdfvfsdasdad"), is("first first"));
        assertThat(map.get("fourth key"), is("fourth"));
        assertThat(map.getSize(), is(15));
        map.delete("jjjjjjjjjjjjjjj");
        assertThat(map.getSize(), is(14));
    }
}
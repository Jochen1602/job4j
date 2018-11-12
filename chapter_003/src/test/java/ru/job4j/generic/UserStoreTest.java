package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    @Test
    public void justBigUserTest() {
        UserStore store = new UserStore(3);
        User first = new User("first");
        User second = new User("second");
        User third = new User("third");
        store.add(first);
        store.add(second);
        store.add(third);
        assertThat(store.findById("third"), is(third));
        assertThat(store.replace("secccccond", new User("changed second")), is(false));
        assertThat(store.replace("second", new User("changed second")), is(true));
        assertThat(store.get(1).getId(), is("changed second"));
        assertThat(store.delete("first"), is(true));
        assertThat(store.delete("first"), is(false));
    }
}
package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoleStoreTest {
    @Test
    public void justBigRoleTest() {
        RoleStore store = new RoleStore(3);
        Role first = new Role("first");
        Role second = new Role("second");
        Role third = new Role("third");
        store.add(first);
        store.add(second);
        store.add(third);
        assertThat(store.findById("third"), is(third));
        assertThat(store.replace("secccccond", new Role("changed second")), is(false));
        assertThat(store.replace("second", new Role("changed second")), is(true));
        assertThat(store.get(1).getId(), is("changed second"));
        assertThat(store.delete("first"), is(true));
        assertThat(store.delete("first"), is(false));
    }
}
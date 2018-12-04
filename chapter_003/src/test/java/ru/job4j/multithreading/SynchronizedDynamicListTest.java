package ru.job4j.multithreading;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SynchronizedDynamicListTest Тестирование задачи 3. ThreadSafe динамический список [#84190]
 *@author antontokarev
 *@since 04.12.2018
 */
public class SynchronizedDynamicListTest {
    private class AddElementToList<E> extends Thread {
        SynchronizedDynamicList<E> list;
        E element;

        public AddElementToList(SynchronizedDynamicList<E> list, E element) {
            this.list = list;
            this.element = element;
        }

        @Override
        public synchronized void run() {
           list.add(element);
        }
    }

    private class SetElementInList<E> extends Thread {
        SynchronizedDynamicList<E> list;
        int index;
        E element;

        public SetElementInList(SynchronizedDynamicList<E> list, int index, E element) {
            this.list = list;
            this.index = index;
            this.element = element;
        }

        @Override
        public synchronized void run() {
            list.set(index, element);
        }
    }

    private class DynamicListIteratorNext<E> extends Thread {
        SynchronizedDynamicList<E> list;
        Iterator it;

        public DynamicListIteratorNext(SynchronizedDynamicList<E> list, Iterator it) {
            this.list = list;
            this.it = it;
        }

        @Override
        public void run() {
            it.next();
            it.next();
            it.next();
        }
    }

    @Test
    public void firstTest() throws InterruptedException {
        SynchronizedDynamicList<String> list = new SynchronizedDynamicList<>();
        Thread first = new AddElementToList(list, "first");
        Thread second = new AddElementToList(list, "second");
        Thread third = new AddElementToList(list, "third");
        Thread fourth = new AddElementToList(list, "fourth");
        first.start();
        second.start();
        third.start();
        fourth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        assertThat(list.get(3) != null, is(true));
        assertThat(list.get(4) != null, is(false));
        Thread fifth = new SetElementInList(list, 0, "re first");
        Thread sixth = new SetElementInList(list, 1, "re second");
        Thread seventh = new SetElementInList(list, 2, "re third");
        Thread eighth = new SetElementInList(list, 3, "re fourth");
        fifth.start();
        sixth.start();
        seventh.start();
        eighth.start();
        fifth.join();
        sixth.join();
        seventh.join();
        eighth.join();
        assertThat(list.get(0), is("re first"));
        assertThat(list.get(1), is("re second"));
        assertThat(list.get(2), is("re third"));
        assertThat(list.get(3), is("re fourth"));
    }

    @Test
    public void iteratorTest() throws InterruptedException {
        SynchronizedDynamicList<String> list = new SynchronizedDynamicList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        Iterator<String> it = list.iterator();
        Thread first = new DynamicListIteratorNext(list, it);
        Thread second = new DynamicListIteratorNext(list, it);
        first.start();
        second.start();
        first.join();
        second.join();

    }

}

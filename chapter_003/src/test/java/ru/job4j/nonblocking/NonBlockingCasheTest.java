package ru.job4j.nonblocking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingCasheTest {
    private final NonBlockingCashe store = new NonBlockingCashe();

    class StoreUpdate extends Thread {
        NonBlockingCashe storage;
        int id;
        int value;
        public StoreUpdate(int id, int value) {
            this.id = id;
            this.value = value;
            this.storage = store;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.update(id, value);
            }
        }
    }

    class StraitUpdate extends Thread {
        NonBlockingCashe storage;
        int id;
        int value;
        public StraitUpdate(int id, int value) {
            this.id = id;
            this.value = value;
            this.storage = store;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.getValue(id).setValue(value);
                System.out.println("ver = " + storage.getValue(id).getVersion() + "    value = " + value);
            }
        }
    }

    @Test
    public void concurrentThreadsTest() throws InterruptedException {
        store.add(new Base(1602, 16801));
        store.update(1602, 1602);
        store.update(1602, 3002);
        store.add(new Base(1602, 16801));
        Thread first = new StoreUpdate(1602, 1602);
        Thread second = new StraitUpdate(1602, 3001);
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }
}

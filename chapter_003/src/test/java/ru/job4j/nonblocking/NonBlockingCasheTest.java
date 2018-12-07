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
            storage.update(id, value);
        }
    }

    @Test
    public void concurrentThreadsTest() throws InterruptedException {
        store.add(new Base(1602, 16801));
        Thread first = new StoreUpdate(1602, 1602);
        Thread second = new StoreUpdate(1602, 2061);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(store.getValue(1602).getValue(), is(1602));
        assertThat(store.getValue(1602).getVersion(), is(1));

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

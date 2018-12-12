package ru.job4j.nonblocking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingCasheTest {
    private final NonBlockingCashe store = new NonBlockingCashe();

    @Test
    public void concurrentThreadsTest() throws InterruptedException {
        store.add(new Base(1602, 16801));
        store.update(1602, 1602);
        store.update(1602, 3002);
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

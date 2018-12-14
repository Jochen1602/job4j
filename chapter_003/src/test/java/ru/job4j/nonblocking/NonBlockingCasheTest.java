package ru.job4j.nonblocking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingCasheTest {
    private final NonBlockingCashe store = new NonBlockingCashe();

    @Test
    public void concurrentThreadsTest() {
        store.add(new Base(1602, 16801));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Base value = store.getValue(1602);
                    Base b = new Base(value.getId(), value.getValue());
                    b.setVersion(value.getVersion());
                    store.update(b);
                }
            }
        };
        Thread first = new Thread(runnable);
        Thread second = new Thread(runnable);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

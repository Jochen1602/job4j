package ru.job4j.pools;

import org.junit.Test;

public class ThreadPoolTest {
    @Test
    public void justTest() {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 6; i++) {
            pool.work(() -> System.out.println("is running"));
        }
        pool.shutdown();
        pool.work(() -> System.out.println("tadaaam"));
    }
}

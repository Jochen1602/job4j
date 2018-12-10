package ru.job4j.pools;

import org.junit.Test;

/**
 *class ThreadPoolTest Тестирование задачи 1. Реализовать ThreadPool[#84174]
 *@author antontokarev
 *@since 10.12.2018
 */
public class ThreadPoolTest {
    @Test
    public void justTest() throws Exception {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 1000; i++) {
            pool.work(() -> { });
        }
        Thread.sleep(200);
        pool.shutdown();
    }
}

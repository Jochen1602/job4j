package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class FindALoopTest Тестирование задачи 5.3.4. Задан связанный список. Определить цикличность.[#84106]
 *@author antontokarev
 *@since 14.11.2018
 */
public class FindALoopTest {
    @Test
    public void firstLoopTest() {
        FindALoop.Node first = new FindALoop.Node<>(1);
        FindALoop.Node second = new FindALoop.Node<>(2);
        FindALoop.Node third = new FindALoop.Node<>(3);
        FindALoop.Node fourth = new FindALoop.Node<>(4);
        FindALoop.Node fifth = new FindALoop.Node<>(5);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = first;
        assertThat(FindALoop.hasCycle(first), is(true));
    }

    @Test
    public void secondLoopTest() {
        FindALoop.Node first = new FindALoop.Node<>(1);
        FindALoop.Node second = new FindALoop.Node<>(2);
        FindALoop.Node third = new FindALoop.Node<>(3);
        FindALoop.Node fourth = new FindALoop.Node<>(4);
        FindALoop.Node fifth = new FindALoop.Node<>(5);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second;
        fifth.next = null;
        assertThat(FindALoop.hasCycle(first), is(true));
    }

    @Test
    public void thirdLoopTest() {
        FindALoop.Node first = new FindALoop.Node<>(1);
        FindALoop.Node second = new FindALoop.Node<>(2);
        FindALoop.Node third = new FindALoop.Node<>(3);
        FindALoop.Node fourth = new FindALoop.Node<>(4);
        FindALoop.Node fifth = new FindALoop.Node<>(5);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;
        assertThat(FindALoop.hasCycle(first), is(false));
    }
}

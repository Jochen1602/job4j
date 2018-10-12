package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class BoardTest тестирование задачи 5.3. Построить шахматную доску в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */

public class BoardTest {
    @Test
    public void WhenTheBoardIs3On4() {
        Board board = new Board();
        String result = board.paint(3, 4);
        String ln = System.lineSeparator();

        assertThat(result, is(String.format("X X%s X %sX X%s X %s", ln, ln, ln, ln)));
    }
}

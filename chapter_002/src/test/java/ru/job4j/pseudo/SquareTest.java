package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class SquareTest Тестирование задачи 4. Используя шаблон проектирования - стратегию
 *@author antontokarev
 *@since 25.10.2018
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("++++")
                                .append("+  +")
                                .append("+  +")
                                .append("++++")
                                .toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("  ^  ")
                                .append(" ^^^ ")
                                .append("^^^^^")
                                .toString()
                )
        );
    }
}
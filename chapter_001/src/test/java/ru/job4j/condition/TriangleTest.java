package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class Max тестирование задачи 4.3. Вычисление площади треугольника.
 *@author antontokarev
 *@since 11.10.2018
 */

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {

        Point a = new Point(0, 0);
        Point b = new Point(0, 3);
        Point c = new Point(4, 0);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = 6D;

        assertThat(result, closeTo(expected, 0.01));
    }

    @Test
    public void whenThreePointsLaysOnOneLine() {
        Point a = new Point(5, 0);
        Point b = new Point(5, 3);
        Point c = new Point(5, -2);

        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1D;
        assertThat(result, is(expected));

    }
}

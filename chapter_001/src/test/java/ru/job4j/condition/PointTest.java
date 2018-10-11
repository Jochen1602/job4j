package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/***
 *Class Calculate тестирование задачи 3.4. Расстояние между точками в системе координат.
 *@author antontokarev
 *@since 11.10.2018
 ***/

public class PointTest {
    @Test
    public void distanceBetweenPoint11And45() {
        Point pointA = new Point(4, 5);
        Point pointB = new Point(1, 1);
        double distance = pointA.distanceTo(pointB);
        assertThat(distance, closeTo(5.0, 0.01));
        }
}
package ru.job4j.condition;

/**
 *Class Triangle решение задачи 4.3. Вычисление площади треугольника.
 *@author antontokarev
 *@since 11.10.2018
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Конструктор
     * @param a 1 вершина
     * @param b 2 вершина
     * @param c 3 вершина
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Функция вычисляющая полупериметр треугольника.
     * @param ab длина 1 стороны.
     * @param bc длина 2 стороны.
     * @param ca длина 3 стороны.
     * @return искомый полупериметр.
     */
    public double period(double ab, double bc, double ca) {
        return (ab + bc + ca) / 2.0;
    }

    /**
     * Функция определяет существует ли такой треугольник.
     * @param ab длина 1 стороны.
     * @param bc длина 2 стороны.
     * @param ca длина 3 стороны.
     * @return true если треугольник существует, false в противном случае.
     */
    private boolean exist(double ab, double bc, double ca) {
        return (!(ab == bc + ca)) && (!(bc == ab + ca)) && (!(ca == ab + bc));
    }

    /**
     * Метод вычисляющий площадь треугольника.
     *
     * @return искомую прощадь, если треугольник существует или -1 в противном случае.
     */
    public double area() {
        double result = -1;
        double ab = this.a.distanceTo(this.b);
        double bc = this.b.distanceTo(this.c);
        double ca = this.c.distanceTo(this.a);
        double p = this.period(ab, bc, ca);
        if (this.exist(ab, bc, ca)) {
            result = Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
        }
        return result;
    }
}
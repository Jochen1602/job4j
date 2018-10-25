package ru.job4j.pseudo;

/**class Paint Решение задачи 4. Используя шаблон проектирования - стратегию
 *@author antontokarev
 *@since 25.10.2018
 */
public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        Square square = new Square();
        triangle.draw();
        square.draw();
    }
}
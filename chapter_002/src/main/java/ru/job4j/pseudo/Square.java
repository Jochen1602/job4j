package ru.job4j.pseudo;

/**class Square Решение задачи 4. Используя шаблон проектирования - стратегию
 *@author antontokarev
 *@since 25.10.2018
 */
public class Square implements Shape {
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++");
        pic.append("+  +");
        pic.append("+  +");
        pic.append("++++");
        return pic.toString();
    }
}
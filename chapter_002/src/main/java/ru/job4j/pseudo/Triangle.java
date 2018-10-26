package ru.job4j.pseudo;

/**class Triangle Решение задачи 4. Используя шаблон проектирования - стратегию
 *@author antontokarev
 *@since 25.10.2018
 */
public class Triangle implements Shape {
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("  ^  ").append(System.lineSeparator());
        pic.append(" ^^^ ").append(System.lineSeparator());
        pic.append("^^^^^");
        return pic.toString();
    }
}
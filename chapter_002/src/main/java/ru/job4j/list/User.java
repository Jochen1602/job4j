package ru.job4j.list;

/**class User Решение задачи 2. Написать программу преобразования List в Map.
 *@author antontokarev
 *@since 02.11.2018
 */
public class User {
    private int id;
    private String name;
    private String city;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }
}
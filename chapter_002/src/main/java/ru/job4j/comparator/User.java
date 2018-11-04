package ru.job4j.comparator;

/**
 *class User Решение задачи 1. Организовать сортировку User
 *@author antontokarev
 *@since 04.11.2018
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    @Override
    public int compareTo(User o) {
        return this.getAge() - o.getAge();
    }
}
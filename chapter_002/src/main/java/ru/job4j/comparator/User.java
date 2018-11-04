package ru.job4j.comparator;

/**
 *class User Решение задачи 1. Организовать сортировку User
 *@author antontokarev
 *@since 04.11.2018
 */
public class User implements Comparable<User> {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return this.getAge().compareTo(o.getAge());
    }
}
package ru.job4j.map;

import java.util.Calendar;

/**
 *class User Решение задачи 1. Создать модель User[#84121]
 *@author antontokarev
 *@since 15.11.2018
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public User(String name, int children) {
        this.name = name;
        this.children = children;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildren();
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
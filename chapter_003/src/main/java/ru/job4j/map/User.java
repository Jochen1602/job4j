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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getChildren() != user.getChildren()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        return getBirthday() != null ? getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }
}
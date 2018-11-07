package ru.job4j.department;

/**
 *class Department Решение задачи Отсортировать департаменты
 *@author antontokarev
 *@since 07.11.2018
 */
public class Department implements Comparable<Department> {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }

        Department that = (Department) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public int compareTo(Department o) {
        return this.getName().compareTo(o.getName());
    }
}
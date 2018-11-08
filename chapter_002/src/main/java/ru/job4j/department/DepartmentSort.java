package ru.job4j.department;

import java.util.*;

/**
 *class DepartmentSort Решение задачи Отсортировать департаменты
 *@author antontokarev
 *@since 07.11.2018
 */
public class DepartmentSort {
    /**
     * Метод сортировки подразделений по возрастанию. Также составляется список департаментов,
     * которые при необходимости также добавляются в список.
     * @param list список подразделений.
     * @return отсортированный по возрастанию список подразделений.
     */
    public List<Department> sortUp(List<Department> list) {
        Set<Department> buf = new TreeSet<>(list);
        Set<Department> mainDepartments = new TreeSet<>();
        list.forEach(value -> mainDepartments.add(new Department(value.getName().split("\\\\")[0])));
        buf.addAll(mainDepartments);
        List<Department> result = new ArrayList<>();
        result.addAll(buf);
        return result;
    }

    /**
     * Метод сортировки подразделений по убыванию.
     * @param list список подразделений.
     * @return отсортированный по убыванию список подразделений.
     */
    public List<Department> sortDown(List<Department> list) {
        Set<Department> buf = new TreeSet<>(list);
        Set<Department> mainDepartments = new TreeSet<>();
        list.forEach(value -> mainDepartments.add(new Department(value.getName().split("\\\\")[0])));
        buf.removeAll(mainDepartments);
        List<Department> result = new ArrayList<>();
        List<Department> md = new ArrayList<>();
        md.addAll(mainDepartments);
        result.addAll(buf);
        Collections.reverse(result);
        Collections.reverse(md);
        int j = 0;
        for (int i = 0; i < result.size() + j; i++) {
            if (result.get(i).getName().split("\\\\")[0].equals(md.get(j).getName())) {
                result.add(i, md.get(j));
                if (j == md.size() - 1) {
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}
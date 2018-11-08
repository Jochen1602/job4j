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
     * Метод сортировки подразделений по убыванию. Использует метод sortUp.
     * @param list список подразделений.
     * @return отсортированный по убыванию список подразделений.
     */
    public List<Department> sortDown(List<Department> list) {
        List<Department> result = sortUp(list);
        Collections.reverse(result);
        return result;
    }
}
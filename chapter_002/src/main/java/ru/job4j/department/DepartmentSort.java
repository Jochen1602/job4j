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
        for (Department d : buf) {
            mainDepartments.add(new Department(d.getName().split("\\\\")[0]));
        }
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
        for (Department d : result) {
            System.out.println(d.getName());
        }
        return result;
    }
}
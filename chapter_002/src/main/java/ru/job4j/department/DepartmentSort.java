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
        List<Department> result = new ArrayList<>();
        Set<Department> set = new TreeSet<>(list);
        list.forEach(value -> set.add(new Department(value.getName().split("\\\\")[0])));
        Set<Department> descendingData = new TreeSet<>((o1, o2) -> {
            int minLen = Math.min(o1.getName().length(), o2.getName().length());
            int res = -o1.getName().substring(0, minLen).compareTo(o2.getName().substring(0, minLen));
            if (res == 0) {
                res = Integer.compare(o1.getName().length(), o2.getName().length());
            }
            return res;
        });
        descendingData.addAll(set);
        result.addAll(descendingData);
        return result;
    }
}
package ru.job4j.department;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *class DepartmentTest Тестирование задачи Отсортировать департаменты
 *@author antontokarev
 *@since 07.11.2018
 */
public class DepartmentTest {
    @Test
    public void firstTest() {
        DepartmentSort listOfDepartments = new DepartmentSort();
        ArrayList<Department> list = new ArrayList<>();
        Department element1 = new Department("K1\\SK1\\SSK2");
        Department element2 = new Department("K2\\SK1\\SSK1");
        Department element3 = new Department("K1\\SK2");
        Department element4 = new Department("K1\\SK1");
        Department element5 = new Department("K2\\SK1\\SSK2");
        Department element6 = new Department("K1\\SK1\\SSK1");
        Department element7 = new Department("K2");
        Department element8 = new Department("K1");
        list.add(element1);
        list.add(element2);
        list.add(element3);
        list.add(element4);
        list.add(element5);
        list.add(element6);
        List<Department> result = listOfDepartments.sortUp(list);
        List<Department> expected = new ArrayList<>();
        expected.add(element8);
        expected.add(element4);
        expected.add(element6);
        expected.add(element1);
        expected.add(element3);
        expected.add(element7);
        expected.add(element2);
        expected.add(element5);
        assertThat(result, is(expected));
    }
    @Test
    public void secondTest() {
        DepartmentSort listOfDepartments = new DepartmentSort();
        ArrayList<Department> list = new ArrayList<>();
        Department element1 = new Department("K1\\SK1\\SSK2");
        Department element2 = new Department("K2\\SK1\\SSK1");
        Department element3 = new Department("K1\\SK2");
        Department element4 = new Department("K1\\SK1");
        Department element5 = new Department("K2\\SK1\\SSK2");
        Department element6 = new Department("K3\\SK1\\SSK1");
        Department element7 = new Department("K2");
        Department element8 = new Department("K3");
        Department element9 = new Department("K1");
        list.add(element1);
        list.add(element2);
        list.add(element3);
        list.add(element4);
        list.add(element5);
        list.add(element6);
        List<Department> result = listOfDepartments.sortUp(list);
        List<Department> expected = new ArrayList<>();
        expected.add(element9);
        expected.add(element4);
        expected.add(element1);
        expected.add(element3);
        expected.add(element7);
        expected.add(element2);
        expected.add(element5);
        expected.add(element8);
        expected.add(element6);
        assertThat(result, is(expected));
    }
    @Test
    public void firstTestDown() {
        DepartmentSort listOfDepartments = new DepartmentSort();
        ArrayList<Department> list = new ArrayList<>();
        Department element1 = new Department("K1\\SK1\\SSK2");
        Department element2 = new Department("K2\\SK1\\SSK1");
        Department element3 = new Department("K1\\SK2");
        Department element4 = new Department("K1\\SK1");
        Department element5 = new Department("K2\\SK1\\SSK2");
        Department element6 = new Department("K3\\SK1\\SSK1");
        Department element7 = new Department("K2");
        Department element8 = new Department("K3");
        Department element9 = new Department("K1");
        list.add(element1);
        list.add(element2);
        list.add(element3);
        list.add(element4);
        list.add(element5);
        list.add(element6);
        list.add(element7);
        List<Department> result = listOfDepartments.sortDown(list);
        List<Department> expected = new ArrayList<>();
        expected.add(element6);
        expected.add(element8);
        expected.add(element5);
        expected.add(element2);
        expected.add(element7);
        expected.add(element3);
        expected.add(element1);
        expected.add(element4);
        expected.add(element9);
        assertThat(result, is(expected));
    }
}
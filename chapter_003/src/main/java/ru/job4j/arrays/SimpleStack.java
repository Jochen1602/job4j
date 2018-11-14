package ru.job4j.arrays;

/**
 *class SimpleStack Решение задачи 5.3.3. Используя контейнер на базе связанного списка создать контейнер Stack[#84102]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleStack<E> {
    MyLinkedList<E> stack = new MyLinkedList<>();

    /**
     * Метод выводит последний элемент стека и затем удаляет его.
     * @return последний элемент.
     */
    public E poll() {
        return stack.deleteLast();
    }

    /**
     * Метод вставляет указанный элемент в стек.
     * @param value элемент для вставки.
     */
    public void push(E value) {
        stack.add(value);
    }
}
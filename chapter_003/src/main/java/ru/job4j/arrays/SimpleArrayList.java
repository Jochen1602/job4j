package ru.job4j.arrays;

/**
 *class SimpleArrayList Решение задачи 5.3.0 Создать метод delete для односвязного списка[#84101]
 *Мы храним размер списка и ссылку на первый элемент.
 *@author antontokarev
 *@since 13.11.2018
 */
public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Статический класс для хранения данных.
     * @param <E> дженерик.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    /**
     * Метод добавления нового элемента в начало списка.
     * @param data данные добавляемого элемента.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаления элемента из начала списка.
     * @return удалённый элемент.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = result.next;
        this.size--;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index индекс.
     * @return найденный элемент.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод возвращает размер списка.
     * @return количество элементов.
     */
    public int getSize() {
        return this.size;
    }
}
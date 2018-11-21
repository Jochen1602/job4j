package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *class Node Решение задачи 1. Создать элементарную структуру дерева[#84134]
 *@author antontokarev
 *@since 21.11.2018
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }

    public E getValue() {
        return value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
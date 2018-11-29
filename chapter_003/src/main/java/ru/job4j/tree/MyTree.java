package ru.job4j.tree;

import java.util.*;

/**
 *class MyTree Решение задачи 1. Создать элементарную структуру дерева[#84134]
 *@author antontokarev
 *@since 21.11.2018
 */
public class MyTree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node root;
    private int modCount = 0;

    /**
     * Конструктор. Ставит в корень входящий элемент.
     * @param e элемент для вставки.
     */
    public MyTree(E e) {
        this.root = new Node(e);
    }

    /**
     * Метод добавления потомка к родителю.
     * @param parent родитель.
     * @param child потомок.
     * @return true если элемент был добавлен, false если нет.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (findBy(parent) != null) {
            findBy(parent).get().add(new Node<>(child));
            result = true;
            modCount++;
        }
        return result;
    }

    /**
     * Метод поиска элемента дерева по значению.
     * @param value значение, по которому проходит поиск.
     * @return искомый элемент, если таковой найден.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод проверяет, бинарное ли наше дерево.
     * @return true если бинарное, false если нет.
     */
    public boolean isBinary() {
        return root.isBinary();
    }

    /**
     * Итератор по нашему дереву.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        final int expectedModCount = modCount;
        return new Iterator<E>() {
            private Node<E> current;
            private Queue<Node<E>> data = new LinkedList<>();

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                if (data.size() == 0) {
                    data.offer(root);
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                current = data.poll();
                if (current != null) {
                    for (Node<E> e : current.leaves()) {
                        data.offer(e);
                    }
                }
                return current.getValue();
            }
        };
    }
}
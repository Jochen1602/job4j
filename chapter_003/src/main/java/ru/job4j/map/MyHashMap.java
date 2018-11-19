package ru.job4j.map;

import java.util.*;

/**
 *class MyHashMap Решение задачи 8. Реализовать собственную структуру данных - HashMap[#84128]
 *@author antontokarev
 *@since 19.11.2018
 */
public class MyHashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 16;
    private Node[] table;
    private int size = 0;
    private int modCount = 0;

    /**
     * Класс для хранения содержания Map.
     * @param <K> дженерик ключа.
     * @param <V> дженерик значения.
     */
    static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public String toString() {
            return key.toString() + " ... " + value.toString();
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    /**
     * Метод, вычисляющий хэш-код ключа при помощи метода hashCode().
     * @param key входной ключ.
     * @return значение хэш-кода.
     */
    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    /**
     * Дефолтный конструктор массива на 16 элементов Node.
     */
    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * Метод возвращает количество элементов Map.
     * @return количество элементов.
     */
    public int getSize() {
        return size;
    }

    /**
     * Метод возвращает размер Map.
     * @return размер Map.
     */
    public int getLength() {
        return this.table.length;
    }

    /**
     * Проверка на модифицированность Map.
     * @return сколько было модификаций на данный момент.
     */
    public int getModCount() {
        return modCount;
    }

    /**
     * Метод вычисляет номер ячейки, куда поместить элемент.
     * @param key ключ.
     * @param capacity размер массива.
     * @return номер ячейки.
     */
    private int indexFor(int key, int capacity) {
        return Math.abs(key % capacity);
    }

    /**
     * Метод увеличивает размер массива.
     * @param newSize новый размер массива.
     */
    private void resize(int newSize) {
        Node<K, V>[] newTable = new Node[this.table.length * 2];
        for (Node<K, V> i : table) {
            if (i != null) {
                newTable[indexFor(hash(i.getKey()), newSize)] = i;
            }
        }
        table = newTable;
        this.modCount++;
    }

    /**
     * Метод добавления элементов в Map.
     * @param key ключ элемента.
     * @param value значение элемента.
     * @return true если объект был успешно вставлен, false иначе.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (this.table[indexFor(hash(key), this.table.length)] == null) {
            this.table[indexFor(hash(key), this.table.length)] = new Node(key, value);
            result = true;
            this.size++;
            this.modCount++;
        }
        if (size >= 0.75 * this.table.length) {

            resize(2 * this.table.length);
        }
        return result;
    }

    /**
     * Метод, возвращяющий значение элемента по ключу.
     * @param key ключ.
     * @return значение, соответствующее данному ключу.
     */
    public V get(K key) {
        V result = null;
        if (this.table[indexFor(hash(key), this.table.length)] != null) {
            result = (V) this.table[indexFor(hash(key), this.table.length)].getValue();
        }
        return result;
    }

    /**
     * Метод удаления элементов из Map.
     * @param key ключ.
     * @return true если элемент был удалён из Map, false иначе.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (this.table[indexFor(hash(key), this.table.length)] != null) {
            result = true;
            this.table[indexFor(hash(key), this.table.length)] = null;
            size--;
            modCount--;
        }
        return result;
    }

    /**
     * Итератор по нашей таблице.
     */
    class KeyIterator implements Iterator<K> {
        Node<K, V> next;
        int expectedModCount;
        int index;

        /**
         * Конструктор итератора. Находит первый ненулевой элемент.
         */
        KeyIterator() {
            expectedModCount = modCount;
            Node<K, V>[] t = table;
            next = null;
            index = 0;
            if (t != null && size > 0) {
                do {
                    next = t[index++];
                } while (index < t.length && next == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        public K next() {
            Node<K, V>[] t = table;
            Node<K, V> e = next;
            if (getModCount() != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            next = table[index++ % table.length];
            if ((next == null && t != null)) {
                while (index < t.length - 1 && next == null) {
                    next = t[index++];
                }
            }
            return e.getKey();
        }
    }
}
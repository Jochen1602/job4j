package ru.job4j.arrays;

/**
 *class SimpleQueue Решение задачи 5.3.3.1 Очередь на двух стеках[#84105]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleQueue<E> {
    SimpleStack<E> firstStack = new SimpleStack<>();
    SimpleStack<E> secondStack = new SimpleStack<>();

    /**
     * Метод выводит последний элемент очереди и затем удаляет его.
     * @return последний элемент.
     */
    public E poll() {
        E result;
        if (secondStack.isEmpty()) {
            E element;
            element = firstStack.poll();
            while (element != null) {
                secondStack.push(element);
                element = firstStack.poll();
            }
        }
        result = secondStack.poll();
        return result;
    }

    /**
     * Метод вставляет указанный элемент в очередь.
     * @param value элемент для вставки.
     */
    public void push(E value) {
        firstStack.push(value);
    }
}
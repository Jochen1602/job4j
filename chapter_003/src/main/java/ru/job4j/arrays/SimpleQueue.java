package ru.job4j.arrays;

/**
 *class SimpleQueue Решение задачи 5.3.3.1 Очередь на двух стеках[#84105]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleQueue<E> {
    SimpleStack<E> firstStack = new SimpleStack<>();
    SimpleStack<E> secondStack = new SimpleStack<>();

    public E poll() {
        E result;

        if (secondStack.isEmpty()) {
            E element;
            System.out.println("second stack is empty");
            element = firstStack.poll();
            while (element != null) {
                secondStack.push(element);
                System.out.println("added in second stack " + element);
                element = firstStack.poll();
                System.out.println("" + element);
            }
        }
        result = secondStack.poll();
        System.out.println("result is " + result);
        return result;
    }

    public void push(E value) {
        firstStack.push(value);
        System.out.println("added in first stack " + value);
    }
}
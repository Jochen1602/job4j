package ru.job4j.arrays;

/**
 *class FindALoop Решение задачи 5.3.4. Задан связанный список. Определить цикличность.[#84106]
 *@author antontokarev
 *@since 14.11.2018
 */
public class FindALoop {
    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * Так как мы не можем по условию задачи использовать списки,
     * а нам необходимо сравнивать попарно все элементы из итерации
     * по next, значит нам необходимо как-то изменять и образец для
     * сравнения. Главное чтобы тогда, когда мы попадём в петлю, они
     * нашлись на любой итерации. Для этого необходимо по-разному
     * сдвигать итерации в обоих сравниваемых элементах.
     * @param first первый элемент.
     * @return true если петля нашлась, false если нет.
     */
    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node check = first;
        Node element = first.next;
        while (check != null && element != null && !result) {
            if (check.equals(element)) {
                result = true;
            }
            check = check.next;
            element = element.next.next;
        }
        return result;
    }
}
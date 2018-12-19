package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class Converter Решение задачи 5.1.4. Создать convert(Iterator<Iterator>)
 *@author antontokarev
 *@since 11.11.2018
 */
public class Converter {
    /**
     * Метод по преобразованию итератора итераторов в обычный итератор.
     * @param it итератор итераторов - масло масляное - смертельный ужас - сокрушительный кулак.
     * @return итератор по результатам работы итератора итераторов.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> internalIterator = it.next();

            /**
             * Метод проверяет, есть ли следующий элемент у итератора итераторов и что
             * нет следующего элемента у внутреннего итератора - тогда он перескакивает
             * на следующий элемент итератора итераторов.
             * @return true если есть следующий элемент у внутреннего итератора, false иначе.
             */
            @Override
            public boolean hasNext() {
                if (it.hasNext() && !internalIterator.hasNext()) {
                    internalIterator = it.next();
                }
                return internalIterator.hasNext();
            }

            /**
             * Кидает исключение, если следующего элемента нет, возвращает следующий элемент
             * по внутреннему итератору.
             * @return следующий элемент.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return internalIterator.next();
                }
            }
        };
    }
}
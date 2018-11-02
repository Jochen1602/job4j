package ru.job4j.search;

import java.util.LinkedList;

/**class PriorityQueue Решение задачи 2. Очередь с приоритетом на LinkedList
 *@author antontokarev
 *@since 01.11.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставки задания в наш список по порядку приоритета. Сначала мы сравниваем приоритет вставляемого
     * задания с приоритетом первого, а потом и последнего элемента списка. Если приоритет нового задания не больше
     * всех приоритетов из списка и не меньше, то ищем место, куда его вставить.
     * @param task задание, что необходимо вставить.
     */
    public void put(Task task) {
        if (tasks.size() == 0) {
            tasks.add(task);
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            if (task.getPriority() <= this.tasks.get(i).getPriority()) {
                tasks.add(i, task);
                break;
            }
        }
    }

    /**
     * Метод вызова самого срочного задания с его удалением из списка
     * @return задание с самым высоким приоритетом
     */
    public Task take() {
        return this.tasks.poll();
    }
}
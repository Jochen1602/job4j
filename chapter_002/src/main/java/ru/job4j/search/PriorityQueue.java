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
        int index = 0;
        if (this.tasks.size() != 0) {
            if (task.getPriority() <= this.tasks.get(0).getPriority()) {
                index = 0;
            }
            if (task.getPriority() >= this.tasks.getLast().getPriority()) {
                index = tasks.size();
            } else {
                for (int i = 0; i < this.tasks.size() - 1; i++) {
                    if (task.getPriority() >= this.tasks.get(i).getPriority() && task.getPriority() <= this.tasks.get(i + 1).getPriority()) {
                        index = i + 1;
                    }
                }
            }
        }
        tasks.add(index, task);
    }

    /**
     * Метод вызова самого срочного задания с его удалением из списка
     * @return задание с самым высоким приоритетом
     */
    public Task take() {
        return this.tasks.poll();
    }
}
package ru.job4j.search;

/**class Task Решение задачи 2. Очередь с приоритетом на LinkedList
 *@author antontokarev
 *@since 01.11.2018
 */
public class Task {
    private String desc;
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
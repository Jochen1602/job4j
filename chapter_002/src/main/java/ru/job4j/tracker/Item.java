package ru.job4j.tracker;

import java.util.Arrays;

/**class Item Решение задачи 2. Реализовать класс Tracker.
 *@author antontokarev
 *@since 14.10.2018
 */
public class Item {
    String id;
    String name;
    String desc;
    long created;
    String[] comments;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public long getCreated() {
        return created;
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Item(String id, String name, String desc, long created, String[] comments) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", desc=" + desc + ", created=" + created + ", comments=" + Arrays.toString(comments);
    }
}
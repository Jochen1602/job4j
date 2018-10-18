package ru.job4j.tracker;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
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

    public String[] getComments() {
        return comments;
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }
}

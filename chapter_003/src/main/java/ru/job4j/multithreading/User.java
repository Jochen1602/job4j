package ru.job4j.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {
    @GuardedBy("this")
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        synchronized (this) {
            return id;
        }
    }

    public int getAmount() {
        synchronized (this) {
            return amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (getId() != user.getId()) {
            return false;
        }
        return getAmount() == user.getAmount();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getAmount();
        return result;
    }
}

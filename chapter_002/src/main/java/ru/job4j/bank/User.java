package ru.job4j.bank;

/**
 *class User Решение задачи Банковские переводы.
 *@author antontokarev
 *@since 05.11.2018
 */
public class User {
    private String name;
    private String passport;

    public User() {
    }

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
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

        return (getName() != null ? getName().equals(user.getName()) : user.getName() == null) && (getPassport() != null ? getPassport().equals(user.getPassport()) : user.getPassport() == null);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPassport() != null ? getPassport().hashCode() : 0);
        return result;
    }
}
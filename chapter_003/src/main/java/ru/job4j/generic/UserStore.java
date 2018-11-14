package ru.job4j.generic;

public class UserStore extends AbstractStore<User> {
    public UserStore(int size) {
        super(size);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public User findById(String id) {
        return super.findById(id);
    }

    @Override
    public User get(int index) {
        return super.get(index);
    }
}
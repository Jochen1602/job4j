package ru.job4j.generic;

public class RoleStore extends AbstractStore<Role> {
    public RoleStore(int size) {
        super(size);
    }

    @Override
    public void add(Base model) {
        super.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        return super.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public Role findById(String id) {
        return super.findById(id);
    }

    @Override
    public Role get(int index) {
        return super.get(index);
    }
}
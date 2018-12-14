package ru.job4j.nonblocking;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class NonBlockingCashe {
    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    protected Base getValue(int id) {
        return this.map.get(id);
    }

    protected int getSize() {
        return this.map.size();
    }

    protected void add(Base model) {
        this.map.putIfAbsent(model.getId(), model);
    }

    /**
     * Если такой элемент есть в мапе, то по его ключу ремапим мапу так, что:
     * если версия аргумента и найденного в мапе элемента с данным id совпали, то
     * найденному элементу присваиваем значение аргумента. Если версии не совпали -
     * кидаем рукописный эксепшен.
     * @param base аргумент.
     */
    protected void update(Base base) {
        this.map.computeIfPresent(base.getId(), (Integer id, Base base1) -> {
            if (base1.getVersion() == base.getVersion()) {
                base1.setValue(base.getValue());
            } else {
                throw new OptimisticException();
            }
        return base1;
        });
    }

    protected void delete(Base model) {
        try {
            this.map.remove(model);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}

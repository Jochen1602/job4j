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

    protected void update(int id, int value) {
        this.map.computeIfPresent(id, new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer id, Base base) {
                final int ver = map.get(id).getVersion();
                if (base.getVersion() == ver) {
                    base.setValue(value);
                    System.out.println("ver = " + ver + "    value = " + value);
                } else {
                    throw new OptimisticException();
                }
            return base;
            }
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

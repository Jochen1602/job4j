package ru.job4j.nonblocking;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class NonBlockingCashe {
    @GuardedBy("this")
    private volatile ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    protected synchronized Base getValue(int id) {
        return this.map.get(id);
    }

    protected synchronized int getSize() {
        return this.map.size();
    }

    protected synchronized void add(Base model) {
        this.map.putIfAbsent(model.getId(), model);
    }

    protected synchronized void update(int id, int value) {
        final int ver = map.get(id).getVersion();
        this.map.computeIfPresent(id, new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer integer, Base base) {
                if (base.getVersion() == ver) {
                    //System.out.println("ver = " + ver + "    value = " + value);
                    base.setValue(value);
                } else {
                    throw new OptimisticException();
                }
            return base;
            }
        });
    }

    protected synchronized void delete(Base model) {
        try {
            this.map.remove(model);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}

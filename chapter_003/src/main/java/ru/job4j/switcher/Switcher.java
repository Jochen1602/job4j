package ru.job4j.switcher;

public class Switcher {
    private String store = "";

    public synchronized void addChar(int i) {
        String c = Integer.toString(i);
        store = store.concat(c);
    }

    public synchronized String getStore() {
        return store;
    }
}

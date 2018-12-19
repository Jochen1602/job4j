package ru.job4j.guaranteed_deadlock;

public class Test
{
    /**
     * Зациклим поток рекурсивным вызовом в методе себя самого.
     * И будем ждать джойн. Пока не отключат свет.
     */
    public static void deadlock(int i) {
        try {
            Thread t;
            final int j = ++i;
            t = new Thread(() -> deadlock(j));
            t.start();
            System.out.println("Started thread № " + j);
            t.join();
            System.out.println("If you see it, you're T2000");
        } catch (Exception e) {
            System.out.println("Just little exception");
        }
    }

    public static void main(String[] args) {
        int i = 0;
        deadlock(i);
    }
}

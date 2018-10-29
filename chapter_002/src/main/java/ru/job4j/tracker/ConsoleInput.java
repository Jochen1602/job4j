package ru.job4j.tracker;

import java.util.Scanner;

/**class ConsoleInput Решение задачи 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения.
 *@author antontokarev
 *@since 18.10.2018
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public int ask(String question, int[] range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu.");
        }
    }
}
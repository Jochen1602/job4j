package ru.job4j.tracker;

import java.util.List;

public class  StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        List<Integer> range = menu.getRanges();
        do {
            menu.show(StartUI::print);
            menu.select(input.ask("Select: ", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

    /**
     * Метод печати списка действий пользователя при помощи лямбда-функции.
     * @param list список действий пользователя.
     */
    private static void print(List<UserAction> list) {
        list.forEach((a) -> System.out.println(a.info()));
    }

    /**
     * Запуск программы.
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, new Tracker()).init();
    }
}
package ru.job4j.tracker;

import java.util.List;

public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
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
            menu.show();
            menu.select(input.ask("Select: ", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
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
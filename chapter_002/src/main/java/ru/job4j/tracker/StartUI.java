package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    static final String ADD = "0";
    /**
     * Константа меню для отображения всех заявок.
     */
    static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    static final String DEL = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    static final String FINDID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select: "));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод отображающий все заявки в хранилище.
     */
    private void showItems() {
        System.out.println("-------------- Все созданные заявки ---------------");
        for (Item i : tracker.findAll()) {
            System.out.println(i.toString());
        }
    }

    /**
     * Метод реализует редактирование заявки в.
     */
    private void editItem() {
        System.out.println("------------- Редактирование заявки ---------------");
        String id = this.input.ask("Введите ID заявки, которую хотите отредактировать :");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.replace(id, item);
        System.out.println("----------- Заявка с Id : " + id + " изменена ----------");
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void delItem() {
        System.out.println("---------------- Удаление заявки ------------------");
        String id = this.input.ask("Введите ID заявки, которую хотите удалить :");
        this.tracker.delete(id);
        System.out.println("------------ Заявка с Id : " + id + " удалена -----------");
    }

    /**
     * Метод реализует поиск заявок по ID.
     */
    private void findItemId() {
        System.out.println("---------------- Поиск заявки по ID ------------------");
        String id = this.input.ask("Введите ID заявки, которую хотите найти :");
        Item i = this.tracker.findById(id);
        System.out.println("-------------- Заявка с Id : " + id + " -------------");
        System.out.println(i.toString());
    }

    /**
     * Метод реализует поиск заявок по имени.
     */
    private void findItemName() {
        System.out.println("-------------- Поиск заявки по имени -----------------");
        String name = this.input.ask("Введите имя заявки, которую хотите найти :");
        System.out.println("-------------- Заявка с именем : " + name + " -------------");
        for (Item i : tracker.findByName(name)) {
            System.out.println(i.toString());
        }
    }

    /**
     * Метод отображения меню программы.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select: ");
    }

    /**
     * Запуск программы.
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
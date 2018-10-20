package ru.job4j.tracker;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DEL = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DEL.equals(answer)) {
                this.delItem();
            } else if (FINDID.equals(answer)) {
                this.findItemId();
            } else if (FINDNAME.equals(answer)) {
                this.findItemName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Incorrect data.");
                this.showMenu();
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void showItems() {
        System.out.println("-------------- Все созданные заявки ---------------");
        for (Item i : tracker.findAll()) {
            System.out.println(i.getId() + " " + i.getName() + " " + i.getDesc() + " " + i.getCreated() + " " + i.getComments());
        }
    }

    private void editItem() {
        System.out.println("------------- Редактирование заявки ---------------");
        String id = this.input.ask("Введите ID заявки, которую хотите отредактировать :");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.replace(id, item);
        System.out.println("----------- Заявка с Id : " +id + " изменена ----------");
    }

    private void delItem() {
        System.out.println("---------------- Удаление заявки ------------------");
        String id = this.input.ask("Введите ID заявки, которую хотите удалить :");
        this.tracker.delete(id);
        System.out.println("------------ Заявка с Id : " +id + " удалена -----------");
    }

    private void findItemId() {
        System.out.println("---------------- Поиск заявки по ID ------------------");
        String id = this.input.ask("Введите ID заявки, которую хотите найти :");
        Item i = this.tracker.findById(id);
        System.out.println("-------------- Заявка с Id : " +id + " -------------");
        System.out.println(i.getId() + " " + i.getName() + " " + i.getDesc() + " " + i.getCreated() + " " + i.getComments());
    }

    private void findItemName() {
        System.out.println("-------------- Поиск заявки по имени -----------------");
        String name = this.input.ask("Введите имя заявки, которую хотите найти :");
        System.out.println("-------------- Заявка с именем : " +name + " -------------");
        for (Item i : tracker.findByName(name)) {
            System.out.println(i.getId() + " " + i.getName() + " " + i.getDesc() + " " + i.getCreated() + " " + i.getComments());
        }
    }

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
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
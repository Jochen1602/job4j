package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Метод, заполняющий меню пунктами, соответствующими внутренним классам
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new item. Добавить новую заявку."));
        this.actions.add(new ShowItems(1, "Show all items. Отобразить все заявки."));
        this.actions.add(new MenuTracker.EditItem(2, "Edit the item. Редактировать заявку по ID."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete the item. Удалить заявку по ID."));
        this.actions.add(new FindItemById(4, "Find the item by ID. Найти заявку по ID."));
        this.actions.add(new FindItemsByName(5, "Find the item by name. Найти заявки по имени."));
    }

    /**
     * Конструктор
     * @param input каким образом реализован интерфейс ввода данных.
     * @param tracker наше хранилище заявок.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Сколько у нас пунктов меню.
     * @return количество пунктов меню.
     */
    public int getActionsLength() {
        return this.actions.size();
    }


    public int[] getRanges() {
        int[] ranges = new int[getActionsLength()];
        for (int i = 0; i < getActionsLength(); i++) {
            ranges[i] = i;
        }
        return ranges;
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * class AddItem внутренний класс для добавления новых элементов
     */
    public static class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, enter item name:");
            String desc = input.ask("Please, enter item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New Item with Id: " + item.getId());
            System.out.println("------------ New Item with Name: " + item.getName());
            System.out.println("------------ New Item with Description: " + item.getDesc());
        }
    }

    /**
     * class ShowItems внутренний класс отображающий все элементы
     */
    public class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().length == 0) {
                System.out.println("Tracker is empty.");
                return;
            }
            System.out.println("------------ All items --------------");
            for (Item i : tracker.findAll()) {
                if (i != null) {
                    System.out.println("ID: " + i.getId() + ", Name: " + i.getName());
                }
            }
        }
}

    /**
     * class EditItem внутренний класс редактирования заявки
     */
    public class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Edit the item --------------");
            String id = input.ask("Enter the ID: ");
            if (tracker.findById(id) != null) {
                String name = input.ask("Enter new name: ");
                String desc = input.ask("Enter new description: ");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
            } else {
                System.out.println("No such tasks.");
            }
        }
    }

    /**
     * class DeleteItem внутренний класс удаления заявки
     */
    public class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Delete the item --------------");
            String id = input.ask("Enter the ID: ");
            if (tracker.findById(id) != null) {
                if (tracker.delete(id)) {
                    System.out.println("Done.");
                }
            } else {
                System.out.println("No tasks with that ID.");
            }
        }
    }

    /**
     * class FindItemById внутренний класс поиска заявки по ID
     */
    public class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Find the item by ID --------------");
            String id = input.ask("Enter the ID: ");
            Item i = tracker.findById(id);
            if (i != null) {
                System.out.println("ID: " + i.getId() + ", Name: " + i.getName());
            } else {
                System.out.println("No tasks with that ID.");
            }
        }
    }
    /**
     * class FindItemByName внутренний класс поиска заявки по ID
     */
    public class FindItemsByName extends BaseAction {
        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Find the item by name --------------");
            String name = input.ask("Enter the name: ");
            Item[] item = tracker.findByName(name);
            if (item.length != 0) {
                for (Item i : item) {
                    System.out.println("ID: " + i.getId() + ", Name: " + i.getName());
                }
            } else {
                System.out.println("No such tasks.");
            }
        }
    }
}
package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**class Tracker Решение задачи 2. Реализовать класс Tracker.
 *@author antontokarev
 *@since 14.10.2018
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод, реализующий редактирование заявок
     * @param id номер заявки для редактирования
     * @param item информация по заявке, что необходимо записать
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
            }
        }
    }

    /**
     * Метод, реализующий удаление заявок
     * @param id номер заявки, что необходимо удалить
     */
    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position - i);
                this.items[position] = null;
                break;
            }
        }
    }

    /**
     * Метод поиска всех заявок
     * @return массив из всех заявок
     */
    public Item[] findAll() {

        int counter = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null) {
                counter++;
            }
        }
        Item[] all = new Item[counter];
        for (int i = 0; i < counter; i++) {
                all[i] = this.items[i];
        }
        return all;
    }

    /**
     * Метод получения списка заявок по имени.
     * @param key ключ поиска
     * @return список найденных заявок
     */
    public Item[] findByName(String key) {
        int counter = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                counter++;
            }
        }
        Item[] found = new Item[counter];
        int j = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                found[j++] = this.items[i];
            }
        }
        return found;
    }

    /**
     * метод поиска заявки по id
     * @param id ключ поиска
     * @return заявка по данному ключу
     */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        int num = (int) (100000.0 * Math.random());
        return simpleDateFormat.format(date) + String.valueOf(num);
    }
}
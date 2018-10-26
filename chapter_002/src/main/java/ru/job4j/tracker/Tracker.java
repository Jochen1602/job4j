package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                item.setId(id);
                this.items[i] = item;
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод, реализующий удаление заявок
     * @param id номер заявки, что необходимо удалить
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position - i);
                this.items[position] = null;
                position--;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод поиска всех заявок
     * @return массив из всех заявок
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }

    /**
     * Метод получения списка заявок по имени.
     * @param key ключ поиска
     * @return список найденных заявок
     */
    public Item[] findByName(String key) {
        int counter = 0;
        Item[] found = new Item[100];
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                found[counter] = this.items[i];
                counter++;
            }
        }
        return Arrays.copyOf(found, counter);
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
                break;
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
        int num = (int) (10.0 * Math.random());
        return simpleDateFormat.format(date) + String.valueOf(num);
    }
}
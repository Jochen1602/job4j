package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**class Tracker Решение задачи 2. Реализовать класс Tracker.
 *@author antontokarev
 *@since 14.10.2018
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод, реализующий редактирование заявок
     * @param id номер заявки для редактирования
     * @param item информация по заявке, что необходимо записать
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (findById(id).equals(this.items.get(i))) {
                item.setId(id);
                this.items.set(i, item);
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
        for (int i = 0; i < this.items.size(); i++) {
            if (findById(id).equals(this.items.get(i))) {
                this.items.remove(i);
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
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод получения списка заявок по имени.
     * @param key ключ поиска
     * @return список найденных заявок
     */
    public List<Item> findByName(String key) {
        Predicate<Item> predicate = p -> p.getName().equals(key);
        return this.items.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * метод поиска заявки по id
     * @param id ключ поиска
     * @return заявка по данному ключу
     */
    public Item findById(String id) {
        Predicate<Item> predicate = p -> p.getId().equals(id);
        return this.items.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        int num = (int) (10000.0 * Math.random());
        return simpleDateFormat.format(date) + String.valueOf(num);
    }
}
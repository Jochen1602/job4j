package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**class PhoneDictionary Решение задачи 1. Телефонный справочник на базе ArrayList
 *@author antontokarev
 *@since 01.11.2018
 */
public class PhoneDictionary {
    /**
     * Наш справочник
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Метод добавления новой позиции в справочник
     * @param person персона с именем, фамилией, телефоном и адресом
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Метод поиска по ключевому слову
     * @param key ключевое слово
     * @return список из результатов поиска
     */
    public List<Person> find(String key) {
        return persons.stream().filter(i -> (i.getName().contains(key) || i.getSurname().contains(key) || i.getPhone().contains(key) || i.getAddress().contains(key))).collect(Collectors.toList());
    }
}
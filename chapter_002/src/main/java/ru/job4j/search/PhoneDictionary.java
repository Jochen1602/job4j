package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

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
        List<Person> result = new ArrayList<>();
        for (Person p : this.persons) {
            if (p.getName().contains(key) || p.getSurname().contains(key) || p.getPhone().contains(key) || p.getAddress().contains(key)) {
                result.add(p);
            }
        }
        return result;
    }
}
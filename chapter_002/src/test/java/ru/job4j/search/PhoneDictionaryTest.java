package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class PhoneDictionaryTest Тестирование задачи 1. Телефонный справочник на базе ArrayList
 *@author antontokarev
 *@since 01.11.2018
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Anton", "Tokarev", "9165655114", "Moscow"));
        phones.add(new Person("Anton", "Schmidt", "9031149090", "Hamburg"));
        phones.add(new Person("Vasilij", "Batareev", "9991234567", "Moscow oblast, Khimki"));
        List<Person> persons = phones.find("114");
        assertThat(persons.iterator().next().getSurname(), is("Tokarev"));
        assertThat(persons.size(), is(2));
    }

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Anton", "Tokarev", "9165655114", "Moscow"));
        phones.add(new Person("Anton", "Schmidt", "9031149090", "Hamburg"));
        phones.add(new Person("Vasilij", "Batareev", "9991234567", "Moscow oblast, Khimki"));
        List<Person> persons = phones.find("Ant");
        assertThat(persons.iterator().next().getSurname(), is("Tokarev"));
        assertThat(persons.size(), is(2));
    }

    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Anton", "Tokarev", "9165655114", "Moscow"));
        phones.add(new Person("Anton", "Schmidt", "9031149090", "Hamburg"));
        phones.add(new Person("Vasilij", "Batareev", "9991234567", "Moscow oblast, Khimki"));
        List<Person> persons = phones.find("Khi");
        assertThat(persons.iterator().next().getName(), is("Vasilij"));
        assertThat(persons.size(), is(1));
    }
}
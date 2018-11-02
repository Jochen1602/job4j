package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class UserConvertTest Тестирование задачи 2. Написать программу преобразования List в Map.
 *@author antontokarev
 *@since 02.11.2018
 */
public class UserConvertTest {
    @Test
    public void firstTest() {
        UserConvert userConvert = new UserConvert();
        User first = new User(1001, "Ivan", "Kirov");
        User second = new User(900, "Max", "Moscow");
        User third = new User(1005, "John", "Berlin");
        List<User> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        list.add(third);
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(1001, first);
        expect.put(900, second);
        expect.put(1005, third);
        assertThat(result, is(expect));
    }
}
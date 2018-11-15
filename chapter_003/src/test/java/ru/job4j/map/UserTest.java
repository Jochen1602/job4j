package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 *class UserTest Решение задачи 2. Не перекрывать equals hashCode[#84125]
 *@author antontokarev
 *@since 15.11.2018
 */
public class UserTest {
    @Test
    public void twoNewUsersTest() {
        User first = new User("Hunter", 2, new GregorianCalendar(1986, Calendar.DECEMBER, 11));
        User second = new User("Hunter", 2, new GregorianCalendar(1986, Calendar.DECEMBER, 11));
        Map<User, Object> map = new HashMap<>();
        map.put(first, first);
        map.put(second, second);
        System.out.println(map);
    }
}
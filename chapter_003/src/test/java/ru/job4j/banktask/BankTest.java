package ru.job4j.banktask;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование задачи Пик посетителей в банке [#66292]
 * @author antontokarev
 * @since 29.11.2018
 */
public class BankTest {

    @Test
    public void whenOneVisit() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10),  time(8, 20))
        );
        assertThat(
                new Bank().max(visits),
                is(
                        Arrays.asList(
                                new Bank.Info(
                                        1, time(8, 10),  time(8, 20)
                                )
                        )
                )
        );
    }

    @Test
    public void whenCrossTwoVisits() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10),  time(8, 50)),
                new Bank.Visit(time(8, 30),  time(9, 15))
        );
        assertThat(
                new Bank().max(visits),
                is(
                        Arrays.asList(
                                new Bank.Info(
                                        2, time(8, 30),  time(8, 50)
                                )
                        )
                )
        );
    }

    @Test
    public void whenCrossThreeVisits() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10),  time(8, 50)),
                new Bank.Visit(time(8, 30),  time(9, 15)),
                new Bank.Visit(time(8, 45),  time(9, 55)),
                new Bank.Visit(time(10, 10),  time(10, 50)),
                new Bank.Visit(time(10, 30),  time(11, 15)),
                new Bank.Visit(time(10, 45),  time(11, 55))
        );
        assertThat(
                new Bank().max(visits),
                is(
                        Arrays.asList(
                                new Bank.Info(
                                        3, time(8, 45),  time(8, 50)
                                ), new Bank.Info(
                                        3, time(10, 45),  time(10, 50)
                                )
                        )
                )
        );
    }

    private long time(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
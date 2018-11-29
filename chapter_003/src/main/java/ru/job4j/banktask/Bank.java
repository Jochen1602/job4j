package ru.job4j.banktask;
import java.util.*;

/**
 * Решение задачи Пик посетителей в банке [#66292]
 * @author antontokarev
 * @since 29.11.2018
 */
public class Bank {
    /**
     * class Visit - класс, описывающий визит каждого посетителя.
     * Время начала и время окончания визита.
     */
    public static class Visit {
        private final long in;
        private final long out;

        public Visit(final long in, final long out) {
            this.in = in;
            this.out = out;
        }
    }

    /**
     * Класс, описывающий статистику по визитам. Сколько было максимум посетителей
     * и в какой период времени.
     */
    public static class Info {
        private long max;
        private long start;
        private long end;

        public Info(long max, long start, long end) {
            this.max = max;
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Info info = (Info) o;

            if (max != info.max) {
                return false;
            }
            if (start != info.start) {
                return false;
            }
            return end == info.end;
        }

        @Override
        public int hashCode() {
            int result = (int) (max ^ (max >>> 32));
            result = 31 * result + (int) (start ^ (start >>> 32));
            result = 31 * result + (int) (end ^ (end >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "max=" + max
                    + ", start=" + this.toTime(this.start)
                    + ", end=" + this.toTime(this.end)
                    + '}';
        }

        public String toTime(long time) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            return String.format("%s:%s", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
        }
    }

    /**
     * Метод определяет пик посетителей в банке. Сначала мы разбиваем весь охваченный
     * посетителями временной интервал на сегменты, когда менялось число посетителей
     * (туда входят все показания часов для входов и выходов посетителей), далее мы
     * пробегаем по всем сегментам и для каждого по всем интервалам визитов посетителей
     * и считаем сколько в данный момент времени было посетителей. Также по завершении
     * цикла мы имеем данные о максимуме посетителей. Следующим проходом по всем интервалам
     * и выводим все интервалы, когда наблюдался максимум посетителей на основании нашей
     * статистики.
     * @param visits визиты посетителей.
     * @return список периодов времени и количество посетителей с максимальным их числом.
     */
    public List<Info> max(List<Visit> visits) {
        List<Info> periods = new ArrayList<>();
        Set<Long> segments = new TreeSet<>();
        Map<Long, Integer> stat = new TreeMap<>();
        int max = 0;
        for (Visit i : visits) {
            segments.add(i.in);
            segments.add(i.out);
        }
        for (Long l : segments) {
            for (Visit v : visits) {
                if (l >= v.in && l < v.out) {
                    stat.merge(l, 1, (a, b) -> a + b);
                    if (max < stat.get(l)) {
                        max = stat.get(l);
                    }
                }
            }
        }
        Long[] seg = segments.toArray(new Long[segments.size()]);
        for (int i = 0; i < segments.size() - 1; i++) {
            if (stat.get(seg[i]) != null) {
                if (stat.get(seg[i]) == max) {
                    periods.add(new Info(max, seg[i], seg[i + 1]));
                }
            }
        }
        return periods;
    }
}
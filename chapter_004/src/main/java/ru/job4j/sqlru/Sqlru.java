package ru.job4j.sqlru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sqlru {
    List<Vacancy> data = new ArrayList<>();
    Set<Vacancy> vacancies = new LinkedHashSet<>();
    private String year;
    private String cron;
    String properties;
    static final String URL = "http://www.sql.ru/forum/job-offers/";

    /**
     * Парсинг конкретной страницы форума на тему поиска предложения о Java-работе.
     * @param doc что парсим.
     * @return false если дошли до конца сканируемого периода.
     * @throws IOException бросаем кирпич.
     */
    boolean parsing(Document doc) throws IOException {
        boolean result = false;
        Element content = doc.body();
        Elements links = content.getElementsByTag("a");
        Elements dates = content.getElementsByClass("altCol");
        if (dates.toString().split("<td style=\"text-align:center\" class=\"altCol\">")[20].split(" ")[2].equals(year + ",")) {
            result = true;
        }
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            if (linkText.contains("Java") && !linkText.contains("Javascript") && !linkText.contains("script") && !linkText.contains("Script") && !linkText.contains("JavaScript")) {
                Document inDoc = Jsoup.connect(linkHref).get();
                Element inContent = inDoc.body();
                String text;
                String date;
                if (inContent.getElementsByClass("msgBody").size() > 1) {
                    text = inContent.getElementsByClass("msgBody").get(1).text();
                    text = text.replace("\'", "");
                    date = inContent.getElementsByClass("msgFooter").get(0).text().split(",")[0];
                    if (!date.equals("вчера") && !date.equals("сегодня")) {
                        if (!date.split(" ")[2].equals(year)) {
                            data.add(new Vacancy(linkText, date, text, linkHref));
                        }
                    } else {
                        data.add(new Vacancy(linkText, date, text, linkHref));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Метод, вызывающий парсинг каждой страницы форума, начиная с первой,
     * покуда не пройдём весь нужный период.
     * @param url урл первой страницы форума.
     * @throws IOException бросаем кирпич.
     */
    private void pages(String url, String properties) throws IOException {
        int page = 1;
        boolean stop = false;
        DB db = new DB();
        db.init(properties);
        while (!stop) {
            Document doc = Jsoup.connect(url + page++).get();
            stop = this.parsing(doc);
        }
        for (int i = this.data.size() - 1; i >= 0; i--) {
            vacancies.add(this.data.get(i));
        }
        data.clear();
        for (Vacancy vacancy : vacancies) {
            System.out.println(vacancy);
        }
        db.add(vacancies, properties);
    }

    /**
     * Метод создания триггера по нашим данным из app1.properties (статическая переменная cron).
     * @param startHr сейчас часов.
     * @param startMin сейчас минут.
     * @param startSec сейчас секунд.
     * @return триггер.
     */
    public static Trigger fireStarter(int startHr, int startMin, int startSec) {
        return TriggerBuilder.newTrigger()
                .withIdentity("quartz")
                .startAt(DateBuilder.todayAt(startHr, startMin, startSec))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 16 * * ?"))
                .build();
    }

    /**
     * Метод считывает настройки Cron'а из файла пропертей.
     */
    public void cron(String properties) {
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream(properties)) {
            Properties config = new Properties();
            config.load(in);
            cron = config.getProperty("cron.time");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Берём текущую дату. Отбираем вакансии за этот год и прошлый. Вызываем парсинг страниц.
     *
     * @param args параметры командной строки.
     * @throws IOException эксепшен.
     * @throws SchedulerException эксепшен.
     */
    public static void main(String[] args) throws IOException, SchedulerException {
        DateFormat dateFormat = new SimpleDateFormat("yy");
        DateFormat dateForm = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time = dateForm.format(date);
        Sqlru sqlru = new Sqlru();
        sqlru.properties = "app.properties";
        sqlru.year = String.valueOf(Integer.parseInt(dateFormat.format(date)) - 2);
        sqlru.pages(URL, "app.properties");
        sqlru.cron("app.properties");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("Set", sqlru.vacancies);
        JobDetail job = JobBuilder.newJob(CronScan.class).build();
        scheduler.start();
        scheduler.scheduleJob(job, fireStarter(Integer.parseInt(time.split(":")[0]), Integer.parseInt(time.split(":")[1]), Integer.parseInt(time.split(":")[2])));
    }
}

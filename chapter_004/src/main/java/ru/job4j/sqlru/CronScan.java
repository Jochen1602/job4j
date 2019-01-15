package ru.job4j.sqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class CronScan implements Job {
    private Set<Vacancy> newVacancies = new LinkedHashSet<>();
    private static final Logger LOG = LogManager.getLogger(CronScan.class);

    /**
     * Парсим каждый день первую страницу (больше нет необходимости), и из того,
     * что напарсили, удаляем то, что было до этого. Для Set'а из того, что осталось,
     * то есть новых вакансий, вызываем метод для добавления их в БД.
     * @param context какой-то параметр.
     */
    public void execute(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        LOG.info(jobKey + " executing at " + new Date());
        try {
            Sqlru sqlru = new Sqlru();
            sqlru.parsing(Jsoup.connect(Sqlru.url).get());
            Set<Vacancy> allVacancies = new LinkedHashSet<>();
            newVacancies.addAll(sqlru.data);
            newVacancies.removeAll(allVacancies);
            if (newVacancies.size() > 0) {
                DB db = new DB();
                db.add(newVacancies, "app.properties");
                allVacancies.addAll(newVacancies);
                LOG.info("Added " + newVacancies.size() + " new vacancies.");
            } else {
                LOG.info("Nothing to add");
            }

        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}

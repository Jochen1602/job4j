package ru.job4j.tracker;

public interface UserAction {
    /**
     *Метод возвращает действие, что хочет выполнить пользователь.
     * @return номер пункта меню действия, что хочет выполнить пользователь.
     */
    int key();

    /**
     *Метод исполнения действий.
     * @param input интерфейс ввода.
     * @param tracker наш трэкер.
     */
    void execute(Input input, ITracker tracker);

    /**
     *Информативный метод.
     * @return инфо о работе метода.
     */
    String info();
}
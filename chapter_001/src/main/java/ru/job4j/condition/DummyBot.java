package ru.job4j.condition;

/***
 *Class Calculate решение задачи 4.1. Глупый бот.
 *@author antontokarev
 *@since 10.10.2018
 ***/

public class DummyBot {
    /**
     * Answer the questions
     * @param question The question from the client
     * @return The answer
     */
    public String answer (String question) {
        //default answer
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}
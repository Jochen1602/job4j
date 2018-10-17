package ru.job4j.classes;

/**class Doctor Решение задачи 1. Создать классы - профессий.
 *@author antontokarev
 *@since 16.10.2018
 */
public class Doctor extends Professions {
    public Diagnose heal(Patient patient) {
        return new Diagnose();
    }
}
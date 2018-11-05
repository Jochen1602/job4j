package ru.job4j.bank;

/**
 *class Account Решение задачи Банковские переводы.
 *@author antontokarev
 *@since 05.11.2018
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
package ru.job4j.bank;

/**
 *class Account Решение задачи Банковские переводы.
 *@author antontokarev
 *@since 05.11.2018
 */
public class Account {
    private double value;
    private String requisites;

    public Account() {
    }

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

    /**
     * Метод списания со счёта определённой суммы.
     * @param amount сумма, которую нужно списать.
     * @return true если списание прошло успешно, false если не прошло.
     */
    public boolean moneyTransfer(double amount) {
        boolean result = false;
        if (this.getValue() >= amount) {
            this.setValue(this.getValue() - amount);
            result = true;
        }
        return result;
    }
}
package ru.job4j.calculate;

/***
 *Class Calculate решение задачи 3.1. Элементарный калькулятор.
 *@author antontokarev
 *@since 09.10.2018
 ***/

public class Calculate {
    private double result;

    /**
     * Method of adding 2 doubles.
     *
     * @param first  First double.
     * @param second Second double.
     **/
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method of subtracting 2 doubles.
     *
     * @param first  First double.
     * @param second Second double.
     **/
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method of division 2 doubles.
     *
     * @param first  First double.
     * @param second Second double.
     **/
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method of multiplication 2 doubles.
     *
     * @param first  First double.
     * @param second Second double.
     **/
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}
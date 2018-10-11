package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/***
 *Class Converter. Тестирование задачи 3.2. Конвертер валюты.
 *@author antontokarev
 *@since 11.10.2018
 ***/

public class ConverterTest {
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    @Test
    public void when140RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(140);
        assertThat(result, is(2));
    }

    @Test
    public void when3DollarToRubleThen180() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(3);
        assertThat(result, is(180));
    }

    @Test
    public void when5EuroToRubleThen350() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(5);
        assertThat(result, is(350));
    }
}
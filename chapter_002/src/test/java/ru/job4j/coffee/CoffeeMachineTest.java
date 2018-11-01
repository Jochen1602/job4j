package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *class CoffeeMachineTest Тестирование кофемашины, выдающей сдачу монетами
 *@author antontokarev
 *@since 01.11.2018
 */
public class CoffeeMachineTest {
    @Test
    public void whenWePay100AndThePriceIs39() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] expect = new int[] {10, 10, 10, 10, 10, 10, 1};
        assertThat(coffeeMachine.changes(100, 39), is(expect));
    }

    @Test
    public void whenWePay100AndThePriceIs41() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] expect = new int[] {10, 10, 10, 10, 10, 5, 2, 2};
        assertThat(coffeeMachine.changes(100, 41), is(expect));
    }

    @Test
    public void whenWePay100AndThePriceIs42() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] expect = new int[] {10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(coffeeMachine.changes(100, 42), is(expect));
    }

    @Test
    public void whenWePay50AndThePriceIs47() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] expect = new int[] {2, 1};
        assertThat(coffeeMachine.changes(50, 47), is(expect));
    }
}
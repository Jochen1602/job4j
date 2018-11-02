package ru.job4j.tracker;

import java.util.List;

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, List<Integer> range) throws MenuOutException {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Select the key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Enter correct data.");
            }
        } while (invalid);
        return value;
    }
}
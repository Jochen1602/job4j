package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) throws MenuOutException {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
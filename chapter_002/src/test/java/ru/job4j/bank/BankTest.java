package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *class BankTest Тестирование задачи Банковские переводы.
 *@author antontokarev
 *@since 05.11.2018
 */
public class BankTest {
    @Test
    public void whenUserWasSuccessfullyDeleted() {
        Bank bank = new Bank();
        User first = new User("Anton", "9406123456");
        User second = new User("Hans", "5544332211");
        bank.addUser(first);
        bank.addUser(second);
        bank.deleteUser(second);
        assertThat(bank.isUser(second), is(false));
    }
    @Test
    public void whenUserHas3Accounts() {
        Bank bank = new Bank();
        User user = new User("Anton", "9406123456");
        bank.addUser(user);
        Account firstAccount = new Account(10000.00, "123456");
        Account secondAccount = new Account(20000.00, "qwerty");
        Account thirdAccount = new Account(1000.00, "Das Boot");
        bank.addAccountToUser(user.getPassport(), firstAccount);
        bank.addAccountToUser(user.getPassport(), secondAccount);
        bank.addAccountToUser(user.getPassport(), thirdAccount);
        assertThat(bank.getUserAccounts("9406123456").size(), is(3));
    }
    @Test
    public void whenUserHas2AccountsAndDelete1OfThem() {
        Bank bank = new Bank();
        User user = new User("Anton", "9406123456");
        bank.addUser(user);
        Account firstAccount = new Account(10000.00, "123456");
        Account secondAccount = new Account(20000.00, "qwerty");
        bank.addAccountToUser(user.getPassport(), firstAccount);
        bank.addAccountToUser(user.getPassport(), secondAccount);
        bank.deleteAccountFromUser("9406123456", firstAccount);
        assertThat(bank.getUserAccounts("9406123456").get(0).getValue(), is(20000.0));
    }
    @Test
    public void whenTransferIfFalseBecauseNotEnoughMoney() {
        Bank bank = new Bank();
        User first = new User("Anton", "9406123456");
        User second = new User("Hans", "5544332211");
        bank.addUser(first);
        bank.addUser(second);
        Account firstAccount = new Account(10000.00, "123456");
        Account secondAccount = new Account(20000.00, "qwerty");
        bank.addAccountToUser(first.getPassport(), firstAccount);
        bank.addAccountToUser(second.getPassport(), secondAccount);
        assertThat(bank.transferMoney("9406123456", "123456", "5544332211", "qwerty", 10001.00), is(false));
    }
    @Test
    public void whenTransferIfTrue() {
        Bank bank = new Bank();
        User first = new User("Anton", "9406123456");
        User second = new User("Hans", "5544332211");
        bank.addUser(first);
        bank.addUser(second);
        Account firstAccount = new Account(10000.00, "123456");
        Account secondAccount = new Account(20000.00, "qwerty");
        bank.addAccountToUser(first.getPassport(), firstAccount);
        bank.addAccountToUser(second.getPassport(), secondAccount);
        assertThat(bank.transferMoney("9406123456", "123456", "5544332211", "qwerty", 10000.00), is(true));
    }
    @Test
    public void whenTransferIfFalseBecauseTheDataIsIncorrect() {
        Bank bank = new Bank();
        User first = new User("Anton", "9406123456");
        User second = new User("Hans", "5544332211");
        bank.addUser(first);
        bank.addUser(second);
        Account firstAccount = new Account(10000.00, "123456");
        Account secondAccount = new Account(20000.00, "qwerty");
        bank.addAccountToUser(first.getPassport(), firstAccount);
        bank.addAccountToUser(second.getPassport(), secondAccount);
        assertThat(bank.transferMoney("9406123456", "123457", "5544332211", "qwerty", 1000.00), is(false));
    }
}
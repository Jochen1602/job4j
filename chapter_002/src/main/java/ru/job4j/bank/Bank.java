package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *class Bank Решение задачи Банковские переводы.
 *@author antontokarev
 *@since 05.11.2018
 */
public class Bank {
    /**
     * Коллекция, хранящая списки счетов у каждого пользователя.
     */
    private Map<User, List<Account>> usersAccounts = new HashMap<>();

    /**
     * Метод добавления пользователя.
     * @param user элемент, что хотим добавить.
     */
    public void addUser(User user) {
        this.usersAccounts.putIfAbsent(user, new ArrayList<>());
    }

    public boolean isUser(User user) {
        return usersAccounts.get(user) != null;
    }

    /**
     * Метод удаления пользователя.
     * @param user элемент, что хотим удалить.
     */
    public void deleteUser(User user) {
        this.usersAccounts.remove(user);
    }

    /**
     * Метод добавления счёта пользователю.
     * @param passport по какому идентификатору добавляется счёт.
     * @param account какой счёт добавляется.
     */
    public void addAccountToUser(String passport, Account account) {
        this.usersAccounts.get(getUserByPassport(passport)).add(account);
    }

    /**
     * Метод удаления счёта пользователю.
     * @param passport по какому идентификатору удаляется счёт.
     * @param account какой счёт удаляется.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.usersAccounts.get(getUserByPassport(passport)).remove(account);
    }

    /**
     * Метод получения всех счетов пользователя.
     * @param passport по какому идентификатору искать счета.
     * @return список счетов.
     */
    public List<Account> getUserAccounts(String passport) {
        return this.usersAccounts.get(getUserByPassport(passport));
    }

    /**
     * Метод поиска пользователя по данным паспорта. Исполняется только если такой пользователь найден.
     * @param passport данные паспорта.
     * @return искомый пользователь.
     */
    private User getUserByPassport(String passport) {
        return usersAccounts.keySet().stream().filter(i -> i.getPassport().equals(passport)).findFirst().get();
    }

    public boolean isUserByPassport(String passport) {
        return usersAccounts.keySet().stream().anyMatch(i -> i.getPassport().equals(passport));
    }

    /**
     * Метод, возвращающий счёт по паспорту пользователя и реквизитам счёта.
     * @param passport номер паспорта пользователя.
     * @param requisite реквизиты счёта.
     * @return искомый счёт.
     */
    private Account getAccountByRequisiteAndPassport(String passport, String requisite) {
        return getUserAccounts(passport).stream().filter(i -> i.getRequisites().equals(requisite)).findFirst().get();
    }

    /**
     * Метод перечисления денег с одного счёта на другой. Перемещения средств осуществляются, если найдены
     * пользователи и счета (источники и назначение), а также на счёте-источнике хватает средств.
     * @param srcPassport идентификатор источника перевода.
     * @param srcRequisite реквизиты источника перевода.
     * @param destPassport идентификатор получателя перевода.
     * @param dstRequisite реквизиты получателя перевода.
     * @param amount рамзер перевода.
     * @return true если перевод проведён успешно, false если перевод не прошёл.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        if (isUserByPassport(srcPassport) && isUserByPassport(destPassport) && getAccountByRequisiteAndPassport(srcPassport, srcRequisite).getValue() >= amount) {
            result = getAccountByRequisiteAndPassport(srcPassport, srcRequisite).moneyTransfer(amount) && getAccountByRequisiteAndPassport(destPassport, dstRequisite).moneyTransfer(-amount);
        }
        return result;
    }
}
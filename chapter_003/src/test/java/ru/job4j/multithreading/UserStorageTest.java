package ru.job4j.multithreading;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class UserStorageTest Тестирование задачи 2. Класс хранилища пользователей UserStorage[#84189]
 *@author antontokarev
 *@since 29.11.2018
 */
public class UserStorageTest {
    private class AddUserToStorage extends Thread {
        private final UserStorage userStorage;
        private final User user;

        public AddUserToStorage(final UserStorage userStorage, final User user) {
            this.userStorage = userStorage;
            this.user = user;
        }

        @Override
        public synchronized void run() {
            userStorage.add(user);
        }
    }

    private class TransferFromUserToUser extends Thread {
        private final UserStorage userStorage;
        private final int fromId;
        private final int toId;
        private final int amount;

        public TransferFromUserToUser(final UserStorage userStorage, final int fromId, final int toId, final int amount) {
            this.userStorage = userStorage;
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
        }

        @Override
        public synchronized void run() {
            userStorage.transfer(fromId, toId, amount);
        }
    }

    @Test
    public void operationsTest() {
        UserStorage store = new UserStorage();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        store.transfer(1, 2, 50);
        assertThat(store.findUserById(1).getAmount(), is(50));
        assertThat(store.findUserById(2).getAmount(), is(250));
        assertThat(store.transfer(1, 2, 51), is(false));
        store.transfer(2, 1, 250);
        assertThat(store.findUserById(1).getAmount(), is(300));
        assertThat(store.findUserById(2).getAmount(), is(0));
        assertThat(store.idDelete(1), is(true));
        assertThat(store.findUserById(1) == null, is(true));
    }

    @Test
    public void threadsTest() throws InterruptedException {
        final UserStorage store = new UserStorage();
        final User first = new User(1, 200);
        final User second = new User(2, 200);
        final User third = new User(3, 200);
        final User fourth = new User(4, 200);
        Thread firstThread = new AddUserToStorage(store, first);
        Thread secondThread = new AddUserToStorage(store, second);
        Thread thirdThread = new AddUserToStorage(store, third);
        Thread fourthThread = new AddUserToStorage(store, fourth);
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();
        fourthThread.join();
        Thread firstTransfer = new TransferFromUserToUser(store, 1, 2, 100);
        Thread secondTransfer = new TransferFromUserToUser(store, 2, 3, 100);
        Thread thirdTransfer = new TransferFromUserToUser(store, 3, 4, 100);
        Thread fourthTransfer = new TransferFromUserToUser(store, 4, 2, 50);
        Thread fifthTransfer = new TransferFromUserToUser(store, 3, 1, 50);
        Thread sixthTransfer = new TransferFromUserToUser(store, 2, 1, 50);
        firstTransfer.start();
        secondTransfer.start();
        thirdTransfer.start();
        fourthTransfer.start();
        fifthTransfer.start();
        sixthTransfer.start();
        firstTransfer.join();
        secondTransfer.join();
        thirdTransfer.join();
        fourthTransfer.join();
        fifthTransfer.join();
        sixthTransfer.join();
        assertThat(store.findUserById(1).getAmount(), is(200));
        assertThat(store.findUserById(2).getAmount(), is(200));
        assertThat(store.findUserById(3).getAmount(), is(150));
        assertThat(store.findUserById(4).getAmount(), is(250));
        Thread a1Transfer = new TransferFromUserToUser(store, 1, 2, 100);
        Thread a2Transfer = new TransferFromUserToUser(store, 2, 1, 100);
        Thread a3Transfer = new TransferFromUserToUser(store, 1, 2, 100);
        Thread a4Transfer = new TransferFromUserToUser(store, 2, 1, 100);
        Thread a5Transfer = new TransferFromUserToUser(store, 1, 2, 100);
        Thread a6Transfer = new TransferFromUserToUser(store, 2, 1, 100);
        Thread a7Transfer = new TransferFromUserToUser(store, 1, 2, 100);
        Thread a8Transfer = new TransferFromUserToUser(store, 2, 1, 100);
        a1Transfer.start();
        a2Transfer.start();
        a3Transfer.start();
        a4Transfer.start();
        a5Transfer.start();
        a6Transfer.start();
        a7Transfer.start();
        a8Transfer.start();
        a1Transfer.join();
        a2Transfer.join();
        a3Transfer.join();
        a4Transfer.join();
        a5Transfer.join();
        a6Transfer.join();
        a7Transfer.join();
        a8Transfer.join();
        assertThat(store.findUserById(1).getAmount(), is(200));
        assertThat(store.findUserById(2).getAmount(), is(200));
        Thread aTransfer = new TransferFromUserToUser(store, 3, 1, 50);
        Thread bTransfer = new TransferFromUserToUser(store, 1, 3, 250);
        Thread cTransfer = new TransferFromUserToUser(store, 3, 2, 50);
        Thread dTransfer = new TransferFromUserToUser(store, 2, 3, 250);
        Thread eTransfer = new TransferFromUserToUser(store, 3, 4, 50);
        Thread fTransfer = new TransferFromUserToUser(store, 4, 3, 300);
        aTransfer.start();
        bTransfer.start();
        cTransfer.start();
        dTransfer.start();
        eTransfer.start();
        fTransfer.start();
        aTransfer.join();
        bTransfer.join();
        cTransfer.join();
        dTransfer.join();
        eTransfer.join();
        fTransfer.join();
        assertThat(store.findUserById(3).getAmount(), is(800));
    }
}

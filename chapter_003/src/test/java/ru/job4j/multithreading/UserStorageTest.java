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
    private class addUserToStorage extends Thread {
        private final UserStorage userStorage;
        private final User user;

        public addUserToStorage(final UserStorage userStorage, final User user) {
            this.userStorage = userStorage;
            this.user = user;
        }

        @Override
        public synchronized void run() {
            userStorage.add(user);
        }
    }

    private class transferFromUserToUser extends Thread {
        private final UserStorage userStorage;
        private final int fromId;
        private final int toId;
        private final int amount;

        public transferFromUserToUser(final UserStorage userStorage, final int fromId, final int toId, final int amount) {
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
        Thread firstThread = new addUserToStorage(store, first);
        Thread secondThread = new addUserToStorage(store, second);
        Thread thirdThread = new addUserToStorage(store, third);
        Thread fourthThread = new addUserToStorage(store, fourth);
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();
        fourthThread.join();
        Thread firstTransfer = new transferFromUserToUser(store, 1, 2, 100);
        Thread secondTransfer = new transferFromUserToUser(store, 2, 3, 100);
        Thread thirdTransfer = new transferFromUserToUser(store, 3, 4, 100);
        Thread fourthTransfer = new transferFromUserToUser(store, 4, 2, 50);
        Thread fifthTransfer = new transferFromUserToUser(store, 3, 1, 50);
        Thread sixthTransfer = new transferFromUserToUser(store, 2, 1, 50);
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
        Thread a1Transfer = new transferFromUserToUser(store, 1, 2, 100);
        Thread a2Transfer = new transferFromUserToUser(store, 2, 1, 100);
        Thread a3Transfer = new transferFromUserToUser(store, 1, 2, 100);
        Thread a4Transfer = new transferFromUserToUser(store, 2, 1, 100);
        Thread a5Transfer = new transferFromUserToUser(store, 1, 2, 100);
        Thread a6Transfer = new transferFromUserToUser(store, 2, 1, 100);
        Thread a7Transfer = new transferFromUserToUser(store, 1, 2, 100);
        Thread a8Transfer = new transferFromUserToUser(store, 2, 1, 100);
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
        Thread aTransfer = new transferFromUserToUser(store, 3, 1, 50);
        Thread bTransfer = new transferFromUserToUser(store, 1, 3, 250);
        Thread cTransfer = new transferFromUserToUser(store, 3, 2, 50);
        Thread dTransfer = new transferFromUserToUser(store, 2, 3, 250);
        Thread eTransfer = new transferFromUserToUser(store, 3, 4, 50);
        Thread fTransfer = new transferFromUserToUser(store, 4, 3, 300);
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

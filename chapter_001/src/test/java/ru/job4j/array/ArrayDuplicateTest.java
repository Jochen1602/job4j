package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class BubbleSortTest Тестирование задачи 6.8. Удаление дубликатов в массиве.
 *@author antontokarev
 *@since 14.10.2018
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = new String[] {"Bob", "Alf", "Alf", "Georg", "Bob", "Alf"};
        String[] expect = new String[] {"Bob", "Alf", "Georg"};
        String[] result = arrayDuplicate.remove(input);
        assertThat(result, is(expect));
    }
    @Test
    public void whenRemoveDuplicatesThenArrayWithDuplicate2() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = new String[] {"Bob", "Alf", "Alf", "Georg", "Bob", "Alf", "Bob", "Georg"};
        String[] expect = new String[] {"Bob", "Alf", "Georg"};
        String[] result = arrayDuplicate.remove(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = new String[] {"Bob", "Alf", "Georg", "Bobby", "Alfred"};
        String[] expect = new String[] {"Bob", "Alf", "Georg", "Bobby", "Alfred"};
        String[] result = arrayDuplicate.remove(input);
        assertThat(result, is(expect));
    }
}
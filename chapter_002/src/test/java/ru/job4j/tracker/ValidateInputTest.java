package ru.job4j.tracker;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**Проверка класса ValidateInput
 *@author antontokarev
 *@since 18.10.2018
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream buf = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.buf));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenWeHaveInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"gzegorz", "4"})
        );
        input.ask("Enter", new int[] {4});
        assertThat(
                this.buf.toString(),
                is(
                        String.format("Enter correct data.%n")
                )
        );
    }

    @Test
    public void whenWeHaveInvalidInputPart2() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"99", "4"})
        );
        input.ask("Enter", new int[] {4});
        assertThat(
                this.buf.toString(),
                is(
                        String.format("Select the key from menu.%n")
                )
        );
    }
}
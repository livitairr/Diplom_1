package tests;

import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private static final String TEST_BUN_NAME = "Some bun";

    // Проверяет, что метод корректно возвращает название булочки.
    @Test
    public void testGetName() {
        Bun bun = new Bun(TEST_BUN_NAME, 500f);
        String expectedName = TEST_BUN_NAME;
        String actualName = bun.getName();
        assertEquals(expectedName, actualName);
    }

    // Проверяет, что метод корректно возвращает цену булочки.
    @Test
    public void testGetPrice() {
        Bun bun = new Bun(TEST_BUN_NAME, 600f);
        float expectedPrice = 600f;
        float actualPrice = bun.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}

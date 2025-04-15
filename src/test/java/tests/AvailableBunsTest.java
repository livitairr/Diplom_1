package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AvailableBunsTest {

    private final String expectedName;
    private final float expectedPrice;
    private final int index;

    private List<Bun> buns;

    // Конструктор, принимающий параметры теста
    public AvailableBunsTest(String expectedName, float expectedPrice, int index) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.index = index;
    }

    // Параметры для теста
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100f, 0},
                {"white bun", 200f, 1},
                {"red bun", 300f, 2}
        };
    }

    @Before
    public void setUp() {
        Database database = new Database();
        buns = database.availableBuns();
    }

    @Test
    public void testAvailableBunName() {
        assertTrue("Список булочек меньше, чем ожидалось", buns.size() > index);
        Bun actualBun = buns.get(index);
        assertEquals(expectedName, actualBun.getName());
    }

    @Test
    public void testAvailableBunPrice() {
        assertTrue("Список булочек меньше, чем ожидалось", buns.size() > index);
        Bun actualBun = buns.get(index);
        assertEquals(expectedPrice, actualBun.getPrice(), 0.01f);
    }
}

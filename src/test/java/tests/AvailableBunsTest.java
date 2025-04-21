package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import java.util.List;

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
    public void testAvailableBunProperties() {
        SoftAssertions softly = new SoftAssertions();

        // Проверка, что список не пуст и содержит нужный индекс
        softly.assertThat(buns.size())
                .as("Список булочек должен содержать индекс %d", index)
                .isGreaterThan(index);

        if (buns.size() > index) {
            Bun actualBun = buns.get(index);

            // Проверка имени булочки
            softly.assertThat(actualBun.getName())
                    .as("Имя булочки по индексу %d", index)
                    .isEqualTo(expectedName);

            // Проверка цены булочки
            softly.assertThat(actualBun.getPrice())
                    .as("Цена булочки по индексу %d", index)
                    .isCloseTo(expectedPrice, within(0.01f));
        }

        softly.assertAll();
    }

    // Хелпер для assertJ, чтобы не импортировать вручную
    private static org.assertj.core.data.Offset<Float> within(float value) {
        return org.assertj.core.data.Offset.offset(value);
    }
}

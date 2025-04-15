package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

// Указываем, что тест будет параметризованным
@RunWith(Parameterized.class)
public class IngredientTypeTest {

    // Поле для хранения тестируемого типа ингредиента
    private final IngredientType ingredientType;

    // Поле для хранения ожидаемого строкового представления
    private final String expectedName;

    // Конструктор, который будет получать параметры теста
    public IngredientTypeTest(IngredientType ingredientType, String expectedName) {
        this.ingredientType = ingredientType;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"}, // Тестируем, что IngredientType.SAUCE соответствует строке "SAUCE"
                {IngredientType.FILLING, "FILLING"} // Тестируем, что IngredientType.FILLING соответствует строке "FILLING"
        };
    }

    // Тест проверяет, что метод name() у IngredientType возвращает ожидаемое строковое значение
    @Test
    public void nameIngredientTypeTest() {
        // Сравниваем ожидаемое и фактическое значения
        assertEquals("Метод name() не возвращает ожидаемое строковое значение для типа: "
                + ingredientType, expectedName, ingredientType.name());
    }
}

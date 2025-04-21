package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType ingredientType;
    private final String expectedName;

    public IngredientTypeTest(IngredientType ingredientType, String expectedName) {
        this.ingredientType = ingredientType;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void nameIngredientTypeTest() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredientType.name())
                .as("Метод name() должен возвращать правильное строковое представление для " + ingredientType)
                .isEqualTo(expectedName);
        softly.assertAll();
    }
}

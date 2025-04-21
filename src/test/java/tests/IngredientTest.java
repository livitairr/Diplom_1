package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static praktikum.IngredientType.FILLING;

public class IngredientTest {

    @Test
    public void testGetPriceReturnsCorrectValue() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 500f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getPrice())
                .as("Цена ингредиента должна быть 500.0")
                .isCloseTo(500f, within(0.01f));
        softly.assertAll();
    }

    @Test
    public void testGetNameReturnsCorrectValue() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 600f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getName())
                .as("Имя ингредиента должно быть 'some ingredient'")
                .isEqualTo("some ingredient");
        softly.assertAll();
    }

    @Test
    public void testGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 400f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getType())
                .as("Тип ингредиента должен быть FILLING")
                .isEqualTo(FILLING);
        softly.assertAll();
    }

    @Test
    public void testZeroPriceIsAccepted() {
        Ingredient ingredient = new Ingredient(FILLING, "free topping", 0f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getPrice())
                .as("Цена должна быть 0.0 для бесплатного ингредиента")
                .isCloseTo(0f, within(0.01f));
        softly.assertAll();
    }

    @Test
    public void testEmptyNameIsAccepted() {
        Ingredient ingredient = new Ingredient(FILLING, "", 100f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getName())
                .as("Имя ингредиента может быть пустым")
                .isEqualTo("");
        softly.assertAll();
    }

    private static org.assertj.core.data.Offset<Float> within(float value) {
        return org.assertj.core.data.Offset.offset(value);
    }
}

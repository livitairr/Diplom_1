package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

@RunWith(Parameterized.class)
public class AvailableIngredientsTest {

    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;
    private final int index;

    private List<Ingredient> ingredients;

    public AvailableIngredientsTest(IngredientType expectedType, String expectedName, float expectedPrice, int index) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f, 0},
                {IngredientType.SAUCE, "sour cream", 200f, 1},
                {IngredientType.SAUCE, "chili sauce", 300f, 2},
                {IngredientType.FILLING, "cutlet", 100f, 3},
                {IngredientType.FILLING, "dinosaur", 200f, 4},
                {IngredientType.FILLING, "sausage", 300f, 5}
        };
    }

    @Before
    public void setUp() {
        ingredients = new Database().availableIngredients();
    }

    @Test
    public void testAvailableIngredientProperties() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(index)
                .as("Индекс %d выходит за пределы списка (размер: %d)", index, ingredients.size())
                .isLessThan(ingredients.size());

        if (index < ingredients.size()) {
            Ingredient actualIngredient = ingredients.get(index);

            softly.assertThat(actualIngredient.getType())
                    .as("Тип ингредиента по индексу %d", index)
                    .isEqualTo(expectedType);

            softly.assertThat(actualIngredient.getName())
                    .as("Название ингредиента по индексу %d", index)
                    .isEqualTo(expectedName);

            softly.assertThat(actualIngredient.getPrice())
                    .as("Цена ингредиента по индексу %d", index)
                    .isCloseTo(expectedPrice, within(0.01f));
        }

        softly.assertAll();
    }

    private static org.assertj.core.data.Offset<Float> within(float value) {
        return org.assertj.core.data.Offset.offset(value);
    }
}

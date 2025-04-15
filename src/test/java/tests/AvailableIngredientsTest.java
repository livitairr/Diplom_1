package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    private Ingredient getIngredientByIndex() {
        assertTrue("Индекс выходит за пределы списка ингредиентов", index < ingredients.size());
        return ingredients.get(index);
    }

    @Test
    public void testAvailableIngredientType() {
        Ingredient actualIngredient = getIngredientByIndex();
        assertEquals(expectedType, actualIngredient.getType());
    }

    @Test
    public void testAvailableIngredientName() {
        Ingredient actualIngredient = getIngredientByIndex();
        assertEquals(expectedName, actualIngredient.getName());
    }

    @Test
    public void testAvailableIngredientPrice() {
        Ingredient actualIngredient = getIngredientByIndex();
        assertEquals(expectedPrice, actualIngredient.getPrice(), 0.01f);
    }
}

package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun)
                .as("Булочка должна быть установлена корректно")
                .isEqualTo(bun);
        softly.assertAll();
    }

    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(ingredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients)
                .as("Список ингредиентов должен содержать 1 элемент")
                .hasSize(1);
        softly.assertAll();
    }

    @Test
    public void testAddIngredientStoresCorrectItem() {
        burger.addIngredient(ingredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0))
                .as("Ингредиент должен быть добавлен на первую позицию")
                .isEqualTo(ingredient);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredientReducesSize() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients)
                .as("Список должен быть пуст после удаления")
                .isEmpty();
        softly.assertAll();
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient);   // 0
        burger.addIngredient(ingredient2);  // 1
        burger.addIngredient(ingredient3);  // 2

        burger.moveIngredient(0, 2); // Перемещаем первый в конец

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(2))
                .as("Ингредиент должен переместиться в позицию 2")
                .isEqualTo(ingredient);
        softly.assertAll();
    }

    @Test
    public void testGetPriceCalculatesCorrectly() {
        when(bun.getPrice()).thenReturn(10f);
        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(20f);
        when(ingredient2.getPrice()).thenReturn(30f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        float expectedPrice = 10f * 2 + 20f + 30f;
        float actualPrice = burger.getPrice();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualPrice)
                .as("Общая цена должна быть рассчитана корректно")
                .isCloseTo(expectedPrice, within(0.01f));
        softly.assertAll();
    }

    @Test
    public void testGetReceiptIsFormattedCorrectly() {
        when(bun.getName()).thenReturn("Fake Bun");
        when(bun.getPrice()).thenReturn(44f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(55f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "Fake Bun",
                "filling", "hot sauce",
                "Fake Bun",
                44f * 2 + 55f
        ).trim();

        String actualReceipt = burger.getReceipt().trim();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualReceipt)
                .as("Чек должен быть отформатирован корректно")
                .isEqualTo(expectedReceipt);
        softly.assertAll();
    }

    private static org.assertj.core.data.Offset<Float> within(float value) {
        return org.assertj.core.data.Offset.offset(value);
    }
}

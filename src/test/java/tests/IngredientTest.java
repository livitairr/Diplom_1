package tests;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 500f);
        float expectedPrice = 500f;
        assertEquals(expectedPrice, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 600f);
        String expectedName = "some ingredient";
        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(FILLING, "some ingredient", 400f);
        IngredientType expectedType = FILLING;
        assertEquals(expectedType, ingredient.getType());
    }


    @Test
    public void testZeroPrice() {
        Ingredient ingredient = new Ingredient(FILLING, "free topping", 0f);
        assertEquals(0f, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void testEmptyName() {
        Ingredient ingredient = new Ingredient(FILLING, "", 100f);
        assertEquals("", ingredient.getName());
    }

}
package day14;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TestRecipe {
    @Test
    public void testRecipeToString() {
        Recipe r = new Recipe();
        r.addIngredient("A", 3);
        r.addIngredient("B", 4);
        r.setOutput("AB", 1);
        assertEquals("3 A, 4 B => 1 AB", r.toString());
    }

    @Test
    public void testRecipeIngredientFactor() {
        Recipe r = new Recipe();
        r.addIngredient("ORE", 7);
        r.setOutput("C", 5);
        HashMap<String, Long> expected = new HashMap<>();
        expected.put("ORE", 21L);
        assertEquals(expected, r.getIngredientsForOutput(13));
        assertEquals(expected, r.getIngredientsForOutput(15));
        assertNotEquals(expected, r.getIngredientsForOutput(16));
        assertNotEquals(expected, r.getIngredientsForOutput(10));
    }

    @Test
    public void testRecipeFromStringWithOneIngredient() {
        assertEquals("10 ORE => 10 A", Recipe.fromString("10 ORE => 10 A").toString());
    }

    @Test
    public void testRecipeFromStringWithMoreIngredients() {
        assertEquals("2 AB, 3 BC, 4 CA => 1 FUEL", Recipe.fromString("2 AB, 3 BC, 4 CA => 1 FUEL").toString());
    }
}

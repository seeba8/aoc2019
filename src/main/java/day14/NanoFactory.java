package day14;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class NanoFactory {
    private List<Recipe> recipeList;

    NanoFactory() {
        recipeList = new ArrayList<>();
    }

    Recipe getRecipe(int index) {
        return recipeList.get(index);
    }

    void addRecipe(Recipe r) {
        recipeList.add(r);
    }

    Recipe getRecipeProducingOutput(String outputType) {
        for (Recipe r : recipeList) {
            if (r.getOutputType().equals(outputType)) return r;
        }
        throw new NoSuchElementException("No recipe produces " + outputType);
    }

    long getRawMaterials(String outputType, long quantity) {
        Map<String, Long> excess = new HashMap<>();
        return getRawMaterials(outputType, quantity, excess);
    }

    private long getRawMaterials(String outputType, long requiredQuantity, Map<String, Long> excess) {
        long rawQuantity = 0;
        Recipe r;

        try {
            r = getRecipeProducingOutput(outputType);
        } catch (NoSuchElementException x) { // is raw material, so we return the input quantity
            return requiredQuantity;
        }

        /*
            Do we already have some or all of the required output? If so, return 0 / reduce the required amount accordingly
         */
        long existingOutputQuantity = excess.getOrDefault(outputType, 0L);
        if (existingOutputQuantity >= requiredQuantity) {
            excess.put(outputType, existingOutputQuantity - requiredQuantity);
            return 0;
        } else {
            requiredQuantity -= existingOutputQuantity;
        }

        /*
            Loop through each ingredient. Find out if we already have produced some of it. If so, we don't need
            to produce that amount again, so we can subtract it. Otherwise, we may produce too much of it.
        */
        long factor = (long) Math.ceil((requiredQuantity * 1.) / r.getOutputQuantity());
        excess.put(outputType, r.getOutputQuantity() * factor - requiredQuantity);
        for (Map.Entry<String, Long> ingredient : r.getIngredients().entrySet()) {
            rawQuantity += getRawMaterials(ingredient.getKey(), ingredient.getValue() * factor, excess);
        }

        return rawQuantity;
    }

    long getOutputQuantity(String outputType, long inputQuantity) {
        long inputForOne = getRawMaterials(outputType, 1);
        long output = inputQuantity / inputForOne; // integer division, rounds down
        long knownMinimum = output;
        long knownMaximum = Long.MAX_VALUE;
        while (true) {
            long inputForOutput = getRawMaterials(outputType, output) < 0 ? Long.MAX_VALUE : getRawMaterials(outputType, output);
            if (inputForOutput <= inputQuantity
                    && getRawMaterials(outputType, output + 1) > inputQuantity) {
                return output;
            }
            if (inputForOutput > inputQuantity) {
                knownMaximum = output;
            } else {
                knownMinimum = output;
            }
            output = (long) (knownMinimum + 0.5 * (knownMaximum - knownMinimum));
        }
    }

    static NanoFactory getFactoryFromRecipes(String recipes) {
        return getFactoryFromRecipes(recipes.split("\n"));
    }

    static NanoFactory getFactoryFromRecipes(String[] recipes) {
        NanoFactory nf = new NanoFactory();
        for (String recipe : recipes) {
            nf.addRecipe(Recipe.fromString(recipe));
        }
        return nf;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        NanoFactory nf = NanoFactory.getFactoryFromRecipes(Input.getLines("day14.txt"));
        System.out.printf("Input requirement for 1 FUEL: %s\n", nf.getRawMaterials("FUEL", 1));
        long trillion = 1_000_000_000_000L;
        System.out.printf("With %s ORE, we can produce %s FUEL\n", trillion, nf.getOutputQuantity("FUEL", trillion));
    }


}

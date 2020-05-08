package day14;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipe {
    private Map<String, Long> ingredients;
    private String outputType;
    private long outputQuantity;

    static Recipe fromString(String recipe) {
        Recipe r = new Recipe();
        String input = recipe.substring(0, recipe.indexOf("=>")).trim();
        String output = recipe.substring(recipe.indexOf("=>")+2).trim();
        r.setOutput(
                output.substring(output.indexOf(" ")).trim(),
                Long.parseLong(output.substring(0, output.indexOf(" ")))
        );
        for(String part : input.split(", ")) {
            r.addIngredient(
                    part.substring(part.indexOf(" ")).trim(),
                    Long.parseLong(part.substring(0, part.indexOf(" ")))
            );
        }
        return r;
    }

    Map<String, Long> getIngredients() {
        return ingredients;
    }


    String getOutputType() {
        return outputType;
    }

    long getOutputQuantity() {
        return outputQuantity;
    }

    void setOutput(String outputType, long outputQuantity) {
        this.outputQuantity = outputQuantity;
        this.outputType = outputType;
    }

    void addIngredient(String type, long quantity) {
        ingredients.put(type, quantity);
    }

    Recipe() {
        ingredients = new HashMap<>();
    }

    Map<String, Long> getIngredientsForOutput(long quantity) {
        long factor = (long) Math.ceil((quantity * 1.) / outputQuantity);
        Map<String, Long> ingred = new HashMap<>();
        for (Map.Entry<String, Long> entry : ingredients.entrySet()) {
            ingred.put(entry.getKey(), entry.getValue() * factor);
        }
        return ingred;
    }

    @Override
    public String toString() {
        String ingredientString = ingredients.entrySet().stream()
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.joining(", "));
        return String.format("%s => %s %s", ingredientString, outputQuantity, outputType);
    }

}

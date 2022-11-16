package com.recette;

public class Contenir {

    private Ingredient ingredient;
    private Recipe recipe;

    public Contenir(Ingredient ingredient,  Recipe recipe) {
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

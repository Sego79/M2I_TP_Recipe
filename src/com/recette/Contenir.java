package com.recette;

public class Contenir {

    private int idIngredient;
    private int idRecipe;

    public Contenir(int idIngredient, int idRecipe) {
        this.setIdIngredient(idIngredient);
        this.setIdRecipe(idRecipe);
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Override
    public String toString() {
        return "Contenir{" +
                "idIngredient=" + idIngredient +
                ", idRecipe=" + idRecipe +
                '}';
    }
}

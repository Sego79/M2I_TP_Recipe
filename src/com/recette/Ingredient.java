package com.recette;

public class Ingredient {

    private int idIngredient;
    private String nameIngredient;
    private int qty;
    private String measureQty;

    @Override
    public String toString() {
        return "Ingredient{" +
                "nameIngredient='" + nameIngredient + '\'' +
                ", qty=" + qty +
                ", measureQty='" + measureQty + '\'' +
                '}';
    }


    public Ingredient() {
        this.setNameIngredient(nameIngredient);
        this.setQty(qty);
        this.setMeasureQty(measureQty);
    }

    public Ingredient( String nameIngredient, int qty, String measureQty) {
        this.setNameIngredient(nameIngredient);
        this.setQty(qty);
        this.setMeasureQty(measureQty);
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMeasureQty() {
        return measureQty;
    }

    public void setMeasureQty(String measureQty) {
        this.measureQty = measureQty;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }
}

package com.recette;

import java.time.LocalDate;
import java.util.List;

public class Recipe{

    private int idRecipe;
    private String context;
    private String theme;
    private int opinion;
    private String nameRecipe;
    private LocalDate dateRecipe;
    private String descriptionRecipe;
    private int duration;
    private int nbPeople;
    private int idUser;

    public Recipe() {
    }
    public Recipe(int idRecipe, String context, String theme, int opinion, String nameRecipe, String descriptionRecipe, int duration, int nbPeople, int idUser) {
        this.idRecipe = idRecipe;
        this.context = context;
        this.theme = theme;
        this.opinion = opinion;
        this.nameRecipe = nameRecipe;
        this.descriptionRecipe = descriptionRecipe;
        this.duration = duration;
        this.nbPeople = nbPeople;
        this.idUser = idUser;
    }
    public Recipe( String context, String theme, int opinion, String nameRecipe, String descriptionRecipe, int duration, int nbPeople, int idUser) {

        this.context = context;
        this.theme = theme;
        this.opinion = opinion;
        this.nameRecipe = nameRecipe;
        this.descriptionRecipe = descriptionRecipe;
        this.duration = duration;
        this.nbPeople = nbPeople;
        this.idUser = idUser;



    }
    public Recipe(int idRecipe, String context, String theme, int opinion, String nameRecipe, LocalDate dateRecipe, String descriptionRecipe, int duration, int nbPeople, int idUser) {
        this.idRecipe = idRecipe;
        this.context = context;
        this.theme = theme;
        this.opinion = opinion;
        this.nameRecipe = nameRecipe;
        this.descriptionRecipe = descriptionRecipe;
        this.dateRecipe = dateRecipe;
        this.duration = duration;
        this.nbPeople = nbPeople;
        this.idUser = idUser;
    }
    public Recipe(String context, String theme, int opinion, String nameRecipe, LocalDate dateRecipe, String descriptionRecipe, int duration, int nbPeople, int idUser) {
        this.context = context;
        this.theme = theme;
        this.opinion = opinion;
        this.nameRecipe = nameRecipe;
        this.descriptionRecipe = descriptionRecipe;
        this.dateRecipe = dateRecipe;
        this.duration = duration;
        this.nbPeople = nbPeople;
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "context='" + context + '\'' +
                ", theme='" + theme + '\'' +
                ", opinion=" + opinion +
                ", nameRecipe='" + nameRecipe + '\'' +
                ", descriptionRecipe='" + descriptionRecipe + '\'' +
                ", duration=" + duration +
                ", nbPeople=" + nbPeople +
                '}';
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public LocalDate getDateRecipe() {
        return dateRecipe;
    }

    public String setDateRecipe(LocalDate dateRecipe) {
        this.dateRecipe = dateRecipe.now();
        return null;
    }

    public String getDescriptionRecipe() {
        return descriptionRecipe;
    }

    public void setDescriptionRecipe(String descriptionRecipe) {
        this.descriptionRecipe = descriptionRecipe;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(int nbPeople) {
        this.nbPeople = nbPeople;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

//    static void createIngredientInRecipe(Recipe nameRecipe, String nameIngredient, int qty, String measureQty ){
//        Ingredient ingredient = new Ingredient(nameIngredient, qty,measureQty);
//        Contenir contenir = new Contenir(ingredient,  nameRecipe);
//        contenir.getIngredient();
//        ingredient.getNameIngredient();
//        System.out.println(ingredient.getNameIngredient());
//    }

//    static void findIngredientInRecipe(Recipe recipe) {
//        Ingredient ingredient = new Ingredient(nameIngredient, qty, measureQty);
//        Contenir contenir = new Contenir(ingredient, recipe);
//        contenir.getIngredient();
//        ingredient.getNameIngredient();
//        System.out.println(ingredient.getNameIngredient());
//    }
}

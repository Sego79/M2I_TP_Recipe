package com.recette;

public class DaoFactory {

    private DaoFactory() {
    }

    public static CrudDao<Recipe> getRecipeDao() {
        return new RecipeJbdcDao();
    }
    public static CrudDaoIngredient<Ingredient> getIngredientDao() {
        return new IngredientJdbcDao();
    }
    public static CrudDaoContenir<Contenir> getContenirDao() {
        return new ContenirJdbcDao();
    }
}

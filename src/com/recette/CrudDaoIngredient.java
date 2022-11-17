package com.recette;

import java.sql.SQLException;
import java.util.List;

public interface CrudDaoIngredient<T> {

    int findIngredientById(T element);
    List<T> SelectIngredientByRecipe() throws SQLException;

    void delete(String nameRecipe) throws SQLException;

    T update(Ingredient ingredient) throws SQLException;

    T create(Ingredient element) throws SQLException;

    List<T> findIngredientFromRecipe(Recipe recipe);
}

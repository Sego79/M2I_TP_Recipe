package com.recette;

import java.sql.SQLException;
import java.util.List;

public interface CrudDaoIngredient<T> {

    List<T> SelectIngredientByRecipe() throws SQLException;

    //List<T> findAll();

    void delete(String nameRecipe) throws SQLException;

    T update(Ingredient ingredient) throws SQLException;

    T create(Ingredient element) throws SQLException;
}

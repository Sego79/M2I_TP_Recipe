package com.recette;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<E> {

    int findRecetteById(E element);
    List<Integer> findIdIngredientFromRecipe(Recipe recipeName);
    List<E> findAll();

    List<E> findByTheme(String theme) throws SQLException;

    List<Recipe> findRecipePlus6Days() throws SQLException;

    void delete(String nameRecipe) throws SQLException;

    E update(E element) throws SQLException;

    E create(E element) throws SQLException;

    E findRecetteByName(String nomRecette);


}

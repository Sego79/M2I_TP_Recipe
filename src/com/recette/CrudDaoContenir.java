package com.recette;

import java.sql.SQLException;

public interface CrudDaoContenir<E> {

    void Create(int idRecipe, int idIngredient) throws SQLException;
}


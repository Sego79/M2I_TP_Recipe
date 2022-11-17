package com.recette;

import java.sql.*;

public class ContenirJdbcDao implements CrudDaoContenir<Contenir> {


    public void Create(int idRecipe, int idIngredient) throws SQLException {
        String queryCreateContenir = " INSERT INTO contenir(idRecipe, idIngredients) VALUES (?, ?); ";

        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement prepareStatement = connection.prepareStatement(queryCreateContenir)) {

            prepareStatement.setInt(1, idRecipe);
            prepareStatement.setInt(2, idIngredient);
            int resultSet = prepareStatement.executeUpdate();

            if (resultSet >0) {
                connection.commit();
                //mapToIngredient(resultSet).toString();
                System.out.println("Creation succesfully occured");
            }

        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Error Query");
            e.printStackTrace();
        }
    }

}

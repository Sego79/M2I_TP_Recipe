package com.recette;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientJdbcDao implements CrudDaoIngredient<Ingredient>{

    private static List<Integer> idIngredientFromRecipee;

    @Override
    public int findIngredientById(Ingredient ingredient) {
        int idIngredient = 0;
        String queryFindIngredientById = "SELECT ingredients.idIngredients FROM ingredients WHERE nameIngredient=?;";
        Connection connection = ConnectionManager.getConnectionInstance();

        try(PreparedStatement prepareStatement = connection.prepareStatement(queryFindIngredientById)) {
            prepareStatement.setString(1, ingredient.getNameIngredient());
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()){
                idIngredient = rs.getInt("idIngredients");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idIngredient;
    }
    @Override
    public List<Ingredient> findIngredientFromRecipe(Recipe recipe) {
        List<Ingredient> listFindIngredientFromRecipe = new ArrayList();
        String queryfindIngredientFromRecipe = "" +
                "SELECT nameIngredient, quantity, measureUnity " +
                "FROM recipe " +
                "INNER JOIN contenir " +
                "ON contenir.idRecipe = recipe.idRecipe " +
                "INNER JOIN ingredients " +
                "ON contenir.idIngredients = ingredients.idIngredients " +
                "WHERE contenir.idRecipe = ?";

        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement st = connection.prepareStatement(queryfindIngredientFromRecipe)) {
            st.setInt(1, recipe.getIdRecipe());
            ResultSet rs = st.executeQuery();
            while (rs.next() == true) {
                Ingredient ingredient = new Ingredient(
                        rs.getString("nameIngredient"),
                        rs.getInt("quantity"),
                        rs.getString("measureUnity"));
               listFindIngredientFromRecipe.add(ingredient);
            }
        } catch (SQLException e) {
            System.out.println("Probleme selection ingredient");
            throw new RuntimeException(e);
        }
        listFindIngredientFromRecipe.forEach(element -> System.out.println(element));
        return listFindIngredientFromRecipe;
    }
    @Override
    public List<Ingredient> SelectIngredientByRecipe() throws SQLException {
        return null;
    }
    @Override
    public void delete(String nameIngredient) throws SQLException {
        String queryDeleteIngredient = "DELETE FROM ingredients WHERE nameIngredient=?";
        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement st = connection.prepareStatement(queryDeleteIngredient)) {
            st.setString(1, nameIngredient);
            st.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    @Override
    public Ingredient update(Ingredient ingredient) throws SQLException {
        String query = "UPDATE ingredients SET nameIngredient=?, quantity=?, measureUnity=?";
        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, ingredient.getNameIngredient());
            pst.setInt(2, ingredient.getQty());
            pst.setString(3, ingredient.getMeasureQty());
            int rows = pst.executeUpdate();
            connection.commit();
            if (rows > 0) {
                System.out.println("Recipe clearly updated !");
                return ingredient;
            }
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
            System.out.println("Something went wrong when updating a Ingredient !");
        }
        return ingredient;
    }

    @Override
    public Ingredient create(Ingredient element) throws SQLException {
        String queryCreateIngredient = " INSERT INTO ingredients(nameIngredient, quantity, measureUnity) " +
                "VALUES (?, ?, ?)";

        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement prepareStatement = connection.prepareStatement(queryCreateIngredient, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, element.getNameIngredient());
            prepareStatement.setInt(2, element.getQty());
            prepareStatement.setString(3, element.getMeasureQty());

            prepareStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = 0;
                id = resultSet.getInt(1);
                prepareStatement.setInt(1, id);
                System.out.println("Creation succesfully occured");
            }

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.err.println("Error Query");
        }
        return element;
    }

    private Ingredient mapToIngredient(ResultSet rs) throws SQLException {
        Ingredient ingredient = new Ingredient(
                rs.getString("nameIngredient"),
                rs.getInt("quantity"),
                rs.getString("measureUnity"));
        return ingredient;
    }

    public List<Integer> getIdIngredientFromRecipe() {
        return idIngredientFromRecipee;
    }

    public static void setIdIngredientFromRecipe(List<Integer> idIngredientFromRecipe) {
        idIngredientFromRecipee = idIngredientFromRecipe;
    }
}

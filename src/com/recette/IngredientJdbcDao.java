package com.recette;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientJdbcDao implements CrudDaoIngredient<Ingredient>{

    private static List<Integer> idIngredientFromRecipee;



    @Override
    public List<Ingredient> SelectIngredientByRecipe() throws SQLException {
        List<Ingredient> listIngredient = new ArrayList();
        String querySelectIngredientByRecipe = """       
                SELECT *\s
                FROM ingredients\s
                WHERE ingredients.idIngredients = ?;""";
        Connection connection = ConnectionManager.getConnectionInstance();

        for (int i = 0; i <= idIngredientFromRecipee.size()-1; i++) {
            try (PreparedStatement st = connection.prepareStatement(querySelectIngredientByRecipe)) {
                st.setInt(1, getIdIngredientFromRecipe().get(i));

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listIngredient.add(mapToIngredient(rs));
                }

                //connection.commit();
            } catch (SQLException e) {
                //connection.rollback();
                e.printStackTrace();
            }
        }
        listIngredient.forEach((element -> System.out.println(element)));
        return listIngredient;
    }

//    @Override
//    public List<Ingredient> findAll() {
//        List<Ingredient> listIngredient = new ArrayList();
//        String queryFindAll = "SELECT * FROM ingredients";
////        Connection connection = ConnectionManager.getConnectionInstance();
//
//        try(Statement statement = connection.createStatement()) {
//            ResultSet rs = statement.executeQuery(queryFindAll);
//            while (rs.next() == true) {
//                listIngredient.add(mapToIngredient(rs));
//            }
//            listIngredient.forEach(element-> System.out.println(element.toString()));
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return listIngredient;
//    }

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

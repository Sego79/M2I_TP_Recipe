package com.recette;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecipeJbdcDao implements CrudDao<Recipe>{

    private LocalDate DateSelection;


    // Methodes pour les recettes

    public Recipe findRecetteByName(String nameRecipe) {
        Recipe recipeByName = new Recipe();
        String queryFindRecetteByName = "SELECT idRecipe, context, theme, opinion, nameRecipe, descriptionRecipe, duration, nbPeople, idUser FROM recipe WHERE nameRecipe=?";
        Connection connection = ConnectionManager.getConnectionInstance();

        try(PreparedStatement prepareStatement = connection.prepareStatement(queryFindRecetteByName)) {
            prepareStatement.setString(1,nameRecipe);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                recipeByName =  mapToRecipeWithoutDate(rs);
            }
       } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(recipeByName.toString() +", isUser =" + recipeByName.getIdUser());
        return recipeByName;
    }

//    public List<Recipe> findAllName() {
//        List<Recipe> listRecipe = new ArrayList();
//        String queryFindAll = "SELECT nameRecipe FROM recipe";
//        Connection connection = ConnectionManager.getConnectionInstance();
//
//        try(Statement statement = connection.createStatement()) {
//            ResultSet rs = statement.executeQuery(queryFindAll);
//            while (rs.next() == true) {
//                listRecipe.add(mapToRecipe(rs));
//            }
//            listRecipe.forEach(element-> System.out.println(element.toString()));
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return listRecipe;
//    }

    @Override
    public List<Integer> findIdIngredientFromRecipe(Recipe recipe) {
        List<Integer> listFindIdIngredientFromRecipe = new ArrayList();
        String queryFindAll = """
                SELECT contenir.idIngredients\s
                FROM recipe\s
                INNER JOIN contenir\s
                ON contenir.idRecipe = ?;                                           
                """;
        Connection connection = ConnectionManager.getConnectionInstance();
        try(PreparedStatement st = connection.prepareStatement(queryFindAll)) {
            st.setInt(1, recipe.getIdRecipe());
            ResultSet rs = st.executeQuery();
            System.out.println(listFindIdIngredientFromRecipe.size());
            while (rs.next() == true) {
                listFindIdIngredientFromRecipe.add(rs.getInt("idIngredients"));
                System.out.println(listFindIdIngredientFromRecipe.size());
            }
            System.out.println(listFindIdIngredientFromRecipe.size());

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        listFindIdIngredientFromRecipe.forEach(element-> System.out.println(element)   );
        return listFindIdIngredientFromRecipe;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> listRecipe = new ArrayList();
        String queryFindAll = "SELECT * FROM recipe";
        Connection connection = ConnectionManager.getConnectionInstance();

        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(queryFindAll);
            while (rs.next() == true) {
                    listRecipe.add(mapToRecipe(rs));
            }
            listRecipe.forEach(element-> System.out.println(element.toString()));
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return listRecipe;
    }

    public List<Recipe> findRecipePlus6Days() throws SQLException {
        List<Recipe> listRecipePlus6Days = new ArrayList();
        String queryFindRecipePlus6Days = "SELECT * FROM recipe WHERE dateRecipe>?";

        Connection connection = ConnectionManager.getConnectionInstance();

        try(PreparedStatement prepareStatement = connection.prepareStatement(queryFindRecipePlus6Days)) {
            prepareStatement.setString(1, (LocalDate.now().minusDays(6)).toString());
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                listRecipePlus6Days.add(mapToRecipe(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        listRecipePlus6Days.forEach(element -> System.out.println(element));
        return listRecipePlus6Days;
    }
    @Override
    public Recipe create(Recipe element) throws SQLException {
        String queryCreateRecipe = " INSERT INTO recipe(context, theme, opinion, nameRecipe, descriptionRecipe, duration, nbPeople, idUser) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement prepareStatement = connection.prepareStatement(queryCreateRecipe, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, element.getContext());
            prepareStatement.setString(2, element.getTheme());
            prepareStatement.setInt(3, element.getOpinion());
            prepareStatement.setString(4, element.getNameRecipe());
            prepareStatement.setDate(5, Date.valueOf(DateSelection.now()));
            prepareStatement.setString(5, element.getDescriptionRecipe());
            prepareStatement.setInt(6, element.getDuration());
            prepareStatement.setInt(7, element.getNbPeople());
            prepareStatement.setInt(8, element.getIdUser());

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

    @Override
    public List <Recipe> findByTheme(String theme) throws SQLException {
            List<Recipe> listRecipeTheme= new ArrayList();
            String querySelectByTheme = "SELECT * FROM recipe WHERE theme=?";
            Connection connection = ConnectionManager.getConnectionInstance();

            try (PreparedStatement st = connection.prepareStatement(querySelectByTheme)) {
                st.setString(1, theme);
                ResultSet rs = st.executeQuery();
                connection.commit();
                   if (rs.next()) {
                       listRecipeTheme.add(mapToRecipe(rs));
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();

            }
            listRecipeTheme.forEach(element -> System.out.println(element));
            return listRecipeTheme;
    }

    @Override
    public void delete(String nameRecipe) throws SQLException {
        String queryDeleteRecipe = "DELETE FROM recipe WHERE nameRecipe=?";
        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement st = connection.prepareStatement(queryDeleteRecipe)) {
            st.setString(1, nameRecipe);
            st.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    @Override
    public Recipe update(Recipe element) throws SQLException {
        String query = "UPDATE recipe SET context=?, theme=?, opinion=?, nameRecipe=?, dateRecipe=?, descriptionRecipe=?, duration=?, nbPeople=? WHERE id=?";
        Connection connection = ConnectionManager.getConnectionInstance();
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, element.getContext());
            pst.setString(2, element.getTheme());
            pst.setInt(3, element.getOpinion());
            pst.setString(4, element.getNameRecipe());
            pst.setDate(5, Date.valueOf(element.getDateRecipe()));
            pst.setString(6, element.getDescriptionRecipe());
            pst.setInt(7, element.getDuration());
            pst.setInt(8, element.getNbPeople());
            int rows = pst.executeUpdate();
            connection.commit();
            if (rows > 0) {
                System.out.println("Recipe clearly updated !");
            }
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
            System.out.println("Something went wrong when updating a book !");
        }
        return element;
    }

    private Recipe mapToRecipe(ResultSet rs) throws SQLException {
        Date date = rs.getDate("dateRecipe");
        LocalDate dateRecipe = date != null ? date.toLocalDate() : null;

        Recipe recipe = new Recipe(
                rs.getInt("idRecipe"),
                rs.getString("context"),
                rs.getString("theme"),
                rs.getInt("opinion"),
                rs.getString("nameRecipe"),
                dateRecipe,
                rs.getString("descriptionRecipe"),
                rs.getInt("duration"),
                rs.getInt("nbPeople"),
                rs.getInt("idUser"));
        return recipe;
    }

    private Recipe mapToRecipeWithoutDate(ResultSet rs) throws SQLException {

        Recipe recipe = new Recipe(
                rs.getInt("idRecipe"),
                rs.getString("context"),
                rs.getString("theme"),
                rs.getInt("opinion"),
                rs.getString("nameRecipe"),
                rs.getString("descriptionRecipe"),
                rs.getInt("duration"),
                rs.getInt("nbPeople"),
                rs.getInt("idUser"));
        return recipe;
    }
}
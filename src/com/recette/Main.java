package com.recette;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionManager.loadDriver();
        LocalDate dateNow = LocalDate.now();
        int choixUser;
        String choix="o";

        CrudDao<Recipe> DaoRecipe = DaoFactory.getRecipeDao();
        CrudDaoIngredient<Ingredient> DaoIngredient = DaoFactory.getIngredientDao();
        CrudDaoContenir<Contenir> DaoContenir =  DaoFactory.getContenirDao();

        try {
                    do {
                        choixUser = UtilBapt.askDataInt("""
                                Que voulez vous faire ?
                                1 - Créer une recette
                                2 - Sélectionner une recette
                                3 - Quitter
                                """, 0, 10000);
                        switch (choixUser) {
                            case 1:
                                System.out.println("Vous avez choisi de créer une recette.");
                                System.out.println("--------------------------------------------\n");

                                String context = UtilBapt.askDataString("Quel est le contexte de votre recette ?");

                                String theme = UtilBapt.askDataString("Quel est le theme de votre recette ?");

                                int opinion = UtilBapt.askDataInt("Quelle est l'opinion de votre recette ? 'Note de 1 a 5", 0, 10000);

                                String nameRecipe = UtilBapt.askDataString("Quel est le nom de votre recette ?");

                                String descriptionRecipe = UtilBapt.askDataString("Quelle est la description de votre recette ?");

                                int duration = UtilBapt.askDataInt("Quelle est la durée de votre recette ?", 0, 10000);

                                int nbPeople = UtilBapt.askDataInt("Pour combien de personne est votre recette ?", 0, 10000);

                                Recipe recipe = new Recipe(context, theme, opinion, nameRecipe, dateNow, descriptionRecipe, duration, nbPeople, 1);
                                DaoRecipe.create(recipe);


                                choix = UtilBapt.askDataString("Voulez vous rajouter un ingrédient? 'o/n'");

                                while (!choix.equals("n")) {

                                    String nomIngredient = " ";
                                    int qtyIngredient = 0;
                                    String measureIngredient = " ";

                                    nomIngredient = UtilBapt.askDataString("Nom de l'ingredient ?");

                                    qtyIngredient = UtilBapt.askDataInt("Quantité de l'ingredient ?", 0, 10000);

                                    measureIngredient = UtilBapt.askDataString("Unité de mesure de l'ingredient (g, ml, u) ?");

                                    Ingredient ingredient = new Ingredient(nomIngredient, qtyIngredient, measureIngredient);
                                    DaoIngredient.create(ingredient);
                                    DaoContenir.Create(DaoRecipe.findRecetteById(recipe) , DaoIngredient.findIngredientById(ingredient));
                                choix = UtilBapt.askDataString("Voulez vous rajouter un ingrédient? 'o/n'");
                                }

                                System.out.println("Votre recette et ses ingredients ont bien été rajoutés.");
                                System.out.println("Toutes les recettes dans votre liste:");
                                System.out.println("--------------------------------------------");
                                DaoRecipe.findAll();
                                break;
                            case 2:
                                System.out.println("Vous avez choisi de sélectionner une recette.");
                                System.out.println("--------------------------------------------\n");

                                int choixSelectionRecette = UtilBapt.askDataInt("""
                                        Que voulez vous faire ?
                                        1 - Sélectionner une recette par son nom de recette
                                        2 - Sélectionner une recette par thematique
                                        3 - Sélectionner une recette qui n'a pas été cuisinée depuis 6 jours
                                        4 - Selectionner une recette par son nom avec tous ces ingredients
                                        5 - Quitter
                                        """, 0, 10000);

                                switch (choixSelectionRecette) {
                                    case 1:
                                        String nameRecipe2 = UtilBapt.askDataString("Quel est le nom de votre recette ?");
                                        System.out.println("--------------------------------------------");
                                        DaoRecipe.findRecetteByName(nameRecipe2);
                                        break;
                                    case 2:
                                        String themeRecipe2 = UtilBapt.askDataString("Quelle la thematique de votre recette ?");
                                        System.out.println("--------------------------------------------");
                                        DaoRecipe.findByTheme(themeRecipe2);

                                        String nameRecipe3 = UtilBapt.askDataString("Quelle recette voulez vous choisir parmis celles-ci ?");
                                        System.out.println("--------------------------------------------");
                                        DaoRecipe.findRecetteByName(nameRecipe3);
                                        break;
                                    case 3:
                                        System.out.println("Les recettes qui n'ont pas été cuisinées depuis 6 jours: ");
                                        System.out.println("--------------------------------------------");
                                        DaoRecipe.findRecipePlus6Days();

                                        String nameRecipe4 = UtilBapt.askDataString("Quelle recette voulez vous choisir parmis celles-ci ?");
                                        System.out.println("--------------------------------------------");
                                        DaoRecipe.findRecetteByName(nameRecipe4);
                                        break;
                                    case 4:
                                        String nameRecipe5 = UtilBapt.askDataString("Quel est le nom de votre recette ?");
                                        System.out.println("--------------------------------------------");
                                        Recipe recipeName = DaoRecipe.findRecetteByName(nameRecipe5);
                                        DaoIngredient.findIngredientFromRecipe(recipeName);


                                    default:
                                        System.out.println("Vous avez quitté la sélection de recettes.");
                                        System.out.println("--------------------------------------------");
                                }
                            default:
                                System.out.println("Vous avez quitté");
                                System.out.println("--------------------------------------------");
                        }
                    } while (choixUser != 3);
                }catch(Exception e){
                    System.err.println("Vous n'avez pas fait le bon choix");
                }

       ConnectionManager.closeConnection();

    }
}

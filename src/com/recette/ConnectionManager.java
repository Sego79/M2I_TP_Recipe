package com.recette;

import java.sql.*;

public class ConnectionManager {
    private static Connection CONNECTION_INSTANCE;

    private final static String URL = "jdbc:mysql://localhost:3306/recipesegolebaptiste?serverTimezone=UTC";

    private ConnectionManager(){

    }

    static void loadDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnectionInstance() {
        if(CONNECTION_INSTANCE == null){
            try {
                loadDriver();
                CONNECTION_INSTANCE = DriverManager.getConnection(URL, "root", "");
                CONNECTION_INSTANCE.setAutoCommit(false);
            } catch (SQLException e) {
                System.err.println("Connexion impossible");
            }
        }
        return CONNECTION_INSTANCE;
    }

    public static void closeConnection(){
        try {
            CONNECTION_INSTANCE.close();
        } catch (Exception e) {
            System.err.println("Fermeture de la connexion impossible");
        }
    }
}


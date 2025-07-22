package com.luis.gvp.infra.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    private static final String DB_URL = "jdbc:sqlite:gestor_vestuario.db";

    public static Connection createConnection() {
        try {
            // O driver do SQLite é carregado automaticamente ao adicionar o JAR
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("FATAL: Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
package com.luis.gvp.infra.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Importe a classe Statement

public class ConnectionFactory {
    private static final String DB_URL = "jdbc:sqlite:gestor_vestuario.db";

    public static Connection createConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            
            // --- LINHAS NOVAS E IMPORTANTES ---
            // Habilita o suporte a chaves estrangeiras e o ON DELETE CASCADE
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }
            // ------------------------------------
            
            return conn;
        } catch (SQLException e) {
            System.err.println("FATAL: Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
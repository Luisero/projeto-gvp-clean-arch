package com.luis.gvp.infra.ui;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Gestor de Vestuário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // --- Painel principal com layout vertical ---
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Bem-vindo!");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JButton botaoRoupas = new JButton("Minhas Roupas");
        JButton botaoLooks = new JButton("Meus Looks");

        botaoRoupas.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoLooks.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Ações dos botões ---
        botaoRoupas.addActionListener(e -> {
            TelaListagemRoupas tela = new TelaListagemRoupas();
            tela.setVisible(true);
        });

        botaoLooks.addActionListener(e -> {
            TelaListagemLooks tela = new TelaListagemLooks();
            tela.setVisible(true);
        });

        painel.add(titulo);
        painel.add(botaoRoupas);
        painel.add(Box.createVerticalStrut(10)); // Espaço entre botões
        painel.add(botaoLooks);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}

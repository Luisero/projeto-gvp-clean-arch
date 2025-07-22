package com.luis.gvp.infra.ui;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaListagemRoupas extends JFrame {

    private JTable tabelaRoupas;
    private DefaultTableModel modeloTabela;
    private ItemRepository itemRepository;

    public TelaListagemRoupas() {
        // --- Configurações básicas da janela ---
        setTitle("Gestor de Vestuário - Minhas Roupas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela

        this.itemRepository = new ItemRepositoryImpl();

        // --- Painel principal com layout ---
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // --- Tabela para listar as roupas ---
        String[] colunas = {"ID", "Descrição", "Tipo", "Cor", "Tamanho", "Estado"};
        modeloTabela = new DefaultTableModel(colunas, 0); // 0 linhas iniciais
        tabelaRoupas = new JTable(modeloTabela);
        
        // Adiciona a tabela a um painel com rolagem
        JScrollPane painelTabela = new JScrollPane(tabelaRoupas);
        painelPrincipal.add(painelTabela, BorderLayout.CENTER);

        // --- Botões de Ação ---
        JPanel painelBotoes = new JPanel();
        JButton botaoCadastrar = new JButton("Cadastrar Nova Roupa");
        JButton botaoAtualizar = new JButton("Atualizar Lista");
        
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoAtualizar);
        
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // --- Adiciona o painel principal à janela ---
        add(painelPrincipal);

        // --- Ações dos Botões (Action Listeners) ---
        botaoAtualizar.addActionListener(e -> carregarDadosNaTabela());


        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria e exibe a tela de cadastro como um pop-up
                TelaCadastroRoupa telaCadastro = new TelaCadastroRoupa(TelaListagemRoupas.this);
                telaCadastro.setVisible(true);

                // IMPORTANTE: Depois que a tela de cadastro fechar,
                // o código continua daqui. Então, atualizamos a tabela!
                carregarDadosNaTabela();
            }
        });

        // --- Carrega os dados iniciais na tabela ---
        carregarDadosNaTabela();
    }

    /**
     * Busca os dados do repositório e os exibe na JTable.
     * Esta função é essencial para a parte de "visualizar itens"[cite: 19].
     */
    private void carregarDadosNaTabela() {
        modeloTabela.setRowCount(0);
        List<Item> itens = this.itemRepository.listarTodos();
        
        // --- ADICIONE ESTA LINHA DE DEBUG ---
        System.out.println("DEBUG: O método listarTodos() encontrou " + itens.size() + " itens no banco.");
        // ------------------------------------

        for (Item item : itens) {
            Object[] linha = {
                item.getId(),
                item.getDescricao(),
                item.getClass().getSimpleName(),
                item.getCor().name(),
                item.getTamanho().name(),
                item.getEstado().name()
            };
            modeloTabela.addRow(linha);
        }
    }

    public static void main(String[] args) {
        // Garante que a UI seja executada na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaListagemRoupas().setVisible(true);
            }
        });
    }
}
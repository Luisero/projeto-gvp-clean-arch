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
//... (importações permanecem as mesmas)

public class TelaListagemRoupas extends JFrame {

 private JTable tabelaRoupas;
 private DefaultTableModel modeloTabela;
 private ItemRepository itemRepository;

 public TelaListagemRoupas() {
     setTitle("Gestor de Vestuário - Minhas Roupas");
     setSize(800, 600);
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Mudei para não fechar o app inteiro
     setLocationRelativeTo(null);

     this.itemRepository = new ItemRepositoryImpl();

     JPanel painelPrincipal = new JPanel(new BorderLayout());

     String[] colunas = {"ID", "Descrição", "Tipo", "Cor", "Tamanho", "Estado"};
     modeloTabela = new DefaultTableModel(colunas, 0);
     tabelaRoupas = new JTable(modeloTabela);

     JScrollPane painelTabela = new JScrollPane(tabelaRoupas);
     painelPrincipal.add(painelTabela, BorderLayout.CENTER);

     JPanel painelBotoes = new JPanel();

     JButton botaoCadastrar = new JButton("Cadastrar Nova Roupa");
     JButton botaoAtualizar = new JButton("Atualizar Lista");
     JButton botaoExcluir = new JButton("Excluir Roupa Selecionada");
     JButton botaoVoltar = new JButton("Voltar à Tela Inicial"); // <- Novo botão

     painelBotoes.add(botaoCadastrar);
     painelBotoes.add(botaoAtualizar);
     painelBotoes.add(botaoExcluir);
     painelBotoes.add(botaoVoltar); // <- Adicionado ao painel

     painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
     add(painelPrincipal);

     botaoAtualizar.addActionListener(e -> carregarDadosNaTabela());

     botaoCadastrar.addActionListener(e -> {
         TelaCadastroRoupa telaCadastro = new TelaCadastroRoupa(TelaListagemRoupas.this);
         telaCadastro.setVisible(true);
         carregarDadosNaTabela();
     });

     botaoExcluir.addActionListener(e -> {
         int linhaSelecionada = tabelaRoupas.getSelectedRow();
         if (linhaSelecionada == -1) {
             JOptionPane.showMessageDialog(this, "Selecione uma roupa para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
             return;
         }

         int idRoupa = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
         String descricaoRoupa = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

         int confirmacao = JOptionPane.showConfirmDialog(this,
                 "Tem certeza que deseja excluir a roupa: " + descricaoRoupa + "?",
                 "Confirmar Exclusão",
                 JOptionPane.YES_NO_OPTION);

         if (confirmacao == JOptionPane.YES_OPTION) {
             itemRepository.deletar(idRoupa);
             carregarDadosNaTabela();
         }
     });

     // --- NOVA AÇÃO do botão Voltar ---
     botaoVoltar.addActionListener(e -> {
         this.dispose(); // Fecha esta janela
         new TelaInicial().setVisible(true); // Abre a tela inicial
     });

     carregarDadosNaTabela();
 }

 private void carregarDadosNaTabela() {
     modeloTabela.setRowCount(0);
     List<Item> itens = this.itemRepository.listarTodos();

     System.out.println("DEBUG: O método listarTodos() encontrou " + itens.size() + " itens no banco.");

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
     SwingUtilities.invokeLater(() -> new TelaListagemRoupas().setVisible(true));
 }
}

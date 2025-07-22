package com.luis.gvp.infra.ui;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.core.repositories.LookRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;
import com.luis.gvp.infra.persistence.LookRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
//... imports existentes ...
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaListagemLooks extends JFrame {

 private JTable tabelaLooks;
 private DefaultTableModel modeloTabela;
 private LookRepository lookRepository;
 private ItemRepository itemRepository;

 public TelaListagemLooks() {
     setTitle("Gestor de Vestuário - Meus Looks");
     setSize(900, 500);
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     setLocationRelativeTo(null);

     itemRepository = new ItemRepositoryImpl();
     this.lookRepository = new LookRepositoryImpl(itemRepository);

     JPanel painelPrincipal = new JPanel(new BorderLayout());

     String[] colunas = {"ID", "Parte Superior", "Parte Inferior", "Parte Íntima", "Acessório"};
     modeloTabela = new DefaultTableModel(colunas, 0);
     tabelaLooks = new JTable(modeloTabela);
     JScrollPane painelTabela = new JScrollPane(tabelaLooks);
     painelPrincipal.add(painelTabela, BorderLayout.CENTER);

     // --- Painel de Botões ---
     JPanel painelBotoes = new JPanel();

     JButton botaoAtualizar = new JButton("Atualizar Lista");
     JButton botaoNovoLook = new JButton("Criar Novo Look");
     JButton botaoVoltar = new JButton("Voltar à Tela Inicial");

     painelBotoes.add(botaoNovoLook);
     painelBotoes.add(botaoAtualizar);
     painelBotoes.add(botaoVoltar);
     painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

     add(painelPrincipal);

     // --- Ações ---
     botaoAtualizar.addActionListener(e -> carregarLooksNaTabela());

     botaoNovoLook.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             TelaCadastroLook telaCadastro = new TelaCadastroLook(TelaListagemLooks.this);
             telaCadastro.setVisible(true);  // Bloqueia até fechar
             carregarLooksNaTabela(); // Atualiza após possível inserção
         }
     });

     botaoVoltar.addActionListener(e -> {
         dispose(); // Fecha essa tela
         new TelaInicial().setVisible(true); // Abre tela inicial
     });

     carregarLooksNaTabela();
 }

 private void carregarLooksNaTabela() {
     modeloTabela.setRowCount(0);

     for (int id = 1; id < 1000; id++) {
         Look look = lookRepository.buscarPorId(id);
         if (look == null) continue;

         modeloTabela.addRow(new Object[]{
                 look.getIdLook(),
                 look.getParteSuperior().getDescricao(),
                 look.getParteInferior().getDescricao(),
                 look.getParteIntima().getDescricao(),
                 look.getAcessorio() != null ? look.getAcessorio().getDescricao() : "Nenhum"
         });
     }
 }
}

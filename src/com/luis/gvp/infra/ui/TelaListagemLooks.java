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

public class TelaListagemLooks extends JFrame {

    private JTable tabelaLooks;
    private DefaultTableModel modeloTabela;
    private LookRepository lookRepository;

    public TelaListagemLooks() {
        setTitle("Gestor de Vestuário - Meus Looks");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Não fecha o app inteiro
        setLocationRelativeTo(null);

        ItemRepository itemRepository = new ItemRepositoryImpl(); // Necessário para LookRepo
        this.lookRepository = new LookRepositoryImpl(itemRepository);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        String[] colunas = {"ID", "Parte Superior", "Parte Inferior", "Parte Íntima", "Acessório"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaLooks = new JTable(modeloTabela);

        JScrollPane painelTabela = new JScrollPane(tabelaLooks);
        painelPrincipal.add(painelTabela, BorderLayout.CENTER);

        JButton botaoAtualizar = new JButton("Atualizar Lista");
        botaoAtualizar.addActionListener(e -> carregarLooksNaTabela());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAtualizar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        carregarLooksNaTabela();
    }

    private void carregarLooksNaTabela() {
        modeloTabela.setRowCount(0);

        // Supondo que você ainda não tenha "listarTodos" no LookRepository
        // Aqui seria ideal se tivesse esse método, mas por enquanto, podemos simular manualmente
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaListagemLooks().setVisible(true));
    }
}

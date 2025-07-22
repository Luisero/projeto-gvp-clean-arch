package com.luis.gvp.infra.ui;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.tiposDeRoupas.*;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.core.repositories.LookRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;
import com.luis.gvp.infra.persistence.LookRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastroLook extends JDialog {

    private JComboBox<RoupaSuperior> comboSuperior;
    private JComboBox<RoupaInferior> comboInferior;
    private JComboBox<RoupaIntima> comboIntima;
    private JComboBox<Acessorio> comboAcessorio;

    private ItemRepository itemRepository;
    private LookRepository lookRepository;

    public TelaCadastroLook(JFrame parent) {
        super(parent, "Montar Novo Look", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);

        itemRepository = new ItemRepositoryImpl();
        lookRepository = new LookRepositoryImpl(itemRepository);

        setLayout(new GridLayout(6, 1, 10, 10));

        // --- Criar ComboBoxes ---
        comboSuperior = new JComboBox<>();
        comboInferior = new JComboBox<>();
        comboIntima = new JComboBox<>();
        comboAcessorio = new JComboBox<>();

        // --- Popular os combos ---
        carregarRoupas();

        add(new JLabel("Parte Superior:"));
        add(comboSuperior);

        add(new JLabel("Parte Inferior:"));
        add(comboInferior);

        add(new JLabel("Roupa Íntima:"));
        add(comboIntima);

        add(new JLabel("Acessório:"));
        add(comboAcessorio);

        JButton botaoSalvar = new JButton("Salvar Look");
        botaoSalvar.addActionListener(e -> salvarLook());
        add(botaoSalvar);
    }

    private void carregarRoupas() {
        List<Item> itens = itemRepository.listarTodos();

        for (Item item : itens) {
            if (item instanceof RoupaSuperior rs) comboSuperior.addItem(rs);
            else if (item instanceof RoupaInferior ri) comboInferior.addItem(ri);
            else if (item instanceof RoupaIntima rin) comboIntima.addItem(rin);
            else if (item instanceof Acessorio ass) comboAcessorio.addItem(ass);
        }
    }

    private void salvarLook() {
        RoupaSuperior rs = (RoupaSuperior) comboSuperior.getSelectedItem();
        RoupaInferior ri = (RoupaInferior) comboInferior.getSelectedItem();
        RoupaIntima rin = (RoupaIntima) comboIntima.getSelectedItem();
        Acessorio ass = (Acessorio) comboAcessorio.getSelectedItem();

        if (rs == null || ri == null || rin == null || ass == null) {
            JOptionPane.showMessageDialog(this, "Selecione todas as peças!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ID fictício (o banco atribuirá o verdadeiro)
        Look look = new Look(0, rs, ri, rin, null);
        look.setAcessorio(ass);

        lookRepository.salvar(look);

        JOptionPane.showMessageDialog(this, "Look salvo com sucesso!");
        dispose();
    }
}

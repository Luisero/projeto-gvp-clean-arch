package com.luis.gvp.infra.ui;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.modeloRoupas.*;
import com.luis.gvp.core.entities.modeloRoupas.acessorios.Relogio;
import com.luis.gvp.core.entities.modeloRoupas.roupaInferior.Bermuda;
import com.luis.gvp.core.entities.modeloRoupas.roupaInferior.Calca;
import com.luis.gvp.core.entities.modeloRoupas.roupaIntima.Calcinha;
import com.luis.gvp.core.entities.modeloRoupas.roupaIntima.Cueca;
import com.luis.gvp.core.entities.modeloRoupas.roupaSuperior.Camisa;
import com.luis.gvp.core.entities.modeloRoupas.roupaSuperior.Regata;
import com.luis.gvp.core.entities.roupasCasosDeUso.cadastrarRoupaCasoDeUsoImpl;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroRoupa extends JDialog {

    // Campos do formulário
    private JComboBox<String> comboTipo;
    private JTextField campoDescricao;
    private JComboBox<Cores> comboCor;
    private JComboBox<Tamanho> comboTamanho;
    private JComboBox<EstadoConservacao> comboEstado;
    private JTextField campoLoja;

    private ItemRepository itemRepository;

    public TelaCadastroRoupa(Frame owner) {
        // Configura o JDialog para ser um pop-up modal
        super(owner, "Cadastrar Nova Roupa", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        this.itemRepository = new ItemRepositoryImpl();

        // --- Painel do Formulário com GridLayout ---
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Campos e Labels ---
       
        painelFormulario.add(new JLabel("Tipo de Roupa:"));
        comboTipo = new JComboBox<>(new String[]{"Camisa", "Calca", "Regata", "Bermuda", "Calcinha","Cueca","Relogio"}); // Adicione outros tipos aqui
        painelFormulario.add(comboTipo);
        
        painelFormulario.add(new JLabel("Descrição:"));
        campoDescricao = new JTextField();
        painelFormulario.add(campoDescricao);

        painelFormulario.add(new JLabel("Cor:"));
        comboCor = new JComboBox<>(Cores.values());
        painelFormulario.add(comboCor);

        painelFormulario.add(new JLabel("Tamanho:"));
        comboTamanho = new JComboBox<>(Tamanho.values());
        painelFormulario.add(comboTamanho);

        painelFormulario.add(new JLabel("Estado de Conservação:"));
        comboEstado = new JComboBox<>(EstadoConservacao.values());
        painelFormulario.add(comboEstado);
        
        painelFormulario.add(new JLabel("Loja de Origem:"));
        campoLoja = new JTextField();
        painelFormulario.add(campoLoja);

        add(painelFormulario, BorderLayout.CENTER);

        // --- Botão de Salvar ---
        JButton botaoSalvar = new JButton("Salvar");
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoSalvar);
        add(painelBotao, BorderLayout.SOUTH);

        // --- Ação do Botão Salvar ---
        botaoSalvar.addActionListener(e -> salvarRoupa());
    }

    private void salvarRoupa() {
        // 1. Pega os valores dos campos
        String tipoSelecionado = (String) comboTipo.getSelectedItem();
        String descricao = campoDescricao.getText();
        Cores cor = (Cores) comboCor.getSelectedItem();
        Tamanho tamanho = (Tamanho) comboTamanho.getSelectedItem();
        EstadoConservacao estado = (EstadoConservacao) comboEstado.getSelectedItem();
        LojaDeOrigem loja = new LojaDeOrigem(campoLoja.getText());

        // Validação simples
        if (descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição não pode ser vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item novoItem = null;
        
        // 2. Cria o objeto do tipo correto
        switch (tipoSelecionado) {
        case "Camisa":
            // No futuro, você pode salvar e ler dados específicos da camisa
            novoItem = new Camisa(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Calca":
        	novoItem = new Calca(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Regata":
        	novoItem = new Regata(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Bermuda":
        	novoItem = new Bermuda(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Calcinha":
        	novoItem = new Calcinha(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Cueca":
        	novoItem = new Cueca(descricao, cor, tamanho, loja, estado, "default");
            break;
        case "Relogio":
        	novoItem = new Relogio(descricao, cor, tamanho, loja, estado, "default");
            break;
        // Adicione um 'case' para cada tipo de roupa que você tiver
    }

        
        if (novoItem != null) {
            
            cadastrarRoupaCasoDeUsoImpl cadastrarCase = new cadastrarRoupaCasoDeUsoImpl(itemRepository);
            cadastrarCase.execute(novoItem);
            JOptionPane.showMessageDialog(this, "Roupa cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // 4. Fecha a janela de cadastro
            dispose();
        }
    }
}
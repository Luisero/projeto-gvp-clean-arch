package com.luis.gvp.infra.persistence;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.modeloRoupas.roupaInferior.Bermuda;
import com.luis.gvp.core.entities.modeloRoupas.roupaInferior.Calca;
import com.luis.gvp.core.entities.modeloRoupas.roupaIntima.Calcinha;
import com.luis.gvp.core.entities.modeloRoupas.roupaIntima.Cueca;
import com.luis.gvp.core.entities.modeloRoupas.roupaSuperior.Camisa;
import com.luis.gvp.core.entities.modeloRoupas.roupaSuperior.Regata;
import com.luis.gvp.core.repositories.ItemRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Esta classe é a IMPLEMENTAÇÃO. Ela diz COMO fazer.
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public void salvar(Item item) {
        String sql = "INSERT INTO TB_ITEM (DESCRICAO, COR, TAMANHO, LOJA_ORIGEM, ESTADO_CONSERVACAO, CAMINHO_FOTO, TIPO_ROUPA) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Obtém uma conexão usando a factory que você já criou
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Preenche os '?' do SQL com os dados do objeto
            pstmt.setString(1, item.getDescricao());
            pstmt.setString(2, item.getCor().name());
            pstmt.setString(3, item.getTamanho().name());
            pstmt.setString(4, item.getNomeLojaOrigem());
            pstmt.setString(5, item.getEstado().name());
            pstmt.setString(6, item.getCaminhoFoto());
            pstmt.setString(7, item.getClass().getSimpleName()); // Guarda "Camisa", "Calca", etc.

            // Executa o comando
            pstmt.executeUpdate();
            
            System.out.println("SUCESSO: Item '" + item.getDescricao() + "' foi salvo no banco de dados.");

        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao salvar o item: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Item buscarPorId(int id) {
        String sql = "SELECT * FROM TB_ITEM WHERE ID = ?";
        Item itemEncontrado = null;

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Define o ID que estamos procurando

            try (ResultSet rs = pstmt.executeQuery()) {
                // Se rs.next() for true, encontramos uma linha
                if (rs.next()) {
                    // Usamos um método auxiliar para transformar a linha em objeto
                    itemEncontrado = extrairItemDoResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao buscar o item com ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
        return itemEncontrado; // Retorna o objeto ou null se não encontrou
    }

    // MÉTODO AUXILIAR PRIVADO: Para não repetir código
    // Este método lê uma linha do ResultSet e cria o objeto Item correspondente.
    private Item extrairItemDoResultSet(ResultSet rs) throws SQLException {
        int itemId = rs.getInt("ID");
        String descricao = rs.getString("DESCRICAO");
        String tipoRoupa = rs.getString("TIPO_ROUPA");
        
        Cores cor = Cores.valueOf(rs.getString("COR"));
        Tamanho tamanho = Tamanho.valueOf(rs.getString("TAMANHO"));
        EstadoConservacao estado = EstadoConservacao.valueOf(rs.getString("ESTADO_CONSERVACAO"));
        
        LojaDeOrigem lojaOrigem = new LojaDeOrigem(rs.getString("LOJA_ORIGEM"));
        String caminhoFoto = rs.getString("CAMINHO_FOTO");

        Item item = null;
        // A "mágica" acontece aqui: decidimos qual objeto criar
        switch (tipoRoupa) {
            case "Camisa":
                // No futuro, você pode salvar e ler dados específicos da camisa
                item = new Camisa(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
                break;
            case "Calca":
            	item = new Calca(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
                break;
            case "Regata":
            	item = new Regata(descricao, cor, tamanho, lojaOrigem, estado, "default");
                break;
            case "Bermuda":
            	item = new Bermuda(descricao, cor, tamanho, lojaOrigem, estado, "default");
                break;
            case "Cueca":
            	item = new Cueca(descricao, cor, tamanho, lojaOrigem, estado, "default");
                break;
            case "Calcinha":
            	item = new Calcinha(descricao, cor, tamanho, lojaOrigem, estado, "default");
                break;
            // Adicione um 'case' para cada tipo de roupa que você tiver
        }
        
        if (item != null) {
            item.setId(itemId); // Não esqueça de setar o ID!
        }
        return item;
    }

    @Override
    public List<Item> listarTodos() {
        String sql = "SELECT * FROM TB_ITEM";
        List<Item> itens = new ArrayList<>(); // Cria uma lista vazia

        try (Connection conn = ConnectionFactory.createConnection();
             // Como o SQL não tem '?', podemos usar um Statement simples
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // A diferença chave: usamos 'while' para percorrer TODAS as linhas
            while (rs.next()) {
                // Reutilizamos nossa mágica!
                Item item = extrairItemDoResultSet(rs);
                if (item != null) {
                    itens.add(item); // Adiciona o item criado à lista
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao listar todos os itens: " + e.getMessage());
            e.printStackTrace();
        }
        return itens; // Retorna a lista com todos os itens encontrados
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM TB_ITEM WHERE ID = ?";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Define o ID do item a ser deletado
            pstmt.setInt(1, id);

            // Executa o comando. executeUpdate() retorna o número de linhas afetadas.
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("SUCESSO: Item com ID " + id + " foi deletado.");
            } else {
                System.out.println("AVISO: Nenhum item com ID " + id + " foi encontrado para deletar.");
            }

        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao deletar o item com ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
package com.luis.gvp.infra.persistence;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.looks.exceptions.LookNaoEncontradoException;
import com.luis.gvp.core.entities.tiposDeRoupas.Acessorio;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaInferior;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaIntima;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaSuperior;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.core.repositories.LookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LookRepositoryImpl implements LookRepository {

    private final ItemRepository itemRepository;

    public LookRepositoryImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void salvar(Look look) {
        String sql = "INSERT INTO TB_LOOK (idPts, idPti, idPtin, idAsse) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, look.getParteSuperior().getId());
            pstmt.setInt(2, look.getParteInferior().getId());
            pstmt.setInt(3, look.getParteIntima().getId());
            pstmt.setInt(4, look.getAcessorio() != null ? look.getAcessorio().getId() : 0);

            pstmt.executeUpdate();
            System.out.println("SUCESSO: Look salvo com sucesso.");
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao salvar o Look: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Look buscarPorId(int id) {
        String sql = "SELECT * FROM TB_LOOK WHERE ID = ?";
        Look look = null;

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idPts = rs.getInt("idPts");
                    int idPti = rs.getInt("idPti");
                    int idPtin = rs.getInt("idPtin");
                    int idAsse = rs.getInt("idAsse");

                    // Recupera os itens usando o repositório
                    RoupaSuperior parteSuperior = (RoupaSuperior) itemRepository.buscarPorId(idPts);
                    RoupaInferior parteInferior = (RoupaInferior) itemRepository.buscarPorId(idPti);
                    RoupaIntima parteIntima = (RoupaIntima) itemRepository.buscarPorId(idPtin);
                    Acessorio acessorio = (Acessorio) itemRepository.buscarPorId(idAsse);

                    look = new Look(id, parteSuperior, parteInferior, parteIntima, acessorio);
                    
                }
            }

        } catch (SQLException e) {
            LookNaoEncontradoException erro = new LookNaoEncontradoException("Look nao encontrado");
            e.printStackTrace();
        }

        return look;
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM TB_LOOK WHERE ID = ?";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("SUCESSO: Look com ID " + id + " foi deletado.");
            } else {
                System.out.println("AVISO: Nenhum Look com ID " + id + " foi encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao deletar o Look com ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

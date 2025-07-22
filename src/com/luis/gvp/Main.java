package com.luis.gvp;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.tiposDeRoupas.Acessorio;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaInferior;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaIntima;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaSuperior;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.core.repositories.LookRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;
import com.luis.gvp.infra.persistence.LookRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        // Inicializa os repositórios
        ItemRepository itemRepo = new ItemRepositoryImpl();
        LookRepository lookRepo = new LookRepositoryImpl(itemRepo);

        // --- ETAPA 1: Buscar ou criar peças de roupa já existentes ---
        // IMPORTANTE: você precisa garantir que esses IDs existem na TB_ITEM!
        RoupaSuperior camisa = (RoupaSuperior) itemRepo.buscarPorId(4);
        RoupaInferior calca = (RoupaInferior) itemRepo.buscarPorId(8);
        RoupaIntima cueca = (RoupaIntima) itemRepo.buscarPorId(12);
        //Acessorio cinto = (Acessorio) itemRepo.buscarPorId(4);

        if (camisa == null || calca == null || cueca == null) {
            System.out.println("ERRO: Verifique se os IDs 1, 2 e 3 existem na tabela TB_ITEM.");
            return;
        }

        // --- ETAPA 2: Criar o Look ---
        Look look = new Look(0, camisa, calca, cueca, null); // O ID será atribuído pelo banco (autoincrement)
        //look.setAcessorio(cinto); // Pode ser null se quiser testar sem acessório

        // --- ETAPA 3: Salvar o Look no banco ---
        lookRepo.salvar(look);

        // --- ETAPA 4: Buscar um Look e exibir ---
        Look lookBuscado = lookRepo.buscarPorId(1); // Verifique o ID real inserido (pode variar)
        if (lookBuscado != null) {
            System.out.println("Look #" + lookBuscado.getIdLook() + ":");
            System.out.println("Superior: " + lookBuscado.getParteSuperior().getDescricao());
            System.out.println("Inferior: " + lookBuscado.getParteInferior().getDescricao());
            System.out.println("Íntima: " + lookBuscado.getParteIntima().getDescricao());
            System.out.println("Acessório: " +
                (lookBuscado.getAcessorio() != null ? lookBuscado.getAcessorio().getDescricao() : "Nenhum"));
        } else {
            System.out.println("Nenhum look encontrado com ID 1.");
        }

        // --- ETAPA 5: Teste de exclusão (opcional) ---
        // lookRepo.deletar(1);
        // System.out.println("Look excluído com sucesso.");
    }
}

package com.luis.gvp;

import java.lang.annotation.Target;
import java.util.List;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.modeloRoupas.roupaSuperior.Camisa;
import com.luis.gvp.core.repositories.ItemRepository;
import com.luis.gvp.infra.persistence.ItemRepositoryImpl;


//Lembre-se desta classe?
public class Main {
 public static void main(String[] args) {
     System.out.println("Iniciando teste de salvamento...");

     Camisa camisaTeste = new Camisa("Camisa de Algodão Branca", Cores.BRANCO, Tamanho.M, new LojaDeOrigem("C&A"), EstadoConservacao.BOM,"fd");

     ItemRepository repositorio = new ItemRepositoryImpl();
     // Esta linha vai inserir o item no banco de dados.
     repositorio.salvar(camisaTeste); 

     System.out.println("Teste finalizado.");
 }
}
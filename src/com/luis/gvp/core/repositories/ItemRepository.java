package com.luis.gvp.core.repositories;

import com.luis.gvp.core.entities.Item;
import java.util.List; // Importe a classe List

// Esta interface é o CONTRATO. Ela diz O QUE fazer.
public interface ItemRepository {
    
    
    void salvar(Item item);
    Item buscarPorId(int id);
    List<Item> listarTodos(); 
    void deletar(int id); 

}
package com.luis.gvp.core.entities.roupasCasosDeUso;

import com.luis.gvp.adapters.RoupasService;
import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.Usuario;
import com.luis.gvp.core.repositories.ItemRepository;

public class cadastrarRoupaCasoDeUsoImpl implements cadastrarRoupaCasoDeUso {
	private  RoupasService service;
    private  ItemRepository itemRepository; // Depende da interface do repositório

	public cadastrarRoupaCasoDeUsoImpl(ItemRepository itemRepository)
	{
		this.itemRepository = itemRepository;
	}
	@Override
	public void execute( Item item)
	{
		this.itemRepository.salvar(item);
	}
}

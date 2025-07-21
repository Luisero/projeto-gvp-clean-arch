package com.luis.gvp.core.entities.roupasCasosDeUso;

import com.luis.gvp.adapters.RoupasService;
import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.Usuario;

public class cadastrarRoupaCasoDeUsoImpl implements cadastrarRoupaCasoDeUso {
	private  RoupasService service;
	@Override
	public void execute(Usuario usuario, Item item)
	{
		this.service.cadastrarRoupa(usuario, item);
	}
}

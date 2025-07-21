package com.luis.gvp.core.entities.roupasCasosDeUso;

import com.luis.gvp.adapters.RoupasService;
import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.Usuario;
import com.luis.gvp.core.entities.roupas.exceptions.NaoPodeEmprestarException;

public class emprestarRoupaCasoDeUsoImpl implements emprestarRoupaCasoDeUso{
	private RoupasService service;
	@Override
	public void execute(Usuario usuario, Item item, Usuario destinatario,int dias) throws NaoPodeEmprestarException
	{
		if(this.service.podeEmprestarRoupa(item))
		{
			
			this.service.emprestarRoupa(usuario, item, destinatario, dias);
		}
		else {
			throw new NaoPodeEmprestarException("Este item não pode ser emprestado");
		}
	}
}

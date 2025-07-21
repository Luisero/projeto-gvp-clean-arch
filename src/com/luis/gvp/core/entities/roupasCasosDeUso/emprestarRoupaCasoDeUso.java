package com.luis.gvp.core.entities.roupasCasosDeUso;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.Usuario;
import com.luis.gvp.core.entities.roupas.exceptions.NaoPodeEmprestarException;

public interface emprestarRoupaCasoDeUso {
	
	void execute(Usuario usuario, Item item, Usuario destinatario, int dias) throws NaoPodeEmprestarException;
}

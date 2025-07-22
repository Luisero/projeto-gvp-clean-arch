package com.luis.gvp.core.entities.modeloRoupas.roupaIntima;

import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaIntima;

public class Cueca extends RoupaIntima{

	public Cueca(String descricao, Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado,
			String caminhoFoto) {
		super(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
		
	}
	
}

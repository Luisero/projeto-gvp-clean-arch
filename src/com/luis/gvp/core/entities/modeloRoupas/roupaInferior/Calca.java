package com.luis.gvp.core.entities.modeloRoupas.roupaInferior;

import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaInferior;

public class Calca extends RoupaInferior{

	public Calca(String descricao, Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado,
			String caminhoFoto) {
		super(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
		
	}
	
}

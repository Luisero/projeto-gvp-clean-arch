package com.luis.gvp.core.entities.modeloRoupas.acessorios;

import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.entities.tiposDeRoupas.Acessorio;

public class Relogio  extends Acessorio{

	public Relogio(String descricao, Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado,
			String caminhoFoto) {
		super(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
		
	}
	
}

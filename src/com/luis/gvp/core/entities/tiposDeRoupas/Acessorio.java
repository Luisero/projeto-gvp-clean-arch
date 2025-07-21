package com.luis.gvp.core.entities.tiposDeRoupas;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.interfaces.IEmprestavel;

public class Acessorio extends Item implements IEmprestavel{
	private boolean podeEmprestar = true;
	public Acessorio(String descricao, Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado,
			String caminhoFoto) {
		super(descricao, cor, tamanho, lojaOrigem, estado, caminhoFoto);
		
	}
	
	@Override
	 
    public boolean podeEmprestar()
    {
    	return this.podeEmprestar;
    }
    

}

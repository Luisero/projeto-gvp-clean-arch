package com.luis.gvp.core.entities.tiposDeRoupas;

import java.time.LocalDate;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.interfaces.ILavavel;

public class RoupaIntima extends Item implements ILavavel{
	private LocalDate ultimaLavagem =  LocalDate.now();
	public RoupaIntima(String descricao,Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado, String caminhoFoto)
	{
		super(descricao, cor, tamanho,lojaOrigem,estado,caminhoFoto);
	}
	
	@Override
	public void registrarLavagem()
	{
		this.ultimaLavagem = LocalDate.now();
	}
	
	@Override
    public LocalDate getDataUltimaLavagem()
    {
    	return this.ultimaLavagem;
    }

}	

package com.luis.gvp.core.entities.tiposDeRoupas;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.LojaDeOrigem;
import com.luis.gvp.core.entities.enums.Cores;
import com.luis.gvp.core.entities.enums.EstadoConservacao;
import com.luis.gvp.core.entities.enums.Tamanho;
import com.luis.gvp.core.interfaces.IEmprestavel;
import com.luis.gvp.core.interfaces.ILavavel;
import java.time.LocalDate;

public class RoupaInferior extends Item implements ILavavel, IEmprestavel {
	private boolean podeEmprestar = true;
	private LocalDate ultimaLavagem =  LocalDate.now();
	public RoupaInferior(String descricao,Cores cor, Tamanho tamanho, LojaDeOrigem lojaOrigem, EstadoConservacao estado, String caminhoFoto)
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
	
	@Override
    public boolean podeEmprestar()
    {
    	return this.podeEmprestar;
    }
    
}

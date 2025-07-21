package core.entities;

import core.entities.enums.Cores;
import core.entities.enums.EstadoConservacao;
import core.entities.enums.Tamanho;
import core.interfaces.IEmprestavel;
import core.interfaces.ILavavel;

public class RoupaInferior extends Item implements ILavavel, IEmprestavel {
	
	public RoupaInferior(String descricao,Cores cor, Tamanho tamanho, String lojaOrigem, EstadoConservacao estado, String caminhoFoto)
	{
		super(descricao, cor, tamanho,lojaOrigem,estado,caminhoFoto);
	}
	
	public void registrarLavagem()
	{		
	}
	public void registrarEmprestimo(Usuario user)
	{}
}

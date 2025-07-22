package com.luis.gvp.core.entities.looksCasodeUso;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.repositories.LookRepository;


public class CriarLookCasoDeUsoImpl implements criarLookCasoDeUso{
	private LookRepository lookRepository;
	public CriarLookCasoDeUsoImpl(LookRepository lookRepository)
	{
		this.lookRepository = lookRepository;
	}
	public void execute(Look look)
	{
		this.lookRepository.salvar(look);
	}
}

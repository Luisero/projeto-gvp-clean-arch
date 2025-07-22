package com.luis.gvp.core.entities;

import com.luis.gvp.core.entities.tiposDeRoupas.Acessorio;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaInferior;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaIntima;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaSuperior;

public class Look {
	private int idLook;
	private RoupaSuperior parteSuperior ;
	private RoupaInferior parteInferior;
	private RoupaIntima parteIntima;
	private Acessorio acessorios;
	
	public Look(int idLook,RoupaSuperior ps1, RoupaInferior ri1, RoupaIntima rin)
	{
		this.parteSuperior = ps1;
		this.parteInferior = ri1;
		this.parteIntima = rin;
		this.idLook = idLook;
	}
	
}

package com.luis.gvp.core.entities;

public class Usuario extends Pessoa{
	private Identity id;

	public Usuario(int id, String nome) {
		super(nome);
		this.id = new Identity(id);
		
	}
	
	public static Usuario cadastrarUsuario(int id, String nome)
	{
		return new Usuario(id, nome);
	}
	
}

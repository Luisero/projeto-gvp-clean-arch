package core.entities;

public abstract class Pessoa {
	protected String nome;
	
	public Pessoa(String nome)
	{
		this.nome = nome;
	}
	
	protected void setNome(String novoNome)
	{
		this.nome = novoNome;
	}
	
	protected String getNome()
	{
		return this.nome;
	}
}

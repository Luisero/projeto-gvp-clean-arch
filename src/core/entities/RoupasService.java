package core.entities;

public interface RoupasService {
	
	public void registrarLavagem();
	public void emprestarRoupa(Usuario usuario, Item item );
	public void getAllEmprestimos(Usuario usuario);
	public void podeEmprestarRoupa(Item item);
}

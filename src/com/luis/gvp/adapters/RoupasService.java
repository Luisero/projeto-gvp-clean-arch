package com.luis.gvp.adapters;

import com.luis.gvp.core.entities.Item;
import com.luis.gvp.core.entities.Usuario;

public interface RoupasService {
	
	public void registrarLavagem();
	public void emprestarRoupa(Usuario usuario, Item item , Usuario destinatario, int dias);
	public void getAllEmprestimos(Usuario usuario);
	public boolean podeEmprestarRoupa(Item item);
	public void cadastrarRoupa(Usuario usuario, Item item);
}

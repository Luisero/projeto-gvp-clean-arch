package com.luis.gvp.adapters;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.Usuario;
import com.luis.gvp.core.entities.looks.exceptions.LookNaoEncontradoException;

public interface LooksService {
	public void cadastrarLook(Usuario usuario, Look look);
	public Look getLookPorId(int idLook) throws LookNaoEncontradoException;
	public Look updateLook(int idLook, Look look);
	
}

package com.luis.gvp.core.entities.looksCasodeUso;

import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.Usuario;
import com.luis.gvp.core.entities.looks.exceptions.LookNaoEncontradoException;

public interface deletarLookCasoDeUso {
	public void execute(Usuario usuario, int idLook) throws LookNaoEncontradoException;
}

package com.luis.gvp.adapters;

import java.util.List;

import com.luis.gvp.core.entities.Emprestimo;
import com.luis.gvp.core.entities.Look;
import com.luis.gvp.core.entities.Usuario;

public interface EstatisticasService {
	public void registrarUsoDeLook(Usuario usuario,Look look);
	public void getLookMaisUsado(Usuario usuario, Look look);
	public List<Emprestimo> getEmprestimos(Usuario usuario);
}

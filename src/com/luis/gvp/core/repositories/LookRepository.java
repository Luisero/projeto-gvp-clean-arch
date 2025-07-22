package com.luis.gvp.core.repositories;

import com.luis.gvp.core.entities.Look;

public interface LookRepository {
	void salvar(Look look);
    Look buscarPorId(int id);
    void deletar(int id);
}

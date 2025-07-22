package com.luis.gvp.core.entities;

import com.luis.gvp.core.entities.tiposDeRoupas.Acessorio;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaInferior;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaIntima;
import com.luis.gvp.core.entities.tiposDeRoupas.RoupaSuperior;

public class Look {
    private int idLook;
    private RoupaSuperior parteSuperior;
    private RoupaInferior parteInferior;
    private RoupaIntima parteIntima;
    private Acessorio acessorios;

    public Look(int idLook, RoupaSuperior ps1, RoupaInferior ri1, RoupaIntima rin, Acessorio asse) {
        if (ps1 == null || ri1 == null || rin == null) {
            throw new IllegalArgumentException("Todas as partes do look são obrigatórias.");
        }
        this.idLook = idLook;
        this.parteSuperior = ps1;
        this.parteInferior = ri1;
        this.parteIntima = rin;
        this.acessorios = asse;
    }

    public void setAcessorio(Acessorio acessorios) {
        this.acessorios = acessorios;
    }

    public Acessorio getAcessorio() {
        return acessorios;
    }

    public int getIdLook() {
        return idLook;
    }

    public RoupaSuperior getParteSuperior() {
        return parteSuperior;
    }

    public RoupaInferior getParteInferior() {
        return parteInferior;
    }

    public RoupaIntima getParteIntima() {
        return parteIntima;
    }

    @Override
    public String toString() {
        return "Look #" + idLook + ": " +
            parteSuperior.getClass().getSimpleName() + ", " +
            parteInferior.getClass().getSimpleName() + ", " +
            parteIntima.getClass().getSimpleName() + ", " +
            (acessorios != null ? acessorios.getClass().getSimpleName() : "Sem acessório");
    }
}

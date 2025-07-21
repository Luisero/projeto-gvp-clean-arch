package com.luis.gvp.core.entities.enums;

public enum EstadoConservacao {
    EXCELENTE("Excelente"),
    BOM("Bom"),
    REGULAR("Regular"),
    RUIM("Ruim");

    private final String descricao;

    EstadoConservacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
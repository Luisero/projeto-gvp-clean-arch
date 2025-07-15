package core.entities;

import core.entities.enums.EstadoConservacao;
// Você também vai importar seus enums de Cor e Tamanho aqui

public abstract class Item {

    private Integer id;
    private String descricao;
    private String cor; // Provisório, idealmente será um Enum Cor
    private String tamanho; // Provisório, idealmente será um Enum Tamanho
    private String lojaOrigem;
    private EstadoConservacao estado; // Usando o Enum que criamos [cite: 13]
    private String caminhoFoto;

    public Item(String descricao, String cor, String tamanho, String lojaOrigem, EstadoConservacao estado, String caminhoFoto) {
        this.descricao = descricao;
        this.cor = cor;
        this.tamanho = tamanho;
        this.lojaOrigem = lojaOrigem;
        this.estado = estado;
        this.caminhoFoto = caminhoFoto;
    }

    // --- Getters e Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getLojaOrigem() {
        return lojaOrigem;
    }

    public void setLojaOrigem(String lojaOrigem) {
        this.lojaOrigem = lojaOrigem;
    }

    public EstadoConservacao getEstado() {
        return estado;
    }

    public void setEstado(EstadoConservacao estado) {
        this.estado = estado;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
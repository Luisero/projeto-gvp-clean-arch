package core.entities;

import core.entities.enums.EstadoConservacao;
import core.entities.enums.Tamanho;
import core.entities.enums.Cores;


public abstract class Item {

    private Integer id;
    private String descricao;
    private Cores cor; 
    private Tamanho tamanho; 
    private String lojaOrigem;
    private EstadoConservacao estado; // Usando o Enum que criamos [cite: 13]
    private String caminhoFoto;

    public Item(String descricao,Cores cor, Tamanho tamanho, String lojaOrigem, EstadoConservacao estado, String caminhoFoto) {
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

    public Cores getCor() {
        return this.cor;
    }

    public void setCor(Cores cor) {
        this.cor = cor;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
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
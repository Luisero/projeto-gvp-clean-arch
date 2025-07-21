package core.entities.enums;

public enum Cores {
    PRETO("Preto"),
    BRANCO("Branco"),
    AZUL("Azul"),
    VERDE("Verde"),
    VERMELHO("Vermelho"),
    AMARELO("Amarelo"),
    CINZA("Cinza"),
    BEGE("Bege"),
    CAQUI("Caqui");

    private final String descricao;

    Cores(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
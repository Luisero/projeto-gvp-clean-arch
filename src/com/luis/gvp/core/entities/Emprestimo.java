package com.luis.gvp.core.entities;

import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    
    private Usuario usuario;
    private Item item;
    private Usuario destinatario;


    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    /**
     * Construtor da classe Emprestimo.
     * A data do empréstimo é definida automaticamente como a data atual.
     * A data de devolução é calculada somando a duração em dias à data atual.
     *
     * @param usuario O usuário que está emprestando o item.
     * @param item O item que está sendo emprestado.
     * @param destinatario O usuário que está recebendo o item.
     * @param duracaoEmDias A quantidade de dias que o empréstimo irá durar.
     */
    public Emprestimo(Usuario usuario, Item item, Usuario destinatario, int duracaoEmDias) {
        
        this.usuario = usuario;
        this.item = item;
        this.destinatario = destinatario;

        
        this.dataEmprestimo = LocalDate.now();

        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(duracaoEmDias);
    }

   

    public Usuario getUsuario() {
        return usuario;
    }

    public Item getItem() {
        return item;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    public long getDiasRestantes() {
       
        LocalDate hoje = LocalDate.now();

        return ChronoUnit.DAYS.between(hoje, this.dataDevolucaoPrevista);
    }
    
    @Override
    public String toString() {
        return "Emprestimo {" +
               "item=" + item.getDescricao() + 
               ", destinatario=" + destinatario.getNome() + 
               ", dataEmprestimo=" + dataEmprestimo +
               ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
               '}';
    }
}
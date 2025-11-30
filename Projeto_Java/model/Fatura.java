package model;

import model.chamado.Chamado;
import model.chamado.StatusChamado;

public class Fatura {
    private int id;
    private Chamado chamado;
    private double valor;
    private boolean faturada;

    public Fatura(int id, Chamado chamado, double valor) {
        this.id = id;
        this.chamado = chamado;
        this.valor = valor;
        this.faturada = false;
    }

    public void faturar() {
        if (chamado.getStatus() == StatusChamado.RESOLVIDO) {
            faturada = true;
            chamado.marcarComoFaturado();
            chamado.getColaboradorAtribuido().atualizarHoras(chamado.getServico().getHorasNecessarias());
        } else {
            throw new IllegalStateException("Chamado não resolvido.");
        }
    }

    public int getId() { return id; }
    public Chamado getChamado() { return chamado; }
    public double getValor() { return valor; }
    public boolean isFaturada() { return faturada; }
}

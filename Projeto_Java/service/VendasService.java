package service;

import model.*;
import model.chamado.Chamado;

import java.util.List;

public class VendasService {
    private List<Chamado> chamados;
    private List<Fatura> faturas;
    private int idChamadoCounter = 1;
    private int idFaturaCounter = 1;

    public VendasService(List<Chamado> chamados, List<Fatura> faturas) {
        this.chamados = chamados;
        this.faturas = faturas;
    }

    public Chamado abrirChamado(Cliente cliente, Servico servico, String descricao, model.colaborador.Comercial comercial) {
        Chamado chamado = new Chamado(idChamadoCounter++, cliente, servico, descricao);
        chamados.add(chamado);

        Fatura fatura = new Fatura(idFaturaCounter++, chamado, servico.getHorasNecessarias() * 50);
        faturas.add(fatura);

        comercial.registrarVenda(fatura.getValor());

        return chamado;
    }
}

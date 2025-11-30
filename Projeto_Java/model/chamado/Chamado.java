package model.chamado;

import model.Cliente;
import model.Servico;
import model.colaborador.Colaborador;
import model.colaborador.Desenvolvedor;
import model.colaborador.Especialista;
import model.colaborador.SuporteNivel;

public class Chamado {
    private int id;
    private Cliente cliente;
    private Servico servico;
    private Colaborador colaboradorAtribuido;
    private StatusChamado status;
    private String descricao;

    public Chamado(int id, Cliente cliente, Servico servico, String descricao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.descricao = descricao;
        this.status = StatusChamado.ABERTO;
    }

    public void atribuirColaborador(Colaborador colaborador) {
        this.colaboradorAtribuido = colaborador;
    }

    public void marcarComoNaoResolvido() {
        if (status == StatusChamado.ABERTO)
            status = StatusChamado.EM_ANALISE;
    }


    public void marcarComoResolvido(Colaborador executor) {

        boolean podeResolver = (executor.equals(this.colaboradorAtribuido) ||
                (executor instanceof Desenvolvedor) ||
                (executor instanceof Especialista) ||
                (executor instanceof SuporteNivel && ((SuporteNivel) executor).getNivel() == 2)
        );

        if (podeResolver) {
            if (status == StatusChamado.EM_ANALISE || status == StatusChamado.EM_DESENVOLVIMENTO) {
                status = StatusChamado.RESOLVIDO;
                System.out.println("INFO: Chamado " + id + " resolvido por " + executor.getNome() + ".");
            } else {
                System.out.println("ERRO: Chamado " + id + " não pode ser resolvido pois está com o status: " + status);
            }
        } else {
            System.out.println("ERRO: O colaborador " + executor.getNome() + " não tem permissão para marcar o chamado " + id + " como RESOLVIDO.");
        }
    }

    public void marcarComoFaturado() {
        if (status == StatusChamado.RESOLVIDO)
            status = StatusChamado.FATURADO;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Servico getServico() { return servico; }
    public Colaborador getColaboradorAtribuido() { return colaboradorAtribuido; }
    public StatusChamado getStatus() { return status; }
    public String getDescricao() { return descricao; }


    public void setStatus(StatusChamado status) { this.status = status; }
}
package service;

import model.chamado.Chamado;
import model.chamado.StatusChamado;
import model.colaborador.*;

import java.util.List;

public class SuporteService {
    private List<SuporteNivel> suportesN1;
    private List<SuporteNivel> suportesN2;
    private List<Especialista> especialistas;

    public SuporteService(List<SuporteNivel> suportesN1, List<SuporteNivel> suportesN2, List<Especialista> especialistas) {
        this.suportesN1 = suportesN1;
        this.suportesN2 = suportesN2;
        this.especialistas = especialistas;
    }

    public void escalonarChamado(Chamado chamado) {

        if (chamado.getStatus() == StatusChamado.ABERTO) {


            SuporteNivel n1 = suportesN1.stream()
                    .filter(s -> s.verificarDisponibilidade(chamado.getServico().getHorasNecessarias()))
                    .findFirst()
                    .orElse(null);

            if (n1 != null) {
                chamado.atribuirColaborador(n1);
                System.out.println("ATRIBUÍDO: Chamado " + chamado.getId() + " atribuído ao N1: " + n1.getNome());
            } else {
                System.out.println("ALERTA: Nenhum N1 disponível. O chamado permanece em ABERTO.");
            }

        } else if (chamado.getStatus() == StatusChamado.EM_ANALISE) {


            Especialista esp = especialistas.stream()
                    .filter(e -> e.verificarDisponibilidade(chamado.getServico().getHorasNecessarias()))
                    .findFirst()
                    .orElse(null);

            if (esp != null) {
                chamado.atribuirColaborador(esp);

                chamado.marcarComoResolvido(esp);
                System.out.println("ESCALONADO: Chamado " + chamado.getId() + " escalonado para o Especialista: " + esp.getNome());
            } else {

                SuporteNivel n2 = suportesN2.stream()
                        .filter(s -> s.verificarDisponibilidade(chamado.getServico().getHorasNecessarias()))
                        .findFirst()
                        .orElse(null);

                if (n2 != null) {
                    chamado.atribuirColaborador(n2);

                    chamado.marcarComoResolvido(n2);
                    System.out.println("ESCALONADO: Chamado " + chamado.getId() + " escalonado para o Suporte N2: " + n2.getNome());
                } else {
                    System.out.println("ALERTA: Nível de suporte esgotado (sem Especialista ou N2 disponíveis).");
                }
            }
        }
    }
}
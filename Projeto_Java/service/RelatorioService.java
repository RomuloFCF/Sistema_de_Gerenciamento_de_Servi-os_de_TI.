package service;

import model.chamado.Chamado;
import model.chamado.StatusChamado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioService {

    public void gerarRelatorioProdutividade(List<Chamado> chamados) {
        Map<String, Integer> produtividade = new HashMap<>();

        for (Chamado c : chamados) {
            if (c.getStatus() == StatusChamado.FATURADO && c.getColaboradorAtribuido() != null) {
                String nome = c.getColaboradorAtribuido().getNome();
                produtividade.put(nome, produtividade.getOrDefault(nome, 0) + 1);
            }
        }

        System.out.println("Relatório de Produtividade:");
        produtividade.forEach((nome, qtd) ->
            System.out.println(nome + ": " + qtd + " chamados resolvidos")
        );
    }
}

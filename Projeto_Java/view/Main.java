package view;

import model.Fatura;
import model.Servico;
import model.chamado.Chamado;
import model.chamado.StatusChamado;
import model.colaborador.*;
import service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<model.Cliente> clientes = new ArrayList<>();
        List<Chamado> chamados = new ArrayList<>();
        List<Fatura> faturas = new ArrayList<>();
        List<SuporteNivel> suportesN1 = new ArrayList<>();
        List<SuporteNivel> suportesN2 = new ArrayList<>();
        List<Especialista> especialistas = new ArrayList<>();
        List<Colaborador> colaboradores = new ArrayList<>();


        model.Cliente cliente1 = new model.Cliente("João Silva", "joao@email.com", "123456789");
        clientes.add(cliente1);

        Comercial comercial1 = new Comercial("Maria Comercial", 1, 40, 2000, 10);
        colaboradores.add(comercial1);


        SuporteNivel n1_pedro = new SuporteNivel("Pedro N1", 2, 40, 1, 20);
        suportesN1.add(n1_pedro);
        colaboradores.add(n1_pedro);


        SuporteNivel n2_carlos = new SuporteNivel("Carlos N2", 4, 30, 2, 25);
        suportesN2.add(n2_carlos);
        colaboradores.add(n2_carlos);


        Especialista esp1_ana = new Especialista("Ana Especialista", 3, 40, "Redes", 2500);
        especialistas.add(esp1_ana);
        colaboradores.add(esp1_ana);


        Desenvolvedor dev1_bruno = new Desenvolvedor("Bruno Dev", 5, 50, 3500);
        colaboradores.add(dev1_bruno);

        Servico servico1 = new Servico("Manutenção Crítica", 10);
        Servico servico2 = new Servico("Nova Feature", 20);

        VendasService vendasService = new VendasService(chamados, faturas);
        SuporteService suporteService = new SuporteService(suportesN1, suportesN2, especialistas);
        FaturamentoService faturamentoService = new FaturamentoService(faturas);
        RelatorioService relatorioService = new RelatorioService();


        Chamado chamado1 = vendasService.abrirChamado(cliente1, servico1, "Servidor Fora do Ar", comercial1);
        System.out.println("======================================================");
        System.out.println("=== CICLO 1: ESCALONAMENTO (N1 -> ESPECIALISTA/N2) ===");
        System.out.println("======================================================\n");
        System.out.println("Chamado " + chamado1.getId() + "- Status: " + chamado1.getStatus());



        System.out.println("\n-> Está sendo Escalonado para N1!!");
        suporteService.escalonarChamado(chamado1);


        System.out.println("-> N1 (" + chamado1.getColaboradorAtribuido().getNome() + ") marca como Não Resolvido (Falha).");
        chamado1.marcarComoNaoResolvido();
        System.out.println("Novo Status: " + chamado1.getStatus());


        System.out.println("\n-> Re-Escalonando (Busca Especialista/N2)...");

        suporteService.escalonarChamado(chamado1);


        Colaborador responsavel1 = chamado1.getColaboradorAtribuido();
        System.out.println("\n----------------------------------------");
        System.out.println("----- Faturamento e Baixa de Horas -----");
        System.out.println("----------------------------------------");
        Fatura fatura1 = faturas.get(0);

        try {
            faturamentoService.emitirFatura(fatura1);

            System.out.println("Fatura " + fatura1.getId() + " emitida!" + "\nStatus Faturada:  " + fatura1.isFaturada());
            System.out.println("Horas restantes de " + responsavel1.getNome() + ": " + responsavel1.getHorasDisponiveis());
            ((Especialista) responsavel1).concluirProjeto();
            System.out.println("----------------------------------------\n");
        } catch (IllegalStateException e) {
            System.out.println("ERRO Faturamento: " + e.getMessage());
        }




        Chamado chamado2 = vendasService.abrirChamado(cliente1, servico2, "Nova Feature em Sistema Legado", comercial1);
        System.out.println("\n=================================================");
        System.out.println("=== CICLO 2: CHAMADO DE DESENVOLVIMENTO (DEV) ===");
        System.out.println("=================================================\n");
        System.out.println("Chamado " + chamado2.getId() + "- Status: " + chamado2.getStatus());


        chamado2.atribuirColaborador(dev1_bruno);
        chamado2.marcarComoNaoResolvido();


        chamado2.setStatus(StatusChamado.EM_DESENVOLVIMENTO);
        System.out.println("Atribuído a: " + dev1_bruno.getNome() + ". Status alterado para: " + chamado2.getStatus());


        dev1_bruno.concluirProjeto();
        System.out.println("\n-> Desenvolvedor (" + dev1_bruno.getNome() + ") marca como Resolvido.");
        chamado2.marcarComoResolvido(dev1_bruno);
        System.out.println("Status de Resolução: " + chamado2.getStatus());

        // 4. Faturamento do Chamado 2
        Colaborador responsavel2 = chamado2.getColaboradorAtribuido();
        System.out.println("\n----------------------------------------");
        System.out.println("----- Faturamento e Baixa de Horas -----");
        System.out.println("----------------------------------------");
        Fatura fatura2 = faturas.get(1);
        try {
            faturamentoService.emitirFatura(fatura2);

            System.out.println("Fatura " + fatura2.getId() + " emitida!" + "\nStatus Faturada: " + fatura2.isFaturada());
            System.out.println("Horas restantes de " + responsavel2.getNome() + ": " + responsavel2.getHorasDisponiveis());
            System.out.println("----------------------------------------\n");
        } catch (IllegalStateException e) {
            System.out.println("ERRO Faturamento: " + e.getMessage());
        }


        System.out.println("\n==================================");
        System.out.println("=== RELATÓRIO FINAL E SALÁRIOS ===");
        System.out.println("==================================\n");


        relatorioService.gerarRelatorioProdutividade(chamados);


        System.out.println("\n\nSalário dos funcionários:");
        for (Colaborador c : colaboradores) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Salário de " + c.getNome() + " (Horas/Vendas/Projetos): R$ " + String.format("%.2f", c.calcularSalario()));
        }
        System.out.println("---------------------------------------------------------------");
    }

}
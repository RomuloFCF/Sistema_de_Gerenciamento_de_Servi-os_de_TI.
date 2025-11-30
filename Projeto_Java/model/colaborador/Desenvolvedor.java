package model.colaborador;

public class Desenvolvedor extends Colaborador {
    private double salarioBase;
    private int projetosConcluidos = 0;

    public Desenvolvedor(String nome, int id, int horasDisponiveis, double salarioBase) {
        super(nome, id, horasDisponiveis);
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (projetosConcluidos * 1000);
    }

    public void concluirProjeto() {
        projetosConcluidos++;
    }
}

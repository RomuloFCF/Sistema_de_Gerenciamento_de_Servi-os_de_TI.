package model.colaborador;

public class Especialista extends Colaborador {
    private String especializacao;
    private double salarioBase;
    private int projetosConcluidos = 0;

    public Especialista(String nome, int id, int horasDisponiveis, String especializacao, double salarioBase) {
        super(nome, id, horasDisponiveis);
        this.especializacao = especializacao;
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (projetosConcluidos * 500);
    }

    public void concluirProjeto() {
        projetosConcluidos++;
    }

    public String getEspecializacao() { return especializacao; }
}

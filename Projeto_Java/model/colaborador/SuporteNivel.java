package model.colaborador;

public class SuporteNivel extends Colaborador {
    private int nivel;
    private double salarioPorHora;

    public SuporteNivel(String nome, int id, int horasDisponiveis, int nivel, double salarioPorHora) {
        super(nome, id, horasDisponiveis);
        this.nivel = nivel;
        this.salarioPorHora = salarioPorHora;
    }

    @Override
    public double calcularSalario() {
        return (100 - horasDisponiveis) * salarioPorHora;
    }

    public int getNivel() { return nivel; }
}

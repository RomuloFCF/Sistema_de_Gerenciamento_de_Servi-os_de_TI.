package model.colaborador;

public class Comercial extends Colaborador {
    private double salarioBase;
    private double comissaoPercentual;
    private double vendasTotais = 0;

    public Comercial(String nome, int id, int horasDisponiveis, double salarioBase, double comissaoPercentual) {
        super(nome, id, horasDisponiveis);
        this.salarioBase = salarioBase;
        this.comissaoPercentual = comissaoPercentual;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (vendasTotais * comissaoPercentual / 100);
    }

    public void registrarVenda(double valor) {
        vendasTotais += valor;
    }

    public double getVendasTotais() { return vendasTotais; }
}

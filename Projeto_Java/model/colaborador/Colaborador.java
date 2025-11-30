package model.colaborador;

public abstract class Colaborador {
    protected String nome;
    protected int id;
    protected int horasDisponiveis;

    public Colaborador(String nome, int id, int horasDisponiveis) {
        this.nome = nome;
        this.id = id;
        this.horasDisponiveis = horasDisponiveis;
    }

    public abstract double calcularSalario();

    public String getNome() { return nome; }
    public int getId() { return id; }
    public int getHorasDisponiveis() { return horasDisponiveis; }

    public void setNome(String nome) { this.nome = nome; }
    public void setId(int id) { this.id = id; }
    public void setHorasDisponiveis(int horasDisponiveis) { this.horasDisponiveis = horasDisponiveis; }

    public boolean verificarDisponibilidade(int horas) {
        return horasDisponiveis >= horas;
    }

    public void atualizarHoras(int horasGastas) {
        if (horasDisponiveis >= horasGastas) {
            horasDisponiveis -= horasGastas;
        } else {
            throw new IllegalArgumentException("Horas insuficientes.");
        }
    }
}

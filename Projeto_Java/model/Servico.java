package model;

public class Servico {
    private String tipo;
    private int horasNecessarias;

    public Servico(String tipo, int horasNecessarias) {
        this.tipo = tipo;
        this.horasNecessarias = horasNecessarias;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getHorasNecessarias() { return horasNecessarias; }
    public void setHorasNecessarias(int horasNecessarias) { this.horasNecessarias = horasNecessarias; }
}

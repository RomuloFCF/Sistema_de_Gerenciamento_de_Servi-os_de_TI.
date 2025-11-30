package service;

import model.Fatura;
import java.util.List;

public class FaturamentoService {
    private List<Fatura> faturas;

    public FaturamentoService(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public void emitirFatura(Fatura fatura) {
        fatura.faturar();
    }
}

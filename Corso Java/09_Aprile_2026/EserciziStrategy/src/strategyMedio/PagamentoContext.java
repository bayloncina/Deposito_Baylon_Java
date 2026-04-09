package strategyMedio;

public class PagamentoContext {

    private MetodoPagamento metodo;

    public PagamentoContext(MetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void eseguiPagamento(double importo) {
        metodo.paga(importo);
    }
}
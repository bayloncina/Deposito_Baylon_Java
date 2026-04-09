package strategy;

public class PagamentoContext {

    private IMetodoPagamento metodo;

    public PagamentoContext(IMetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void setMetodo(IMetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void eseguiPagamento(double importo) {
        metodo.paga(importo);
    }
}
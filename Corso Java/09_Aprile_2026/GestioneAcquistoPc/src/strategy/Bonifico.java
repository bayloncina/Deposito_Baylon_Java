package strategy;

public class Bonifico implements IMetodoPagamento {

    String nConto;

    public Bonifico(String nConto) {
        this.nConto = nConto;
    }

    @Override
    public void paga(double importo) {
        System.out.println("Pagamento con bonifico  euro " + importo + "con il conto n. " + nConto);
    }

}

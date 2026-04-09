package strategy;
//concrete strategy
public class CartaDiCredito implements IMetodoPagamento {

    private String nome;
    private int numeroCarta;

    public CartaDiCredito(String nome, int numeroCarta) {
        this.nome = nome;
        this.numeroCarta = numeroCarta;

    }


    @Override
    public void paga(double importo) {

        System.out.println("Pagamento con carta di credito di " + nome + ": euro " + importo + "con carta n. " + numeroCarta);

    }

}

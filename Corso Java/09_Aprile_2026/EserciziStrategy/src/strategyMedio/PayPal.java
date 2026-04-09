package strategyMedio;
//concrete strategy
public class PayPal implements IMetodoPagamento {

    private String email;

    public PayPal(String email) {
        this.email = email;
        
    }

    @Override
    public void paga(double importo) {
        System.out.println("Pagamento con PayPal di " + email + ": euro " + importo);
    }

}

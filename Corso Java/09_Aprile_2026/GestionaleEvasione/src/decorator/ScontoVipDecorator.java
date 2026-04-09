package decorator;

import strategy.StartegiaEvasione;

public class ScontoVipDecorator extends DecoratorEvasione {

    public ScontoVipDecorator(StartegiaEvasione strategia) {
        super(strategia);
    }

    @Override
    public void eseguiEvasione(double prezzo) {
        double prezzoVip = prezzo * 0.90; // sconto 10% VIP
        System.out.println("[VIP] Sconto del 10% applicato: € " + prezzoVip);
        strategia.eseguiEvasione(prezzoVip);
    }
}
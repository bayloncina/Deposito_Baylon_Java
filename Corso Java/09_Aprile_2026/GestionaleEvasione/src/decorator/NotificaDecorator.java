package decorator;

import strategy.StartegiaEvasione;

public class NotificaDecorator extends DecoratorEvasione{

    public NotificaDecorator(StartegiaEvasione strategia) {
        super(strategia);
        //TODO Auto-generated constructor stub
    }

    @Override
public void eseguiEvasione(double prezzo) {
    System.out.println("[NOTIFICA] Avvio evasione ordine - prezzo base: euro " + prezzo);
    strategia.eseguiEvasione(prezzo);
}
}
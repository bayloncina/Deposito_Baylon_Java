package decorator;

import strategy.StartegiaEvasione;

public class NotificaDecorator extends DecoratorEvasione {

    public NotificaDecorator(StartegiaEvasione strategia) {
        super(strategia);
    }

    @Override
    public void eseguiEvasione(double prezzo) {
        System.out.println("[NOTIFICA] Avvio evasione ordine - prezzo base: euro " + prezzo);
        strategia.eseguiEvasione(prezzo);
        System.out.println("[NOTIFICA] Evasione completata con successo.");
    }
}
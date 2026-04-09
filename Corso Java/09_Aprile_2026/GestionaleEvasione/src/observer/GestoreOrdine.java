package observer;

import decorator.NotificaDecorator;
import strategy.*;

// observer concreto
public class GestoreOrdine implements OrdineObserver {

    private Ordine ordine;
    private boolean notificaAttiva;

    public GestoreOrdine(Ordine ordine, boolean notificaAttiva) {
        this.ordine = ordine;
        this.notificaAttiva = notificaAttiva;
    }

    // riceve lo stato aggiornato e chiama setStrategia sull'ordine assegnandogli la
    // strategia corretta.
    // Il cambio di strategia avviene qui, non nel main.
    @Override
    public void statoOrdine(String stato) {
        StartegiaEvasione nuovaStrategia;

        switch (stato) {
            case "NORMALE":
                nuovaStrategia = new EvasioneNormale();
                break;
            case "PRIORITA":
                nuovaStrategia = new EvasionePrioritaria();
                break;
            case "CONTROLLO":
                nuovaStrategia = new EvasioneControllata();
                break;
            default:
                return;
        }

        if (notificaAttiva) {
            nuovaStrategia = new NotificaDecorator(nuovaStrategia);
        }

        ordine.setStrategia(nuovaStrategia);
        System.out.println("Strategia aggiornata: stato " + stato
                + (notificaAttiva ? " [+NOTIFICA]" : ""));
    }
}
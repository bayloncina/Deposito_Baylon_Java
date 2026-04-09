package observer;

import strategy.*;
//observer concreto
public class GestoreOrdine implements OrdineObserver {

    private Ordine ordine;

    public GestoreOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    //riceve lo stato aggiornato e chiama setStrategia sull'ordine assegnandogli la strategia corretta. 
    // Il cambio di strategia avviene qui, non nel main.

    @Override
    public void statoOrdine(String stato) {
        switch (stato) {
            case "NORMALE":
                ordine.setStrategia(new EvasioneNormale());
                break;
            case "PRIORITA":
                ordine.setStrategia(new EvasionePrioritaria());
                break;
            case "CONTROLLO":
                ordine.setStrategia(new EvasioneControllata());
                break;
        }
        System.out.println("Strategia aggiornata: stato " + stato);
    }
}
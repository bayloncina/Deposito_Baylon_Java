import java.util.ArrayList;
import java.util.List;

public class StazioneMeteo {
    // lista di tutti i display registrati
    private List<IDisplay> displays = new ArrayList<>();

    // temperatura attuale
    private float temperatura;

    // aggiunge un display alla lista
    public void aggiungiDisplay(IDisplay d) {
        displays.add(d);
    }

     // rimuove un display dalla lista
    public void rimuoviDisplay(IDisplay d) {
        displays.remove(d);
    }
    
    // avvisa tutti i display della temperatura attuale
    public void notificaDisplay() {
        for (IDisplay d : displays) {
            d.aggiorna(temperatura);
        }
    }
    // aggiorna la temperatura e notifica automaticamente tutti i display
    public void setTemperatura(float t) {
        this.temperatura = t;
        notificaDisplay();
    }
}

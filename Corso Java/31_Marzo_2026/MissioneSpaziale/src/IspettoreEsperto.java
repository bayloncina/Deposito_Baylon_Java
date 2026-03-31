import java.util.ArrayList;

public class IspettoreEsperto extends Ispettore {

    public IspettoreEsperto(Ispettore i) {
        super(i.getNome(), i.getEmail());
    }

    // Stampa tutte le valutazioni della stazione
    public void stampaTutteValutazioni(StazioneSpaziale stazione) {
        System.out.println("=== IspettoreEsperto: tutte le valutazioni ===");
        ArrayList<Integer> valutazioni = stazione.getValutazioni();
        if (valutazioni.isEmpty()) {
            System.out.println("Nessuna valutazione presente.");
        } else {
            for (int i = 0; i < valutazioni.size(); i++) {
                System.out.println("Valutazione " + (i + 1) + ": " + valutazioni.get(i));
            }
        }
    }

    @Override
    public void visualizzaDati() {
        super.visualizzaDati();
        System.out.println("Ruolo avanzato: IspettoreEsperto");
    }
}

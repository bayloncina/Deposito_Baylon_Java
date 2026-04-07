import java.util.ArrayList;
import java.util.List;


//Observable
public class AgenziaBorsa {

    private List<Investitore> investitoriRegistrati = new ArrayList<>();

    // aggiungi investitore
    public void aggiungiInestitore(Investitore i) {
        investitoriRegistrati.add(i);
    }

    // aggiornaInvestitore

    // aggiornaValoreAzione
    public void aggiornaValoreazione(String nome, double valore) {
        for (Investitore i : investitoriRegistrati) {
            i.notifica(nome, valore);
        }
    }

    public void rimuoviInvestitore(Investitore i) {
    investitoriRegistrati.remove(i);
}
}

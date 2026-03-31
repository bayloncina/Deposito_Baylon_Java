import java.util.ArrayList;

public class StazioneSpaziale {
    private ArrayList<String> esperimenti = new ArrayList<>();
    private ArrayList<Integer> valutazioni = new ArrayList<>();

    public void aggiungiEsperimento(String esperimento) {
        esperimenti.add(esperimento);
        System.out.println("Esperimento aggiunto: " + esperimento);
    }

    public void aggiungiValutazione(int valutazione) {
        if (valutazione >= 1 && valutazione <= 5) {
            valutazioni.add(valutazione);
            System.out.println("Valutazione aggiunta: " + valutazione);
        } else {
            System.out.println("Valutazione non valida. Inserire un valore tra 1 e 5.");
        }
    }

    public void stampaEsperimentiEValutazioni() {
        System.out.println("=== Esperimenti e Valutazioni ===");
        for (int i = 0; i < esperimenti.size(); i++) {
            String valStr = (i < valutazioni.size()) ? String.valueOf(valutazioni.get(i)) : "N/D";
            System.out.println("- " + esperimenti.get(i) + " | Valutazione: " + valStr);
        }
    }

    public ArrayList<String> getEsperimenti() { return esperimenti; }
    public ArrayList<Integer> getValutazioni() { return valutazioni; }
}
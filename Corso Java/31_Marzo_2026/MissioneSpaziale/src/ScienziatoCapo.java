public class ScienziatoCapo extends Scienziato {

    public ScienziatoCapo(Scienziato s) {
        super(s.getNome(), s.getEmail());
        // Mantiene il numero di azioni già svolte
    }

    // Aggiunge tutti gli esperimenti in una sola operazione
    public void aggiungiTuttiEsperimenti(StazioneSpaziale stazione, String[] esperimenti) {
        System.out.println("=== ScienziatoCapo: aggiunta massiva esperimenti ===");
        for (String esp : esperimenti) {
            stazione.aggiungiEsperimento(esp);
        }
    }

    @Override
    public void visualizzaDati() {
        super.visualizzaDati();
        System.out.println("Ruolo avanzato: ScienziatoCapo");
    }
}
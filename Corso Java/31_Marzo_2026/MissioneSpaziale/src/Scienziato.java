public class Scienziato extends Astronauta {
    private int azioniSvolte; // conta le azioni: serve per evolversi in ScienziatoCapo

    public Scienziato(String nome, String email) {
        super(nome, email);
        this.azioniSvolte = 0;
    }

    public void aggiungiEsperimento(StazioneSpaziale stazione, String esperimento) {
        stazione.aggiungiEsperimento(esperimento);
        azioniSvolte++;
        System.out.println("Azioni svolte: " + azioniSvolte + "/3");
    }

    public boolean isEsperto() {
        return azioniSvolte >= 3;
    }

    public int getAzioniSvolte() { return azioniSvolte; }

    @Override
    public void visualizzaDati() {
        super.visualizzaDati();
        System.out.println("Ruolo: Scienziato");
        System.out.println("Azioni svolte: " + azioniSvolte);
        System.out.println("Stato: " + (isEsperto() ? "ScienziatoCapo" : "Scienziato"));
    }
}
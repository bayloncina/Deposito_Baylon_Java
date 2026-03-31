public class Ispettore extends Astronauta {
    private int azioniSvolte; // conta le azioni: serve per evolversi in IspettoreEsperto

    public Ispettore(String nome, String email) {
        super(nome, email);
        this.azioniSvolte = 0;
    }

    public void inserisciValutazione(StazioneSpaziale stazione, int valutazione) {
        stazione.aggiungiValutazione(valutazione);
        azioniSvolte++;
        System.out.println("Azioni svolte: " + azioniSvolte + "/3");
    }

    public boolean isEsperto() {
        return azioniSvolte >= 3;
    }

    public int getAzioniSvolte() {
        return azioniSvolte;
    }

    @Override
    public void visualizzaDati() {
        super.visualizzaDati();
        System.out.println("Ruolo: Ispettore");
        System.out.println("Azioni svolte: " + azioniSvolte);
        System.out.println("Stato: " + (isEsperto() ? "IspettoreEsperto" : "Ispettore"));
    }
}

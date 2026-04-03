package classi;

public class DocenteMedie extends Docente {
    private String specializzazione;

    public DocenteMedie(String nome, int eta, String specializzazione) {
        super(nome, eta, "Scuola Media");
        this.specializzazione = specializzazione;
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di scuola media. Materia: " + specializzazione);
    }

    public void organizzaInterrogazione(Studente s) {
        if (getStudenti().contains(s)) {
            System.out.println("Interrogazione programmata per: " + s.getNome());
        } else {
            System.out.println("Errore: " + s.getNome() + " non è nella lista di questo docente!");
        }
    }
}
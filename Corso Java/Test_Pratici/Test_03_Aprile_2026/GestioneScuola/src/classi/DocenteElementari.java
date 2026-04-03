package classi;

public class DocenteElementari extends Docente {
    
    private String attivitaDidattiche;

    public DocenteElementari(String nome, int eta, String attivitaDidattiche) {
        super(nome, eta, "Scuola Elementare");
        this.attivitaDidattiche = attivitaDidattiche;
    }

    public String getAttivitaDidattiche() {
        return attivitaDidattiche;
    }

    public void setAttivitaDidattiche(String a) {
        this.attivitaDidattiche = a;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di scuola elementare. Attività: " + attivitaDidattiche);
    }

    public void organizzaGita(String destinazione) {
        System.out.println("Gita scolastica organizzata a: " + destinazione);
    }
}
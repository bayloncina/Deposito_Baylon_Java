package classi;

public class DocenteSuperiori extends Docente {
    
    private String materiaSpecializzata;
    private boolean coordinatoreClasse;

    public DocenteSuperiori(String nome, int eta, String materiaSpecializzata, boolean coordinatoreClasse) {
        super(nome, eta, "Scuola Superiore");
        this.materiaSpecializzata = materiaSpecializzata;
        this.coordinatoreClasse = coordinatoreClasse;
    }

    public String getMateriaSpecializzata() {
        return materiaSpecializzata;
    }

    public void setMateriaSpecializzata(String m) {
        this.materiaSpecializzata = m;
    }

    public boolean isCoordinatoreClasse() {
        return coordinatoreClasse;
    }

    public void setCoordinatoreClasse(boolean c) {
        this.coordinatoreClasse = c;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di scuola superiore. Materia: " + materiaSpecializzata
                + (coordinatoreClasse ? " [Coordinatore di classe]" : ""));
    }

    public void assegnaEsame(Studente s, String tipoEsame) {
        if (getStudenti().contains(s)) {
            System.out.println("Esame \"" + tipoEsame + "\" assegnato a " + s.getNome());
        } else {
            System.out.println("Errore: " + s.getNome() + " non è nella lista di questo docente!");
        }
    }
}
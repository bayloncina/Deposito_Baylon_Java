package classi;

import java.util.ArrayList;

import interfaces.Registrabile;

public abstract class Docente extends Persona implements Registrabile {

    private String materia;
    //ogni docente ha la sua lista privata di studenti
    private ArrayList<Studente> studenti = new ArrayList<>();

    public Docente(String nome, int eta, String materia) {
        super(nome, eta);
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }

    public void aggiungiStudente(Studente s) {
        studenti.add(s);
    }

    // Restituisce la lista degli studenti assegnati a questo docente
    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    /**
     * Assegna un voto a uno studente.
     * Prima verifica che lo studente sia nella lista del docente
     * altriemtni non può assegnare il voto
     */
    public void assegnaVoto(Studente s, int voto) {
        if (studenti.contains(s)) {
            s.aggiungiVoto(voto);
            System.out.println("Voto " + voto + " assegnato a " + s.getNome());
        } else {
            System.out.println("Errore: " + s.getNome()
                    + " non è nella lista di questo docente!");
        }
    }

    @Override
    public void registrazione() {
        System.out.println("Registrazione tramite segreteria didattica");
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di " + materia);
    }

    @Override
    public String toString() {

        return "Docente: " + super.toString();
    }

}

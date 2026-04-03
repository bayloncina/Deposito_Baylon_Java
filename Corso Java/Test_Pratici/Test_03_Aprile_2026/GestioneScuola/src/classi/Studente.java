package classi;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.Registrabile;
import interfaces.Valutabile;

public abstract class Studente extends Persona implements Valutabile, Registrabile {

    private String classeFrequentata;
    private ArrayList<Integer> voti = new ArrayList<>();

    public Studente(String nome, int eta, String classeFrequentata) {
        super(nome, eta);
        this.classeFrequentata = classeFrequentata;

    }

    public String getClasseFrequentata() {
        return classeFrequentata;
    }

    public ArrayList<Integer> getVoti() {
        return voti;
    }

    public void setClasseFrequentata(String classeFrequentata) {
        this.classeFrequentata = classeFrequentata;
    }

    public void setVoti(ArrayList<Integer> voti) {
        this.voti = voti;
    }

    void aggiungiVoto(int voto) {
        voti.add(voto);
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente");

    }

    @Override
    public String toString() {
        return "Studente: " + super.toString() + "Classe: " + classeFrequentata;
    }

    @Override
    public int votoMassimo() {
        if (voti.isEmpty())
            return 0;
        return Collections.max(voti);
    }

    @Override
    public int votoMinimo() {
        if (voti.isEmpty())
            return 0;
        return Collections.min(voti);
    }

    public void stampaVoti() {
        if (voti.isEmpty()) {
            System.out.println(getNome() + " non ha ancora voti.");
            return;
        }
        System.out.println("Voti di " + getNome() + ": " + voti);
        System.out.printf("Media: %.2f | Max: %d | Min: %d%n",
                calcolaMedia(), votoMassimo(), votoMinimo());
    }

    @Override
    public double calcolaMedia() {
        if (voti.isEmpty())
            return 0.0;
        int somma = 0;
        for (int v : voti)
            somma += v;
        return (double) somma / voti.size();
    }

    @Override
    public void registrazione() {
        System.out.println("Registrazione tramite modulo online");
    }
}
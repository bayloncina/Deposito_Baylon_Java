package fabbrica.personale;

import fabbrica.produzione.Macchina;

public abstract class Operaio {
    private String nome;

    public Operaio(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // concreti uguali per tutti gli operai
    public void lavora(Macchina m) {
        System.out.println(nome + " accende la macchina " + m.getNome());
        m.accendi();
    }

    public void ferma(Macchina m) {
        System.out.println(nome + " spegne la macchina " + m.getNome());
        m.spegni();
    }

    // astratto ogni operaio ha la sua mansione specifica
    public abstract void eseguiMansione(Macchina m);

    @Override
    public String toString() {
        return "nome: " + nome;
    }
}
package fabbrica.personale;

import fabbrica.produzione.Macchina;

public class OperaioDirigente extends Operaio{

    public OperaioDirigente(String nome) {
        super(nome);
    }


    // stampa lo stato della macchina solo il dirigente può farlo
    @Override
    public void eseguiMansione(Macchina m) {
        System.out.println(getNome() + " controlla lo stato:");
        m.stampaStato();
    }
    @Override
    public String toString() {
        return "Operaio Dirigente" + super.toString();
    }
}

package fabbrica.produzione;

public class BustinaSolubile extends Prodotto {

    private String gusto;
    private double grammi;

    public BustinaSolubile(String nome, String descrizione, String gusto, double grammi) {
        super(nome, descrizione);
        this.gusto = gusto;
        this.grammi = grammi;

    }

    public String getGusto() {
        return gusto;
    }

    public double getGrammi() {
        return grammi;
    }

    public void setGrammi(double grammi) {
        this.grammi = grammi;
    }

    public void setGusto(String gusto) {
        this.gusto = gusto;
    }

    @Override
    public void mostraDettagli() {
    }

}
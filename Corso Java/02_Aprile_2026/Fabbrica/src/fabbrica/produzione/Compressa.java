package fabbrica.produzione;

public class Compressa extends Prodotto {

    private String rivestimento;

    public Compressa(String nome, String descrizione, String rivestimento) {
        super(nome, descrizione);
        this.rivestimento = rivestimento;
    }

    public String getRivestimento() {
        return rivestimento;
    }

    public void setRivestimento(String rivestimento) {
        this.rivestimento = rivestimento;
    }

    @Override
    public void mostraDettagli() {

    }

}

package fabbrica.produzione;

public abstract class Prodotto {
    private String nome;
    private String descrizione;

    public Prodotto(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }

     // metodo astratto ogni famiglia di prodotti lo implementa a modo suo
    public abstract void mostraDettagli();

}

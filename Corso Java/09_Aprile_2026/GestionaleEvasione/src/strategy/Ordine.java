package strategy;

public class Ordine {

    private int id;
    private String cliente;
    private String prodotto;
    private StartegiaEvasione strategia;
    private double prezzo;

    public Ordine(String cliente, String prodotto,
            StartegiaEvasione strategia, double prezzo) {

        this.cliente = cliente;
        this.prodotto = prodotto;
        this.strategia = strategia;
        this.prezzo = prezzo;

    }

    public void setStrategia(StartegiaEvasione strategia) {
        this.strategia = strategia;

    }
    public StartegiaEvasione getStrategia() {
    return strategia;
}

    public void eseguiEvasione() {
        strategia.eseguiEvasione(prezzo);
    }

    @Override
    public String toString() {
        return "Ordine " + id + " | Cliente: " + cliente + " | Prodotto: " + prodotto + " | Prezzo: euro " + prezzo;
    }
}

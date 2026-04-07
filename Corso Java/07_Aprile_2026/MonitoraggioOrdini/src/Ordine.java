public class Ordine {

    private int id;
    private String cliente;
    private String prodotto;
    private int quantita;
    private StatoOrdine stato;

    public Ordine(String cliente, String prodotto, int quantita, StatoOrdine stato) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Ordine{id=" + id + ", cliente='" + cliente + "', prodotto='" + prodotto
                + "', quantita=" + quantita + ", stato=" + stato + "}";
    }
}
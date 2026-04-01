public class ComponenteFinitura {
    private String codice;
    private String nome;
    private String materiale;
    private String colore;
    private double prezzo;

    public ComponenteFinitura(String codice, String nome, String materiale, String colore, double prezzo) {
        this.codice = codice;
        this.materiale = materiale;
        this.colore = colore;
        this.prezzo = prezzo;
    }

    // GETTER
    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getMateriale() {
        return materiale;
    }

    public String getColore() {
        return colore;
    }

    public double getPrezzo() {
        return prezzo;
    }

    // SETTER con controlli
    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            System.out.println("Il nome non può essere vuoto.");
        this.nome = nome;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public void setColore(String colore) {
        if (colore == null || colore.isEmpty())
            System.out.println("Il colore non può essere vuoto.");
        this.colore = colore;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            System.out.println("Il prezzo deve essere maggiore di 0.");
        this.prezzo = prezzo;
    }

    // metodo polimorfico — ridefinito nelle sottoclassi
    public void mostraDettagli() {
        System.out.println("Codice: " + codice + " | Nome: " + nome +
                " | Materiale: " + materiale + " | Colore: " + colore +
                " | Prezzo: " + prezzo + " euro");
    }
}

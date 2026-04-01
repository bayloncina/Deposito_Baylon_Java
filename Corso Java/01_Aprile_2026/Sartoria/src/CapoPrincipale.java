public class CapoPrincipale {

    private String codice;
    private String nome;
    private String tessuto;
    private String colore;
    private String taglia;
    private double prezzo;

    public CapoPrincipale(String codice, String nome, String tessuto, String colore, String taglia, double prezzo) {
        this.codice = codice;
        this.nome = nome;
        this.tessuto = tessuto;
        this.colore = colore;
        this.taglia = taglia;
        this.prezzo = prezzo;
    }

    // GETTER
    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getTessuto() {
        return tessuto;
    }

    public String getColore() {
        return colore;
    }

    public String getTaglia() {
        return taglia;
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

    public void setTessuto(String tessuto) {
        this.tessuto = tessuto;
    }

    public void setColore(String colore) {
        if (colore == null || colore.isEmpty())
            System.out.println("Il colore non può essere vuoto.");
        this.colore = colore;
    }

    public void setTaglia(String taglia) {
        if (taglia == null || taglia.isEmpty())
            System.out.println("La taglia non può essere vuota.");
        this.taglia = taglia;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            System.out.println("Il prezzo deve essere maggiore di 0.");
        this.prezzo = prezzo;
    }

    // metodo polimorfico ridefinito nelle sottoclassi
    public void mostraDettagli() {
        System.out.println("Codice: " + codice + " | Nome: " + nome +
                " | Tessuto: " + tessuto + " | Colore: " + colore +
                " | Taglia: " + taglia + " | Prezzo: " + prezzo + " euro");
    }

}
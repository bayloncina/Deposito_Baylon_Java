public class Pantalone extends CapoPrincipale {
    private String tipoTaglio;

    public Pantalone(String codice, String nome, String tessuto, String colore, String taglia, double prezzo, String tipoTaglio) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        this.tipoTaglio = tipoTaglio;
    }

    public String getTipoTaglio() { return tipoTaglio; }
    public void setTipoTaglio(String tipoTaglio) { this.tipoTaglio = tipoTaglio; }

    @Override
    public void mostraDettagli() {
        super.mostraDettagli();
        System.out.println(" |  Tipo taglio: " + tipoTaglio);
    }
}

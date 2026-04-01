public class Papillon extends ComponenteFinitura {
    private String tipoChiusura;

    public Papillon(String codice, String nome, String materiale, String colore, double prezzo, String tipoChiusura) {
        super(codice, nome, materiale, colore, prezzo);
        this.tipoChiusura = tipoChiusura;
    }

    public String getTipoChiusura() {
        return tipoChiusura;
    }

    public void setTipoChiusura(String tipoChiusura) {
        this.tipoChiusura = tipoChiusura;
    }

    @Override
    public void mostraDettagli() {
        super.mostraDettagli();
        System.out.println("  | Tipo chiusura: " + tipoChiusura);
    }
}
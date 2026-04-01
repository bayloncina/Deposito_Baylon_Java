public class Pochette extends ComponenteFinitura {
    private String piegaDecorativa;

    public Pochette(String codice, String nome, String materiale, String colore, double prezzo,
            String piegaDecorativa) {
        super(codice, nome, materiale, colore, prezzo);
        this.piegaDecorativa = piegaDecorativa;
    }

    public String getPiegaDecorativa() {
        return piegaDecorativa;
    }

    public void setPiegaDecorativa(String piegaDecorativa) {
        this.piegaDecorativa = piegaDecorativa;
    }

    @Override
    public void mostraDettagli() {
        super.mostraDettagli();
        System.out.println(" |  Piega decorativa: " + piegaDecorativa);
    }
}
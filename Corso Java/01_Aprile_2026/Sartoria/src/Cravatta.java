public class Cravatta extends ComponenteFinitura {
    private double larghezza;

    public Cravatta(String codice, String nome, String materiale, String colore, double prezzo, double larghezza) {
        super(codice, nome, materiale, colore, prezzo);
        this.larghezza = larghezza;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    @Override
    public void mostraDettagli() {
        super.mostraDettagli();
        System.out.println(" | Larghezza: " + larghezza + " cm");
    }
}

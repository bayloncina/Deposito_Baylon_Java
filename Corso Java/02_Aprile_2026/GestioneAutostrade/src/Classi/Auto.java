package Classi;

public class Auto extends Veicolo {

    private double cilindrata;

    public Auto(String targa, double velocita, int numeroAssi, double cilindrata) {
        super(targa, velocita, numeroAssi);
        this.cilindrata = cilindrata;
    }

    public double getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(double cilindrata) {
        this.cilindrata = cilindrata;
    }

    // Pedaggio base di 2€ + 0.50€ ogni 1000cc di cilindrata.
    @Override
    public double calcolaPedaggio() {
        return 2.0 + (cilindrata / 1000) * 0.5;
    }

    @Override
    public String toString() {

        return " Auto: " + super.toString() + " Cilindrata: " + cilindrata;
    }
}

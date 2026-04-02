package Classi;

public class Camion extends Veicolo {

    private double peso;

    public Camion(String targa, double velocita, int numeroAssi, double peso) {
        super(targa, velocita, numeroAssi);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Pedaggio base di 5€ + 1.50€ per ogni asse + 2€ ogni 1000kg di peso.
    @Override
    public double calcolaPedaggio() {
        return 5.0 + (getNumeroAssi() * 1.5) + (peso / 1000) * 2.0;
    }

    @Override
    public String toString() {
        return "Camion: " + super.toString() + " Peso: " + peso;
    }
}

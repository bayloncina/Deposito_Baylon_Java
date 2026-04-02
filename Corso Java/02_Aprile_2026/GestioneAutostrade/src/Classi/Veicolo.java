package Classi;

public abstract class Veicolo {

    private String targa;
    private double velocita;
    private int numeroAssi;

    public Veicolo(String targa, double velocita, int numeroAssi) {
        this.targa = targa;
        this.velocita = velocita;
        this.numeroAssi = numeroAssi;
    };

    public int getNumeroAssi() {
        return numeroAssi;
    }

    public String getTarga() {
        return targa;
    }

    public double getVelocita() {
        return velocita;
    }

    public void setNumeroAssi(int numeroAssi) {
        this.numeroAssi = numeroAssi;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setVelocita(double velocita) {

        if (velocita < 0) {
            System.out.println("Inserire una velocità superiore a 0");
        } else {
            this.velocita = velocita;
        }

    }

    public abstract double calcolaPedaggio();

    public String toString() {
        return "Targa: " + targa + " Velocità consentita: " + velocita + " Numero assi: " + numeroAssi;
    }

}
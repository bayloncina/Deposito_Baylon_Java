package Classi;

public class Moto extends Veicolo {

    private String tipoMoto;

    public Moto(String targa, double velocita, int numeroAssi, String tipoMoto) {
        super(targa, velocita, numeroAssi);
        this.tipoMoto = tipoMoto;
    }

    public String getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(String tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    // Pedaggio fisso di 1€
    @Override
    public double calcolaPedaggio() {
        return 1.0;
    }

    @Override
    public String toString() {
        return "Moto" + super.toString() + " Tipo:" + tipoMoto;
    }
}

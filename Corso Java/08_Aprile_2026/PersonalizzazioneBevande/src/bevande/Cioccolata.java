package bevande;
public class Cioccolata implements Bevanda {
    @Override
    public String getDescrizione() {
        return "Cioccolata calda";
    }

    @Override
    public double getCosto() {
        return 1.50;
    }
}
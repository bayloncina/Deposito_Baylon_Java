package decorator;
import bevande.Bevanda;

public class LatteDec extends IngredienteDecorator {
    
    public LatteDec(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevanda.getDescrizione() + " + Latte";
    }

    @Override
    public double getCosto() {
        return bevanda.getCosto() + 0.30;
    }
}
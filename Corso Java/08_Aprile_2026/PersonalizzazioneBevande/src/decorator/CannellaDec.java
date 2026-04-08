package decorator;
import bevande.Bevanda;

public class CannellaDec extends IngredienteDecorator {
    
    public CannellaDec(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevanda.getDescrizione() + " + Cannella";
    }

    @Override
    public double getCosto() {
        return bevanda.getCosto() + 0.20;
    }
}
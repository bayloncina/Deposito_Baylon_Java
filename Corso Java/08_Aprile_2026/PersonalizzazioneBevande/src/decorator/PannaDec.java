package decorator;
import bevande.Bevanda;

public class PannaDec extends IngredienteDecorator {
    
    public PannaDec(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevanda.getDescrizione() + " + Panna";
    }

    @Override
    public double getCosto() {
        return bevanda.getCosto() + 0.50;
    }
}
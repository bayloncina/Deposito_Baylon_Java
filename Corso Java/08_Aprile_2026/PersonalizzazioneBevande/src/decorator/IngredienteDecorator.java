package decorator;
import bevande.Bevanda;

public abstract class IngredienteDecorator implements Bevanda {
    
    protected Bevanda bevanda;

    public IngredienteDecorator(Bevanda bevanda) {
        this.bevanda = bevanda;
    }
}
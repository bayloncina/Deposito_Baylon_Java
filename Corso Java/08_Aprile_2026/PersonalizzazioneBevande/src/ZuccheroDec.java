public class ZuccheroDec extends IngredienteDecorator {
    
    public ZuccheroDec(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevanda.getDescrizione() + " + Zucchero";
    }

    @Override
    public double getCosto() {
        return bevanda.getCosto() + 0.10;
    }
}
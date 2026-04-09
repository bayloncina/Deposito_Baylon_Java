public class FormaggioDecorator extends HamburgerDecorator {

    public FormaggioDecorator(IHamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getDescrizione() {
        return hamburger.getDescrizione() + " + Formaggio";
    }

    @Override
    public double getPrezzo() {
        return hamburger.getPrezzo() + 0.50;
    }
}

    


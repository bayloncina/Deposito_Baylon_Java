public class BaconDecorator extends HamburgerDecorator {

    public BaconDecorator(IHamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getDescrizione() {
        return hamburger.getDescrizione() + " + Bacon";
    }

    @Override
    public double getPrezzo() {
        return hamburger.getPrezzo() + 0.80;
    }
}
